package valera.gord.magnusmovieproject.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<MovieResponseDto> addMovie(@RequestBody MovieRequestDto movieRequestDto) {
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
    public ResponseEntity<MovieResponseDto> updateMovieById(
            @PathVariable @Valid @NotNull long id,
            @Valid @RequestBody MovieRequestDto dto){
        return ResponseEntity.ok(movieService.updateMovieById(dto,id));
    }
    // api/v1/movies/1
    @DeleteMapping("/{id}")
    public ResponseEntity<MovieResponseDto> deleteMovieById(@PathVariable @Valid @NotNull long id) {
        return ResponseEntity.ok(movieService.deleteMovieById(id));

    }

    // GET api/v1/movies/page
    // GET api/v1/movies/pageSize=15&pageNo=1&
    @GetMapping("/page")
    public ResponseEntity<List<MovieResponseDto>> getMoviePage(
            @RequestParam(value = "pageNo",required = false,defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize
    ){
        return ResponseEntity.ok(movieService.getAllMovies(pageNo,pageSize));

    }

}
