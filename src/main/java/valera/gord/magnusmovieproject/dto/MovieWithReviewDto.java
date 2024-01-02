package valera.gord.magnusmovieproject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieWithReviewDto {

    private Long id;

    private String title;

    private String description;

    private LocalDate releaseDate;

    private String genre;

    private Integer duration;

    private String director;

    private String poster;

    private List<ReviewResponseDto> reviews;

}
