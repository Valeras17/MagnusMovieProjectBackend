package valera.gord.magnusmovieproject.dto;

import java.util.Set;

public record SignInResponseDto(String jwt, Set<String> roles) {

}
