package ru.golovnev.entity;

import lombok.*;
import ru.golovnev.model.CounterAgent;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "counteragents")
public class CounterAgentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "inn")
    private String inn;

    @Column(name = "kpp")
    private String kpp;

    @Column(name = "account")
    private String numberAccount;

    @Column(name = "bik")
    private String bik;

    public static CounterAgentEntity from(CounterAgent newAgent) {
        return CounterAgentEntity.builder()
                .name(newAgent.getName())
                .inn(newAgent.getInn())
                .kpp(newAgent.getKpp())
                .numberAccount(newAgent.getNumberAccount())
                .bik(newAgent.getBik())
                .build();
    }
}
