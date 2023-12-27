package valera.gord.magnusmovieproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valera.gord.magnusmovieproject.entity.Movie;

import java.time.LocalDate;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

        Optional<Movie> findByTitleAndReleaseDate(String title, LocalDate releaseDate);




}
