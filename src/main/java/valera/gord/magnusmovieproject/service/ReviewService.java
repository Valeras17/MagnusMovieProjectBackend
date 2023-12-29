package valera.gord.magnusmovieproject.service;

import org.springframework.security.core.Authentication;
import valera.gord.magnusmovieproject.dto.ReviewRequestDto;
import valera.gord.magnusmovieproject.dto.ReviewResponseDto;

import java.util.List;

public interface ReviewService {
    ReviewResponseDto addNewReview(long movieId, ReviewRequestDto dto, Authentication authentication);
    List<ReviewResponseDto> findReviewByMovieId(long movieId);

    ReviewResponseDto updateReview( long reviewId, ReviewRequestDto dto);
    ReviewResponseDto deleteReviewById(long reviewId);




}
