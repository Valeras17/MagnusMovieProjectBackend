package valera.gord.magnusmovieproject.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import valera.gord.magnusmovieproject.dto.ReviewRequestDto;
import valera.gord.magnusmovieproject.dto.ReviewResponseDto;
import valera.gord.magnusmovieproject.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
@SecurityRequirement(
        name = "Bearer Authentication"
)
public class ReviewController {
    private final ReviewService reviewService;

//POST http://localhost:8080/api/v1/movies/1/reviews
@PostMapping("/{id}/reviews")
public ResponseEntity<ReviewResponseDto> addNewReview(
        @PathVariable(name = "id") long movieId,
        @RequestBody ReviewRequestDto dto,
        UriComponentsBuilder uriComponentsBuilder,
        Authentication authentication
){
    var saved = reviewService.addNewReview(movieId,dto,authentication);
    var uri = uriComponentsBuilder
            .path("api/v1/movies/{movie_id}/{review_id}")
            .buildAndExpand(movieId,saved.getId())
            .toUri();
    return ResponseEntity.created(uri).body(saved);

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
            @RequestBody ReviewRequestDto dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(reviewService.updateReview(reviewId,dto,authentication));
    }

    @DeleteMapping("/reviews/{reviewId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReviewResponseDto> deleteReviewById(@PathVariable long reviewId,Authentication authentication){
        return ResponseEntity.ok(reviewService.deleteReviewById(reviewId,authentication));
    }

}
