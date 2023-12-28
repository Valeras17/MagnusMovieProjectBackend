package valera.gord.magnusmovieproject.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target({ElementType.FIELD})
@Retention(RUNTIME)
public @interface DateRange {
    String message() default "The date must be between 1900 and the current date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
