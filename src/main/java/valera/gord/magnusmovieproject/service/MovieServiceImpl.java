package valera.gord.magnusmovieproject.service;

import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import valera.gord.magnusmovieproject.dto.MoviePageResponseDto;
import valera.gord.magnusmovieproject.dto.MovieRequestDto;
import valera.gord.magnusmovieproject.dto.MovieResponseDto;
import valera.gord.magnusmovieproject.dto.MovieWithReviewDto;
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
        Optional<Movie> existingMovie = movieRepository.findByTitle(
                movie.getTitle());
        if (existingMovie.isPresent()) {
            throw new BadRequestException("Movie", "A movie with the same title  already exists.");
        }
        Movie savedMovie = movieRepository.save(movie);
        return modelMapper.map(savedMovie, MovieResponseDto.class);
    }

        @Override
        public List<MovieResponseDto> getAllMovies() {
            return movieRepository
                    .findAll()
                    .stream()
                    .map(p->modelMapper.map(p,MovieResponseDto.class))
                    .toList();//List<Post>-->List<PostDto>
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
    public MoviePageResponseDto getAllMovies(int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.fromString(sortDir), sortBy);

        Page<Movie> page = movieRepository.findAll(pageable);
        List<MovieWithReviewDto> movies = page.getContent().stream()
                .map(movie -> modelMapper.map(movie, MovieWithReviewDto.class))
                .collect(Collectors.toList());

        return MoviePageResponseDto.builder()
                .results(movies)
                .pageSize(pageSize)
                .pageNo(pageNo)
                .totalPages(page.getTotalPages())
                .totalMovies(page.getTotalElements())
                .isLast(page.isLast())
                .isFirst(page.isFirst())
                .build();
    }

    @Override
    public List<String> getDistinctGenres() {
        try {
            List<String> genres = movieRepository.findDistinctGenres();
            if (genres.isEmpty()) {
                return Collections.emptyList();
            }
            return genres;
        } catch (Exception e) {
            throw new ServiceException("Failed to fetch genres from the database.");
        }
    }

}



