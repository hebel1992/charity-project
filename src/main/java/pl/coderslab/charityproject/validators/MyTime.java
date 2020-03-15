package pl.coderslab.charityproject.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MyTimeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTime {
    int minHour();
    int maxHour();

    String message() default "{myTime.error.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
