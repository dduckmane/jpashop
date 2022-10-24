package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity@Getter@Setter
public class Delivery {
    @Id@GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "delivery")
    private Order order;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)//꼭 String으로 해주어야한다. ordinal은 숫자로 들어간다.
    //숫자로 되어있으면 중간에 삽입을 할 수가 없다.
    private DeliveryStatus status;

}
