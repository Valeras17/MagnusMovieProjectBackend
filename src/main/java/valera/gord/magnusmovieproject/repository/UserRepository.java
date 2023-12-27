package valera.gord.magnusmovieproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valera.gord.magnusmovieproject.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long > {
    Optional<User> findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);
    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findByEmailIgnoreCase(String email);

    //check if a user exists for validation purposes:
    Boolean existsUserByUsernameIgnoreCase(String username);
    Boolean existsUserByEmailIgnoreCase(String username);
}
