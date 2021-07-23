package ru.golovnev.validation.annotation;

import ru.golovnev.validation.ValidNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация для валидации поля наименования контрагента
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidNameValidator.class)
@Documented
public @interface ValidName {
    String message() default "{ValidName.invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
