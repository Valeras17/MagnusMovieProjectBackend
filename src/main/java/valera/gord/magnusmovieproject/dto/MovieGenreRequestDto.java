package valera.gord.magnusmovieproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieGenreRequestDto {
    private Long id;

    private String title;
    private String description;
    private LocalDate releaseDate;
    private String genre;
    private Integer duration; // in minutes
    private String director;
    private String cast;
}
