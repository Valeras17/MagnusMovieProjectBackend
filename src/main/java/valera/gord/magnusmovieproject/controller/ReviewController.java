package valera.gord.magnusmovieproject.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import valera.gord.magnusmovieproject.dto.ReviewRequestDto;
import valera.gord.magnusmovieproject.dto.ReviewResponseDto;
import valera.gord.magnusmovieproject.service.MovieService;
import valera.gord.magnusmovieproject.service.ReviewService;
import valera.gord.magnusmovieproject.service.UserService;

import java.util.List;



@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;
    private final MovieService movieService;
    private final UserService userService;

//POST http://localhost:8080/api/v1/movies/1/reviews
    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<ReviewResponseDto> addNewReview(
            @PathVariable long movieId,
            @RequestHeader(name = "userId") long userId,
            @RequestBody ReviewRequestDto dto) {

        ReviewResponseDto reviewResponseDto = reviewService.addNewReview(movieId, userId, dto);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.CREATED);
    }

//GET http://localhost:8080/api/v1/movies/1/reviews
    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<List<ReviewResponseDto>> findReviewsByMovieId(@PathVariable long movieId) {
        List<ReviewResponseDto> reviews = reviewService.findReviewByMovieId(movieId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

//PUT http://localhost:8080/api/v1/movies/reviews/7
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReviewById(
            @PathVariable("reviewId")long reviewId,
            @RequestBody ReviewRequestDto dto
    ) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId,dto));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDto> deleteReviewById(@PathVariable long reviewId){
        return ResponseEntity.ok(reviewService.deleteReviewById(reviewId));
    }




}
