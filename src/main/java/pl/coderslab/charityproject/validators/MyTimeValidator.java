package pl.coderslab.charityproject.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;

public class MyTimeValidator implements ConstraintValidator<MyTime, LocalTime> {
    private int min;
    private int max;

    @Override
    public void initialize(MyTime constraintAnnotation) {
        this.min = constraintAnnotation.minHour();
        this.max = constraintAnnotation.maxHour();
    }

    @Override
    public boolean isValid(LocalTime localTime, ConstraintValidatorContext constraintValidatorContext) {
        if (localTime != null) {
            if (localTime.getHour() == max) {
                return localTime.getMinute() == 0;
            } else {
                return (localTime.getHour() >= min && (localTime.getHour() <= max));
            }
        }
        return true;
    }
}
