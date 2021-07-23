package ru.golovnev.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Сущность, которая описывает контрагента. Связана с таблицей "counteragents"
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "counteragents")
public class CounterAgentEntity {
    /**
     * ID контрагента
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Наименование контрагента
     */
    @Column(name = "name")
    private String name;

    /**
     * ИНН контрагента
     */
    @Column(name = "inn")
    private String inn;

    /**
     * Код причины постановки на учет(КПП) контрагента
     */
    @Column(name = "kpp")
    private String kpp;

    /**
     * Номер счета контрагента
     */
    @Column(name = "account")
    private String numberAccount;

    /**
     * Банковский идентификационный код(БИК) контрагента
     */
    @Column(name = "bik")
    private String bik;
}
