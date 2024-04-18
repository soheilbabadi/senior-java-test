package ir.smartpath.senior.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class ItuneSearchResponse {
    private int resultCount;
    private List<Result> results;



    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Result {
        private String wrapperType;
        private String kind;
        private long artistId;
        private long collectionId;
        private long trackId;
        private String artistName;
        private String collectionName;
        private String trackName;
        private String collectionCensoredName;
        private String trackCensoredName;
        private String artistViewUrl;
        private String collectionViewUrl;
        private String trackViewUrl;
        private String previewUrl;
        private String artworkUrl30;
        private String artworkUrl60;
        private String artworkUrl100;
        private double collectionPrice;
        private double trackPrice;
        private String releaseDate;
        private String collectionExplicitness;
        private String trackExplicitness;
        private int discCount;
        private int discNumber;
        private int trackCount;
        private int trackNumber;
        private long trackTimeMillis;
        private String country;
        private String currency;
        private String primaryGenreName;
        private boolean isStreamable;

    }
}

