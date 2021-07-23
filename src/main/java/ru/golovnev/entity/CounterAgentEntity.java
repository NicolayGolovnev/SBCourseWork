package ru.golovnev.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ru.golovnev.model.CounterAgent;

import javax.persistence.*;
import java.util.UUID;

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
    private Long id;

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
}
