package valera.gord.magnusmovieproject.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) //we need to check in runtime
@Constraint(validatedBy = {UniqueEmailValidator.class})
public @interface UniqueEmail {
    String message() default "The date must be between 1900 and the current date";
    //(קבוצות מאפשרות לקבץ ולידטורים יחד) groups validation enable/
    Class<?>[] groups() default {};
    //the annotation stores a payload
    //the field value is stored in the payload
    Class<? extends Payload>[] payload() default { };
}
