package ru.golovnev.validations.annotations;

import ru.golovnev.validations.ValidInnValidator;
import ru.golovnev.validations.ValidNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidInnValidator.class)
@Documented
public @interface ValidInn {
    String message() default "{ValidInn.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
