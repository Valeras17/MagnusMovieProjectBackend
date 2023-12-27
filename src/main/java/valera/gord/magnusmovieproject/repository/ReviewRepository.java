package valera.gord.magnusmovieproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valera.gord.magnusmovieproject.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,String> {
}
