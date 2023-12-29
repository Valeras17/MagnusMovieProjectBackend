package valera.gord.magnusmovieproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import valera.gord.magnusmovieproject.dto.ReviewRequestDto;
import valera.gord.magnusmovieproject.dto.ReviewResponseDto;
import valera.gord.magnusmovieproject.entity.Movie;
import valera.gord.magnusmovieproject.entity.Review;
import valera.gord.magnusmovieproject.entity.User;
import valera.gord.magnusmovieproject.error.ResourceNotFoundException;
import valera.gord.magnusmovieproject.repository.MovieRepository;
import valera.gord.magnusmovieproject.repository.ReviewRepository;
import valera.gord.magnusmovieproject.repository.UserRepository;

import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

        @Override
        public ReviewResponseDto addNewReview(long movieId, ReviewRequestDto dto, Authentication authentication) {
            String username = authentication.getName();
            User user = userRepository.findByUsernameIgnoreCase(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User", username));

            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new ResourceNotFoundException("Movie", movieId));

            Review review = modelMapper.map(dto, Review.class);
            review.setUser(user);
            review.setMovie(movie);

            Review savedReview = reviewRepository.save(review);
            return modelMapper.map(savedReview, ReviewResponseDto.class);
        }

        @Override
    public List<ReviewResponseDto> findReviewByMovieId(long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new ResourceNotFoundException("movie", movieId);
        }
        var reviews = reviewRepository.findReviewByMovieId(movieId);
        return reviews.stream().map(
                review -> modelMapper.map(review, ReviewResponseDto.class)
        ).toList();
    }

    @Override
    public ReviewResponseDto updateReview(long reviewId, ReviewRequestDto dto) {
        var reviewDb =
                reviewRepository
                        .findById(reviewId)
                        .orElseThrow(()-> new ResourceNotFoundException("Review",reviewId));
        reviewDb.setTextReview(dto.getTextReview());
        var saved = reviewRepository.save(reviewDb);
        return modelMapper.map(saved,ReviewResponseDto.class);
    }

    @Override
    public ReviewResponseDto deleteReviewById(long reviewId) {
        var saved =
                reviewRepository
                        .findById(reviewId)
                        .orElseThrow(()-> new ResourceNotFoundException("Review",reviewId));
        reviewRepository.deleteById(reviewId);
        return modelMapper.map(saved,ReviewResponseDto.class);
    }
}




