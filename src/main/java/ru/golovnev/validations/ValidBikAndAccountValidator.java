package ru.golovnev.validations;

import ru.golovnev.model.CounterAgent;
import ru.golovnev.validations.annotations.ValidBikAndAccount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidBikAndAccountValidator implements ConstraintValidator<ValidBikAndAccount, CounterAgent> {

    @Override
    public void initialize(ValidBikAndAccount constraintAnnotation) {
    }

    @Override
    public boolean isValid(CounterAgent value, ConstraintValidatorContext context) {
        String bik = value.getBik();
        String numberAccount = value.getNumberAccount();
        if (numberAccount.length() != 20){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Поле должно содержать 20 значений")
                    .addPropertyNode("numberAccount")
                    .addConstraintViolation();
            return false;
        }
        if (bik.charAt(6) == '0' && bik.charAt(7) == '0')
            if (!checkAccountByRKC(bik, numberAccount)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Счет указан неверно - отсутствует в данном РКЦ (БИК)")
                        .addPropertyNode("numberAccount")
                        .addConstraintViolation();
                return false;
            }
        else
            if (!checkAccountByCredit(bik, numberAccount)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Коррсчет указан неверно - отсутствует в данном РКЦ (БИК)")
                        .addPropertyNode("numberAccount")
                        .addConstraintViolation();
                return false;
            }
        return true;
    }

    private boolean checkAccountByRKC(String bik, String account) {
        account = '0' + bik.substring(4, 6) + account;
        return checkSum(account) == 0;
    }

    private boolean checkAccountByCredit(String bik, String account) {
        account = bik.substring(6) + account;
        return checkSum(account) == 0;
    }

    private int checkSum(String account) {
        int[] checksums = {7, 1, 3};
        long sum = 0L;
        for (int i = 0; i < account.length(); i++)
            sum += (long) Character.getNumericValue(account.charAt(i)) * checksums[i % checksums.length];
        return (int) (sum % 10);
    }
}
