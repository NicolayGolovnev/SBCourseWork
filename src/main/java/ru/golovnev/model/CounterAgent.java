package ru.golovnev.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CounterAgent {
    private long id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Max(value = 20, message = "Имя не должно быть больше 20 символов")
    private String name;

    @Size(min = 10, max = 12, message = "ИНН должен иметь 10 или 12 знаков")
    private String inn;

    @Size(min = 9, max = 9, message = "Код содержит ровно 9 символов")
    private String kpp;

    private String numberAccount;

    private String bik;


}
