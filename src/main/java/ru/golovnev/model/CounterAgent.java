package ru.golovnev.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import ru.golovnev.validation.annotation.ValidBikAndAccount;
import ru.golovnev.validation.annotation.ValidInn;
import ru.golovnev.validation.annotation.ValidName;
import ru.golovnev.validation.group.OnCreate;
import ru.golovnev.validation.group.OnUpdate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Модель сущности контрагента с валидацией
 */
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Модель контрагента")
@ValidBikAndAccount(groups = {OnCreate.class, OnUpdate.class})
public class CounterAgent {
    /**
     * ID контрагента
     */
    @ApiModelProperty(
            value = "Идентификатор",
            name = "id",
            dataType = "Long",
            example = "9999")
    private Long id;

    /**
     * Наименование контрагента
     */
    @ApiModelProperty(
            value = "Имя контрагента",
            name = "name",
            dataType = "String",
            example = "CounterAgentName")
    @ValidName(groups = {OnCreate.class}, message = "Такое наименование контрагента уже существует")
    @NotEmpty(groups = {OnCreate.class, OnUpdate.class}, message = "Поле не должно быть пустым")
    private String name;

    /**
     * ИНН контрагента
     */
    @ApiModelProperty(
            value = "ИНН контрагента",
            name = "inn",
            dataType = "String",
            example = "1234567890")
    @ValidInn(groups = {OnCreate.class, OnUpdate.class}, message = "Инн должен быть корректным (10-значный или 12-значный)")
    private String inn;

    /**
     * КПП контрагента
     */
    @ApiModelProperty(
            value = "Код причины постановки на учет (КПП)",
            name = "kpp",
            dataType = "String",
            example = "222443001")
    @Size(groups = {OnCreate.class, OnUpdate.class}, min = 9, max = 9, message = "Код должен содержать 9 символов")
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = "(^[(0-9)]+)$", message = "КПП должен состоять только из цифр")
    private String kpp;

    /**
     * Номер счета контрагента
     */
    @ApiModelProperty(
            value = "Номер счета",
            name = "numberAccount",
            dataType = "String",
            example = "30101810200000000604")
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = "(^[(0-9)]+)$", message = "Счет должен состоять только из цифр")
    private String numberAccount;

    /**
     * Банковский идентификационный код(БИК) контрагента
     */
    @ApiModelProperty(
            value = "Банковский идентификационный код",
            name = "bik",
            dataType = "String",
            example = "040173604")
    @Size(groups = {OnCreate.class, OnUpdate.class}, min = 9, max = 9, message = "БИК банка должен содержать 9 цифр")
    @Pattern(groups = {OnCreate.class, OnUpdate.class}, regexp = "(^[(0-9)]+)$", message = "БИК банка должен состоять только из цифр")
    private String bik;
}
