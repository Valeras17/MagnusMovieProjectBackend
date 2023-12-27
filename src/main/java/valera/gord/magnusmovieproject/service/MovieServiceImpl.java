package valera.gord.magnusmovieproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import valera.gord.magnusmovieproject.dto.*;
import valera.gord.magnusmovieproject.entity.Movie;
import valera.gord.magnusmovieproject.error.BadRequestException;
import valera.gord.magnusmovieproject.error.ResourceNotFoundException;
import valera.gord.magnusmovieproject.repository.MovieRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public MovieResponseDto addMovie(MovieRequestDto movieRequestDto) {
        Movie movie = modelMapper.map(movieRequestDto, Movie.class);
        Optional<Movie> existingMovie = movieRepository.findByTitleAndReleaseDate(
                movie.getTitle(), movie.getReleaseDate());
        if (existingMovie.isPresent()) {
            throw new BadRequestException("Movie", "A movie with the same title and release date already exists.");
        }
        Movie savedMovie = movieRepository.save(movie);
        return modelMapper.map(savedMovie, MovieResponseDto.class);
    }

        @Override
        public List<MovieResponseDto> getAllMovies() {
            List<Movie> movies = movieRepository.findAll();
            if (movies.isEmpty()) {
                throw new ResourceNotFoundException("Movies", "No movies found");
            }
            return movies.stream()
                    .map(movie -> modelMapper.map(movie, MovieResponseDto.class))
                    .collect(Collectors.toList());
        }

    @Override
    public MovieResponseDto getMovieById(long id) {
        Movie movie = getMovieEntity(id);
        return modelMapper.map(movie, MovieResponseDto.class);
    }

    private Movie getMovieEntity(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", id, "Not Found"));
    }

    @Override
    public MovieResponseDto updateMovieById(MovieRequestDto dto, long id) {
        var savedMovieDb = getMovieEntity(id);

        savedMovieDb.setTitle(dto.getTitle());
        savedMovieDb.setDescription(dto.getDescription());
        savedMovieDb.setGenre(dto.getGenre());
        savedMovieDb.setDirector(dto.getDirector());
        savedMovieDb.setCast(dto.getCast());
        savedMovieDb.setDuration(dto.getDuration());

        var saved = movieRepository.save(savedMovieDb);
        return modelMapper.map(saved,MovieResponseDto.class);
    }

    @Override
    public MovieResponseDto deleteMovieById(long id) {
        Movie movie = getMovieEntity(id);
        movieRepository.deleteById(id);

        return modelMapper.map(movie,MovieResponseDto.class);
    }
    //Pagination
    @Override
    public List<MovieResponseDto> getAllMovies(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Movie> page = movieRepository.findAll(pageable);
        return
                page.getContent()
                        .stream()
                        .map(movie -> modelMapper.map(movie,MovieResponseDto.class))
                        .toList();

    }


}










//    Optional<Movie> existingMovie = movieRepository.findByTitleAndReleaseDate(movie.getTitle(), movie.getReleaseDate());
//        if (existingMovie.isPresent()) {
//        throw new BadRequestException("Movie", "A movie with the same title and release date already exists.");
//    }
//        return movieRepository.save(movie);





