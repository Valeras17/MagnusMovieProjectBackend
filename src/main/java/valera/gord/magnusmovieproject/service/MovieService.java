package valera.gord.magnusmovieproject.service;

import valera.gord.magnusmovieproject.dto.MovieRequestDto;
import valera.gord.magnusmovieproject.dto.MovieResponseDto;

import java.util.List;

public interface MovieService {
    MovieResponseDto addMovie(MovieRequestDto movieRequestDto);

    List<MovieResponseDto> getAllMovies();

    MovieResponseDto getMovieById(long id);

    MovieResponseDto updateMovieById(MovieRequestDto dto, long id);

    MovieResponseDto deleteMovieById(long id);

    List<MovieResponseDto> getAllMovies(int pageNo, int pageSize);
}