package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Member {
    @Id@GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Embedded
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    //mappedBy란 연관관계의 주인을 orders로 하고 orders의 member에 연관되어있다.
    //주인만 외래키값을 변경할 수 있다.
    //mapped by는 양방향에서 사용하는 것이다. 양방향에서 누가 외래키의 주인이냐 누가 외래키를 만질 수 있냐
    private List<Order>orders=new ArrayList<>();

    public Member() {
    }
}
