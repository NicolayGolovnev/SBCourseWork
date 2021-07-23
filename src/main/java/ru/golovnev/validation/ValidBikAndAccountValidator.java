package ru.golovnev.validation;

import lombok.extern.slf4j.Slf4j;
import ru.golovnev.model.CounterAgent;
import ru.golovnev.validation.annotation.ValidBikAndAccount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Класс-валидатор для одновременной валидации полей БИК и номера счета
 */
@Slf4j
public class ValidBikAndAccountValidator implements ConstraintValidator<ValidBikAndAccount, CounterAgent> {

    @Override
    public boolean isValid(CounterAgent value, ConstraintValidatorContext context) {
        String bik = value.getBik();
        String numberAccount = value.getNumberAccount();
        if (numberAccount.length() != 20){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Поле должно содержать 20 значений")
                    .addPropertyNode("numberAccount")
                    .addConstraintViolation();
            log.error("[ValidBikAndAccount]\tValidation failed");
            return false;
        }
        if (bik.charAt(6) == '0' && bik.charAt(7) == '0') {
            if (!checkAccountByRKC(bik, numberAccount)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Счет указан неверно - отсутствует в данном РКЦ (БИК)")
                        .addPropertyNode("numberAccount")
                        .addConstraintViolation();
                log.error("[ValidBikAndAccount]\tValidation failed");
                return false;
            }
        }
        else {
            if (!checkAccountByCredit(bik, numberAccount)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Коррсчет указан неверно - отсутствует в данном РКЦ (БИК)")
                        .addPropertyNode("numberAccount")
                        .addConstraintViolation();
                log.error("[ValidBikAndAccount]\tValidation failed");
                return false;
            }
        }
        log.info("[ValidBikAndAccount]\tValidation successful");
        return true;
    }

    /**
     * Метод, проверяющий номер счета, открытого в РКЦ
     * @param bik БИК банка
     * @param account номер счета
     * @return {@code true} - если номер счета правильный, иначе неправильный
     */
    private boolean checkAccountByRKC(String bik, String account) {
        account = '0' + bik.substring(4, 6) + account;
        return checkSum(account) == 0;
    }

    /**
     * Метод, проверяющий номер счета, открытого в кредитной организации
     * @param bik БИК банка
     * @param account номер счета
     * @return {@code true} - если номер счета правильный, иначе неправильный
     */
    private boolean checkAccountByCredit(String bik, String account) {
        account = bik.substring(6) + account;
        return checkSum(account) == 0;
    }

    /**
     * Метод подсчета контрольного числа
     * @param account номер счета
     * @return результат проверки номера счета
     */
    private int checkSum(String account) {
        int[] checksums = {7, 1, 3};
        long sum = 0L;
        for (int i = 0; i < account.length(); i++)
            sum += (long) Character.getNumericValue(account.charAt(i)) * checksums[i % checksums.length];
        return (int) (sum % 10);
    }
}
