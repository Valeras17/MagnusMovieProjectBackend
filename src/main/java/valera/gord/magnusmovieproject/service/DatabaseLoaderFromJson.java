package valera.gord.magnusmovieproject.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import valera.gord.magnusmovieproject.entity.Movie;
import valera.gord.magnusmovieproject.repository.MovieRepository;
import java.io.IOException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DatabaseLoaderFromJson {
    private final MovieRepository movieRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;


    @PostConstruct
    @Transactional
    public void init() throws IOException {
        if (movieRepository.count() == 0) {
            var movies = loadMoviesFromJson();
            movieRepository.saveAll(movies);
        }
    }

    private List<Movie> loadMoviesFromJson() throws IOException {
        var resource = resourceLoader.getResource("classpath:movies_data_base.json");
        return objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Movie>>() {});
    }

}
