package valera.gord.magnusmovieproject.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import valera.gord.magnusmovieproject.dto.MoviePageResponseDto;
import valera.gord.magnusmovieproject.dto.MovieRequestDto;
import valera.gord.magnusmovieproject.dto.MovieResponseDto;
import valera.gord.magnusmovieproject.service.MovieService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
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
    public ResponseEntity<List<MovieResponseDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
// api/v1/movies/1
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable @Valid @NotNull long id) {
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

    // GET api/v1/movies/page
    // GET api/v1/movies/pageSize=15&pageNo=1&
    @GetMapping("/page")
    public ResponseEntity<MoviePageResponseDto> getMoviesPage(
            @RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {
        return ResponseEntity.ok(movieService.getAllMovies(pageNo, pageSize, sortBy, sortDir));
    }

    }
