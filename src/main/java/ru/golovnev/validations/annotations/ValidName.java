package ru.golovnev.validations.annotations;

import ru.golovnev.model.CounterAgent;
import ru.golovnev.validations.ValidNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidNameValidator.class)
@Documented
public @interface ValidName {
    String message() default "{ValidName.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
