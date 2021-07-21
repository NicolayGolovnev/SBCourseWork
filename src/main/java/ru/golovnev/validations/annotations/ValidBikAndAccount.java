package ru.golovnev.validations.annotations;

import ru.golovnev.validations.ValidBikAndAccountValidator;
import ru.golovnev.validations.ValidInnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidBikAndAccountValidator.class)
@Documented
public @interface ValidBikAndAccount {
    String message() default "{ValidBikAndAccount.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
