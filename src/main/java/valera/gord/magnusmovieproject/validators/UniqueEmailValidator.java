package valera.gord.magnusmovieproject.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import valera.gord.magnusmovieproject.entity.User;
import valera.gord.magnusmovieproject.repository.UserRepository;


import java.util.Optional;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository userRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        Optional<User> user = userRepository.findByEmailIgnoreCase(email);
        //if user does not exist -> VALID
        return user.isEmpty();
    }
}
