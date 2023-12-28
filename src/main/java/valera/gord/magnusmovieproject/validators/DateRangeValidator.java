package valera.gord.magnusmovieproject.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
@RequiredArgsConstructor
public class DateRangeValidator implements ConstraintValidator<DateRange, LocalDate> {

    private static final int YEAR_START = 1900;

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if (date == null) {
            return true; // or false, depending on your requirements
        }
        LocalDate start = LocalDate.of(YEAR_START, 1, 1);
        LocalDate now = LocalDate.now();
        return !date.isBefore(start) && !date.isAfter(now);
    }
}

