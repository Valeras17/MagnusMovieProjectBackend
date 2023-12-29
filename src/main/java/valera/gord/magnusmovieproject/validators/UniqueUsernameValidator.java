package valera.gord.magnusmovieproject.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import valera.gord.magnusmovieproject.entity.User;
import valera.gord.magnusmovieproject.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(username);
        //if post  with same title does not exist -->GOOD!
        return user.isEmpty();
    }
}
