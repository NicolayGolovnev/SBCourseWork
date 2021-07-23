package ru.golovnev.model;

import lombok.*;
import ru.golovnev.validation.annotation.ValidBikAndAccount;
import ru.golovnev.validation.annotation.ValidInn;
import ru.golovnev.validation.annotation.ValidName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidBikAndAccount
public class CounterAgent {
    private long id;

    @ValidName(message = "Такое наименование контрагента уже существует")
    @NotEmpty(message = "Поле не должно быть пустым")
    private String name;

    @ValidInn(message = "Инн должен быть корректным (10-значный или 12-значный)")
    private String inn;

    @Size(min = 9, max = 9, message = "Код должен содержать 9 символов")
    private String kpp;

    private String numberAccount;

    @Size(min = 9, max = 9, message = "БИК банка должен содержать 9 цифр")
    private String bik;
}
