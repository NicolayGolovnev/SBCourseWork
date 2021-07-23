package ru.golovnev.validation.annotation;

import ru.golovnev.validation.ValidInnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация для валидации поля ИНН контрагента
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidInnValidator.class)
@Documented
public @interface ValidInn {
    String message() default "{ValidInn.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
