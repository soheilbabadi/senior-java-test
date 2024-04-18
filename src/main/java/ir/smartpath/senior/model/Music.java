package ir.smartpath.senior.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class Music implements Serializable {

    @Serial
    private static final long serialVersionUID = 3678881466896927979L;

    private String kind;
    private long artistId;
    private long trackId;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String collectionCensoredName;
    private String trackCensoredName;
    private String releaseDate;
}
