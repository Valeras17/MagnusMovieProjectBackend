package valera.gord.magnusmovieproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotNull
    @Size(min = 2,max = 15)
    private String username;
    @Size(max =25)
    @NotNull
    private String email;
    @Size(min = 6,max = 20)
    @NotNull
    private String password;
}
