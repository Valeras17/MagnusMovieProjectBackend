package valera.gord.magnusmovieproject.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends MagnusMovieException{
    private final String resourceName;
    public BadRequestException(String resourceName) {
        super("%S was invalid".formatted(resourceName));
        this.resourceName = resourceName;
    }
    public BadRequestException(String resourceName,String message) {
        super(message);
        this.resourceName = resourceName;
    }
}
