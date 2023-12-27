package valera.gord.magnusmovieproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    @NotNull
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;




}
