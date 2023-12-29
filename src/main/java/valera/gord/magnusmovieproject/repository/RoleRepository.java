package valera.gord.magnusmovieproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import valera.gord.magnusmovieproject.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long > {

    Optional<Role> findByNameIgnoreCase(String name);
}
