package sber.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
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

    public static CounterAgent from(CounterAgentForm form) {
        return CounterAgent.builder()
                .name(form.getName())
                .inn(form.getInn())
                .kpp(form.getKpp())
                .numberAccount(form.getNumberAccount())
                .bik(form.getBik())
                .build();
    }
}
