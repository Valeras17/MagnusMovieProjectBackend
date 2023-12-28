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


@Builder
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")  // Изменено здесь
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;
    private final MovieService movieService;
    private final UserService userService;

    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<ReviewResponseDto> addNewReview(
            @PathVariable long movieId,
            @RequestHeader(name = "userId") long userId,
            @RequestBody ReviewRequestDto dto) {

        ReviewResponseDto reviewResponseDto = reviewService.addNewReview(movieId, userId, dto);
        return new ResponseEntity<>(reviewResponseDto, HttpStatus.CREATED);
    }
}
