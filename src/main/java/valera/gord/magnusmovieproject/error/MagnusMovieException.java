package valera.gord.magnusmovieproject.error;

public class MagnusMovieException extends RuntimeException{

    public MagnusMovieException() {
    }
    public MagnusMovieException(String message) {
        super(message);
    }

    public MagnusMovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public MagnusMovieException(Throwable cause) {
        super(cause);
    }

    public MagnusMovieException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
