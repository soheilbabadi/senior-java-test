package ir.smartpath.senior.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.Timer;
import ir.smartpath.senior.exception.NotFoundException;
import ir.smartpath.senior.model.*;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "searchCache")

public class SearchServiceImpl implements SearchService {

    private final RestTemplate restTemplate;


    @Value("${search.itune}")
    private String ITUNES_API_URL;

    @Value("${search.google}")
    private String googleApi;

    @Value("${search.limit}")
    private String limit;

    @Value("${search.googleApikey}")
    private String googleApiKey;


    private final Timer getMusicTimer;

    private final Timer getBookTimer;

    @Override
    @Cacheable(key = "#term")
    public synchronized ResponseData search(String term) {

        List<Music> musics = getMusic(term);
        List<Book> books = getBook(term);
        if (musics.isEmpty() && books.isEmpty()) {
            throw new NotFoundException("No results found for term: " + term + "!");
        }
        return new ResponseData(musics, books);
    }

    @Synchronized
    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 10000), retryFor = {SocketTimeoutException.class})
    @Cacheable(key = "#term")
    public List<Music> getMusic(String term) {
        Timer.Sample sample = Timer.start();
        term = term.trim().replace(" ", "+");
        String url = ITUNES_API_URL + "term=" + term + "&limit=" + limit + "&media=music";

        ResponseEntity<String> response = prepareRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ItuneSearchResponse finalResponse = objectMapper.readValue(response.getBody(), ItuneSearchResponse.class);
            sample.stop(getMusicTimer);

            return convertAppleResponseToMusic(finalResponse);
        } catch (JsonProcessingException e) {
            log.error("An error occurred: {}", e.getMessage());
        }

        return null;
    }

    @Synchronized
    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 10000), retryFor = {SocketTimeoutException.class})
    @Cacheable(key = "#term")
    public List<Book> getBook(String term) {
        Timer.Sample sample = Timer.start();
        term = term.trim().replace(" ", "+");
        String url = googleApi + term + "&key=" + googleApiKey + "&maxResults=" + limit + "&printType=books&orderBy=relevance";

        ResponseEntity<String> response = prepareRequest(url);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            GoogleBooksApiResponse finalResponse = objectMapper.readValue(response.getBody(), GoogleBooksApiResponse.class);
            sample.stop(getBookTimer);
            return convertGoogleResponseToBook(finalResponse);
        } catch (JsonProcessingException e) {
            log.error("An error occurred: {}", e.getMessage());
        }
        return null;
    }

    private ResponseEntity<String> prepareRequest(String url) {
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>(requestHeaders);
        requestHeaders.set("Accept", "text/javascript;charset=utf-8");
        return restTemplate.exchange(
                url, HttpMethod.GET, requestEntity,
                String.class);
    }

    private List<Music> convertAppleResponseToMusic(ItuneSearchResponse results) {
        var appleResponse = results.getResults();
        return appleResponse.parallelStream().map(result -> {
            var entity = new Music();
            BeanUtils.copyProperties(result, entity);
            return entity;
        }).collect(Collectors.toList());
    }

    private List<Book> convertGoogleResponseToBook(GoogleBooksApiResponse googleResponse) {

        return googleResponse.getItems().stream().map(item -> {
            var volumeInfo = item.getVolumeInfo();
            var book = new Book();
            BeanUtils.copyProperties(volumeInfo, book);
            if (item.getVolumeInfo().getDescription() != null) {
                book.setDescription(item.getVolumeInfo().getDescription().substring(0, Math.min(item.getVolumeInfo().getDescription().length(), 100)) + "...");
            } else {
                book.setDescription("No description available");
            }
            return book;
        }).toList();

    }
}
