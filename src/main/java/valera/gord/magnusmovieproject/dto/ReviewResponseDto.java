package valera.gord.magnusmovieproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private String textReview;
    private Integer rating;
    private MovieResponseDto movie;
    private UserResponseDto user;
}
