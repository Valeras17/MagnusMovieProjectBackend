package valera.gord.magnusmovieproject.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import valera.gord.magnusmovieproject.validators.UniqueEmail;
import valera.gord.magnusmovieproject.validators.UniqueUsername;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20, message = "username must contain at least 2 letters")
    @UniqueUsername
    private String username;
    @NotNull
    @Email
    @NotEmpty
    @UniqueEmail
    private String email;
    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    @NotNull(message = "password is mandatory")
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?\\W).{6,20}$",
            message = "password must contain at least 6 characters, one or more lower case letters, uppercase letter, symbol, digits"
    )
    private String password;
}
