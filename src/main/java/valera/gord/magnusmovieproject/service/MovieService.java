package valera.gord.magnusmovieproject.service;

import org.springframework.web.client.RestOperations;
import valera.gord.magnusmovieproject.dto.MoviePageResponseDto;
import valera.gord.magnusmovieproject.dto.MovieRequestDto;
import valera.gord.magnusmovieproject.dto.MovieResponseDto;
import valera.gord.magnusmovieproject.entity.Movie;

import java.util.List;

public interface MovieService {
    MovieResponseDto addMovie(MovieRequestDto movieRequestDto);

    List<MovieResponseDto> getAllMovies();

    MovieResponseDto getMovieById(long id);

    MovieResponseDto updateMovieById(MovieRequestDto dto, long id);

    MovieResponseDto deleteMovieById(long id);

    MoviePageResponseDto getAllMovies(int pageNo, int pageSize, String sortBy, String sortDir);


}