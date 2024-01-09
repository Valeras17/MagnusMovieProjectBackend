package valera.gord.magnusmovieproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieWithReviewDto {

    private Long id;
    private String title;
    private String year;
    private String url;
    private String poster;
    private String genre;
    private String backdropScreen;
    private List<ReviewResponseDto> reviews;

}
