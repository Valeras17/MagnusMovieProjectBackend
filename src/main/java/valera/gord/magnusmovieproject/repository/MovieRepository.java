package valera.gord.magnusmovieproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import valera.gord.magnusmovieproject.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

        Optional<Movie> findByTitle(String title);
        @Query("SELECT DISTINCT m.genre FROM Movie m")
        List<String> findDistinctGenres();

        List<Movie> findMoviesByGenreIgnoreCase( String genre);

        List<Movie> findByTitleContainingIgnoreCase(String title);

}
