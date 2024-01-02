package valera.gord.magnusmovieproject.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import valera.gord.magnusmovieproject.entity.Movie;
import valera.gord.magnusmovieproject.repository.MovieRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle,String> {

    private final MovieRepository movieRepository;


    @Override
    public boolean isValid(String title, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Movie> movie = movieRepository.findByTitle(title);
        return movie.isEmpty();
    }
}
