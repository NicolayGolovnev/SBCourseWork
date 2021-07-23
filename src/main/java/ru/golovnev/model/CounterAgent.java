package ru.golovnev.model;

import lombok.*;
import ru.golovnev.validation.annotation.ValidBikAndAccount;
import ru.golovnev.validation.annotation.ValidInn;
import ru.golovnev.validation.annotation.ValidName;
import ru.golovnev.validation.group.OnCreate;
import ru.golovnev.validation.group.OnUpdate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidBikAndAccount(groups = {OnCreate.class, OnUpdate.class})
public class CounterAgent {
    private Long id;

    @ValidName(groups = {OnCreate.class}, message = "Такое наименование контрагента уже существует")
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class}, message = "Поле не должно быть пустым")
    private String name;

    @ValidInn(groups = {OnCreate.class, OnUpdate.class}, message = "Инн должен быть корректным (10-значный или 12-значный)")
    private String inn;

    @Size(groups = {OnCreate.class, OnUpdate.class}, min = 9, max = 9, message = "Код должен содержать 9 символов")
    private String kpp;

    private String numberAccount;

    @Size(groups = {OnCreate.class, OnUpdate.class}, min = 9, max = 9, message = "БИК банка должен содержать 9 цифр")
    private String bik;
}
