package sber.entity;

import lombok.*;
import sber.model.CounterAgentModel;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "counteragents")
public class CounterAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "inn")
    private String inn;

    @Column(name = "kpp")
    private int kpp;

    @Column(name = "account")
    private String numberAccount;

    @Column(name = "bik")
    private String bik;

    public static CounterAgent from(CounterAgentModel newAgent) {
        return CounterAgent.builder()
                .name(newAgent.getName())
                .inn(newAgent.getInn())
                .kpp(newAgent.getKpp())
                .numberAccount(newAgent.getNumberAccount())
                .bik(newAgent.getBik())
                .build();
    }
}
