package jpabook.jpashop.practice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class Person {
    @Id@GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}
