package ir.smartpath.senior.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ir.smartpath.senior.model.Book;
import ir.smartpath.senior.model.ResponseData;
import ir.smartpath.senior.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")

public class SearchController {

    private final SearchService searchService;
    private final MeterRegistry meterRegistry;

    @Operation(summary = "Get a book or music by a keyword in title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Entity",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))}),

            @ApiResponse(responseCode = "404", description = "If Response of search has no Element",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "any Internal server error",
                    content = @Content),
            @ApiResponse(responseCode = "504", description = "SocketTimeoutException on search itune or google",
                    content = @Content),
            @ApiResponse(responseCode = "406", description = "Not Acceptable response body from itune or google",
                    content = @Content)


    }

    )
    @GetMapping("/{term}")
    public ResponseEntity<ResponseData> search(@PathVariable String term) {

        return ResponseEntity.ok(searchService.search(term));
    }

}
