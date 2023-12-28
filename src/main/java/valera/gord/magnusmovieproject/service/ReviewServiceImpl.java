package valera.gord.magnusmovieproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    @Service
    @RequiredArgsConstructor
    public class ReviewServiceImpl implements ReviewService {
        private final ReviewRepository reviewRepository;
        private final UserRepository userRepository;
        private final MovieRepository movieRepository;
        private final ModelMapper modelMapper;

        @Override
        public ReviewResponseDto addNewReview(long movieId, long userId, ReviewRequestDto dto) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", userId));
            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new ResourceNotFoundException("Movie", movieId));

            Review review = modelMapper.map(dto, Review.class);
            review.setUser(user);
            review.setMovie(movie);

            Review savedReview = reviewRepository.save(review);
            return modelMapper.map(savedReview, ReviewResponseDto.class);
        }
    }





