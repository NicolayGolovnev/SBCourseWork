package ru.golovnev.validation;

import lombok.extern.slf4j.Slf4j;
import ru.golovnev.validation.annotation.ValidInn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Класс-валидатор для валидации поля ИНН контрагента
 */
@Slf4j
public class ValidInnValidator implements ConstraintValidator<ValidInn, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.length() < 10 || value.length() == 11) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Поле должно содержать 10 или 12 цифр")
                    .addBeanNode()
                    .addConstraintViolation();
            log.error("[ValidInn]\tValidation failed");
            return false;
        }
        try {
            Long.parseLong(value);
        }
        catch (Exception e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Поле должно содержать 10 или 12 цифр")
                    .addBeanNode()
                    .addConstraintViolation();
            log.error("[ValidInn]\tValidation failed");
            return false;
        }
        if (check10DigInn(value) || check12DigInn(value)) {
            log.info("[ValidInn]\tValidation successful");
            return true;
        }
        else {
            log.error("[ValidInn]\tValidation failed");
            return false;
        }
    }

    /**
     * Метод проверки 10-значного ИНН
     * @param inn передаваемый ИНН
     * @return {@code true} - если ИНН корректный, иначе некорректный
     */
    private boolean check10DigInn(String inn) {
        if (inn.length() != 10)
            return false;
        int[] checksums = {2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
        long sum = 0L;
        for (int i = 0; i < inn.length(); i++)
            sum += (long) checksums[i] * Character.getNumericValue(inn.charAt(i));
        sum = (sum % 11) % 10;
        return sum == Character.getNumericValue(inn.charAt(inn.length() - 1));
    }

    /**
     * Метод проверки 12-значного ИНН
     * @param inn передаваемый ИНН
     * @return {@code true} - если ИНН корректный, иначе некорректный
     */
    private boolean check12DigInn(String inn) {
        if (inn.length() != 12)
            return false;
        int[] checksums = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8, 0};
        long firstSum = 0L;
        long secondSum = 0L;
        for (int i = 0; i < checksums.length - 1; i++) {
            firstSum += checksums[i + 1] * (long) Character.getNumericValue(inn.charAt(i));
        }
        firstSum = (firstSum % 11) % 10;
        for (int i = 0; i < checksums.length; i++) {
            secondSum += checksums[i] * (long) Character.getNumericValue(inn.charAt(i));
        }
        secondSum = (secondSum % 11) % 10;
        return (long) Character.getNumericValue(inn.charAt(10)) == firstSum && (long) Character.getNumericValue(inn.charAt(11)) == secondSum;
    }
}
