package valera.gord.magnusmovieproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valera.gord.magnusmovieproject.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long > {

    Optional<User> findByEmailIgnoreCase(String email);
}
