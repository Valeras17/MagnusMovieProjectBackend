package valera.gord.magnusmovieproject.controller;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import valera.gord.magnusmovieproject.dto.ReviewRequestDto;
import valera.gord.magnusmovieproject.dto.ReviewResponseDto;
import valera.gord.magnusmovieproject.service.MovieService;
import valera.gord.magnusmovieproject.service.ReviewService;
import valera.gord.magnusmovieproject.service.UserService;


@Builder
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;
    private final MovieService movieService;
    private final UserService userService;


    //POST http://localhost:8080/api/v1/movies/{1}/reviews
    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<ReviewResponseDto> addNewReview(
            @PathVariable(name = "movieId") @Valid long movieId,
            @RequestHeader(name = "userId") long userId,
            @RequestBody ReviewRequestDto dto,
            UriComponentsBuilder uriComponentsBuilder) {
        var saved = reviewService.addNewReview(movieId, userId, dto);
        var uri = uriComponentsBuilder
                .path("/api/v1/movies/{movieId}/reviews/{reviewId}")
                .buildAndExpand(movieId, saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(saved);
    }




}
