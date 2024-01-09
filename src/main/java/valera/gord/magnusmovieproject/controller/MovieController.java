package valera.gord.magnusmovieproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import valera.gord.magnusmovieproject.dto.MoviePageResponseDto;
import valera.gord.magnusmovieproject.dto.MovieRequestDto;
import valera.gord.magnusmovieproject.dto.MovieResponseDto;
import valera.gord.magnusmovieproject.service.MovieService;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@SecurityRequirement(
        name = "Bearer Authentication"
)
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieResponseDto> addMovie(
            @RequestBody @Valid MovieRequestDto movieRequestDto) {
        MovieResponseDto movieResponseDto = movieService.addMovie(movieRequestDto);
        return new ResponseEntity<>(movieResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all the site movies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = MovieResponseDto.class)
                            ))),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content(mediaType = "application/json"),
                    description = "Unauthorized"
            )
    })
    public ResponseEntity<List<MovieResponseDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
// api/v1/movies/1
    @GetMapping("/{id}")
    @Operation(summary = "Get a movie by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Movie",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found",
                    content = @Content)})
    public ResponseEntity<MovieResponseDto> getMovieById(
            @PathVariable
            @Valid
            @NotNull
            @Parameter(description = "id of movie, to be searched") long id) {
        MovieResponseDto movieResponseDto = movieService.getMovieById(id);
        return ResponseEntity.ok(movieResponseDto);
    }

    // api/v1/movies/1
   @PutMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieResponseDto> updateMovieById(
            @PathVariable @Valid @NotNull long id,
            @Valid @RequestBody MovieRequestDto dto){
        return ResponseEntity.ok(movieService.updateMovieById(dto,id));
    }
    // api/v1/movies/1
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieResponseDto> deleteMovieById(@PathVariable @Valid @NotNull long id) {
        return ResponseEntity.ok(movieService.deleteMovieById(id));
    }


    //GET api/v1/movies/page
    //GET api/v1/movies/pageSize=15&pageNo=1&
    //GET http://localhost:8080/api/v1/movies/page?pageSize=2&pageNo=0&sortBy=id&sortDir=asc
    @GetMapping("/page")
    public ResponseEntity<MoviePageResponseDto> getMoviesPage(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(movieService.getAllMovies(pageNo, pageSize, sortBy, sortDir));
    }


    @GetMapping("/genres")
    public ResponseEntity<List<String>> getDistinctGenres() {
        try {
            List<String> genres = movieService.getDistinctGenres();
            return ResponseEntity.ok(genres);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList("Error fetching genres: " + e.getMessage()));
        }
    }

}
