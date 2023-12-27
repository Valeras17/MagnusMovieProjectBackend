package valera.gord.magnusmovieproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import valera.gord.magnusmovieproject.dto.ReviewRequestDto;
import valera.gord.magnusmovieproject.dto.ReviewResponseDto;
import valera.gord.magnusmovieproject.entity.Review;
import valera.gord.magnusmovieproject.error.ResourceNotFoundException;
import valera.gord.magnusmovieproject.repository.MovieRepository;
import valera.gord.magnusmovieproject.repository.ReviewRepository;
import valera.gord.magnusmovieproject.repository.UserRepository;



@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewResponseDto addNewReview(long movieId, long userId, ReviewRequestDto dto) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", movieId));

        var review = modelMapper.map(dto, Review.class);
        review.setUser(user);
        review.setMovie(movie);

        var saved = reviewRepository.save(review);
        return modelMapper.map(saved, ReviewResponseDto.class);
    }
}



