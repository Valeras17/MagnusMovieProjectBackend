package valera.gord.magnusmovieproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valera.gord.magnusmovieproject.entity.Movie;
import valera.gord.magnusmovieproject.entity.Review;
import valera.gord.magnusmovieproject.entity.User;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findReviewByMovieId(long movieId);
    List<Review> findReviewByUser(User user);
    Optional<Review> findByUserAndMovie(User user, Movie movie);


}
