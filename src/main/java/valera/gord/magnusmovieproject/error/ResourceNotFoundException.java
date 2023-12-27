package valera.gord.magnusmovieproject.error;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ToString
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends MagnusMovieException{
    private String resourceName;//User
    private String resourceId;//7
    private String message;//not found

//Designated Constructor:
    public ResourceNotFoundException(String resourceName, String resourceId, String message) {
        super("%S with id =  %S %S".formatted(resourceName,resourceId,message));
        this.resourceName = resourceName;
        this.resourceId = resourceId;
        this.message = message;
    }
    //convenience constructor:
    public ResourceNotFoundException(String resourceName, long resourceId, String message) {
        //use other constructor:
        this(resourceName,String.valueOf(resourceId),message);
    }
    //convenience constructor:
    public ResourceNotFoundException(String resourceName, long resourceId) {
        //use other constructor:
        this(resourceName,String.valueOf(resourceId),"Not Found");
    }

    public ResourceNotFoundException(long resourceId) {
        //use other constructor:
        this("Resource",String.valueOf(resourceId),"Not Found");
    }

    public ResourceNotFoundException(String movies, String noMoviesFound) {

    }
}


