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
public class MoviePageResponseDto {
    private List<MovieWithReviewDto> results;
    private int totalPages;
    private long totalMovies;
    private boolean isLast;
    private boolean isFirst;
    /**
     *current page
     */
    private int pageNo;
    /**
     *current page size
     */
    private int pageSize;
}
