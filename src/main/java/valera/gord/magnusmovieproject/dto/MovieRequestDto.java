package valera.gord.magnusmovieproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import valera.gord.magnusmovieproject.validators.DateRange;
import valera.gord.magnusmovieproject.validators.UniqueTitle;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequestDto {
    @NotNull
    @Size(min = 2,max = 255)
    @UniqueTitle
    private String title;
    @NotNull
    @Size(min = 5,max = 1000)
    private String description;
    @NotNull
    @DateRange
    private LocalDate releaseDate;

    @NotNull
    @Size(min = 2,max = 50)
    private String genre;
    @NotNull
    private Integer duration;
    @NotNull
    @Size(min = 2,max = 50)
    private String director;
    @NotNull
    @Size(min = 5,max = 1000)
    private String cast;
}
