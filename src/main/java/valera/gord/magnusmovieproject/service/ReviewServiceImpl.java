package valera.gord.magnusmovieproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import valera.gord.magnusmovieproject.dto.ReviewRequestDto;
import valera.gord.magnusmovieproject.dto.ReviewResponseDto;
import valera.gord.magnusmovieproject.entity.Movie;
import valera.gord.magnusmovieproject.entity.Review;
import valera.gord.magnusmovieproject.entity.User;
import valera.gord.magnusmovieproject.error.BadRequestException;
import valera.gord.magnusmovieproject.error.ResourceNotFoundException;
import valera.gord.magnusmovieproject.repository.MovieRepository;
import valera.gord.magnusmovieproject.repository.ReviewRepository;
import valera.gord.magnusmovieproject.repository.RoleRepository;
import valera.gord.magnusmovieproject.repository.UserRepository;

import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

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
    @Transactional
    public ReviewResponseDto updateReview(long reviewId, ReviewRequestDto dto, Authentication authentication) {
        var reviewDb =
                reviewRepository
                        .findById(reviewId)
                        .orElseThrow(()-> new ResourceNotFoundException("Review",reviewId));
        validateEditingByUser(authentication, reviewDb);

        reviewDb.setTextReview(dto.getTextReview());
        var saved = reviewRepository.save(reviewDb);
        return modelMapper.map(saved,ReviewResponseDto.class);
    }

        private void validateEditingByUser(Authentication authentication, Review reviewDb) {
            var user = reviewDb.getUser();
            var adminRole = roleRepository.findByNameIgnoreCase("ROLE_ADMIN").orElseThrow();
            var isAdmin = user.getRoles().contains(adminRole);
            var userOwnsReview = user.getUsername().equalsIgnoreCase(authentication.getName());
            if (!isAdmin && userOwnsReview){
                throw new BadRequestException("user","Review must belong to the editing user");
            }
        }


    @Override
    @Transactional
    public ReviewResponseDto deleteReviewById(long reviewId, Authentication authentication) {
        var saved =
                reviewRepository
                        .findById(reviewId)
                        .orElseThrow(()-> new ResourceNotFoundException("Review",reviewId));
        validateEditingByUser(authentication,saved);
        reviewRepository.deleteById(reviewId);
        return modelMapper.map(saved,ReviewResponseDto.class);
    }
}




