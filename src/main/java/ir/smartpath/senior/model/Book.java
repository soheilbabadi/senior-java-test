package ir.smartpath.senior.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 3678881466896927979L;

    private String title;
    private String subtitle;
    private List<String> authors;
    private String publishedDate;
    private String publisher;
    private String description;
    private int pageCount;
    private List<String> categories;
}
