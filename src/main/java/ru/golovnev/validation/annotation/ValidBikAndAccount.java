package ru.golovnev.validation.annotation;

import ru.golovnev.validation.ValidBikAndAccountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация для валидации пары БИК + номер счета
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidBikAndAccountValidator.class)
@Documented
public @interface ValidBikAndAccount {
    String message() default "{ValidBikAndAccount.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
