package valera.gord.magnusmovieproject.service;

import valera.gord.magnusmovieproject.dto.ReviewRequestDto;
import valera.gord.magnusmovieproject.dto.ReviewResponseDto;

public interface ReviewService {
    ReviewResponseDto addNewReview(long movieId,long userId,ReviewRequestDto dto);
}
