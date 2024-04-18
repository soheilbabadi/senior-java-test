package ir.smartpath.senior.model;

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
public class ResponseData implements Serializable {

    @Serial
    private final static long serialVersionUID = 3678881466896927979L;

    private List<Music> musics;
    private List<Book> books;

}
