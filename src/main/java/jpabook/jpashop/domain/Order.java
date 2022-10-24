package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@Table(name = "orders")//order로 하면 에러가 난다.
/*기본생성자는 있어야함 + 생성자함수를 통해서만 생성하겠다라는 의미*/
/*protected 생성자를 만들겠다라는 의미이다.*/
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    /*필드에 하는 것은 딱 연관관계만 정해주기 위한거다. 외래키를 어디에 두느냐*/
    @Id@GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //주문 회원

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem>orderItems=new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    //many가 있으면 many쪽에 외래키가 들어가지만 onetoone에서는 joincolumn한쪽에 외래키가 들어간다.
    private Delivery delivery;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    //연관관계편의 메서드
    public void setMember(Member member){
        this.member=member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        /*연관관계의 주인과 상관없이 편의메서드위치든 자유롭다.*/
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery=delivery;
        delivery.setOrder(this);
    }



    //==생성메서드==//
    public static Order createOrder(Member member,Delivery delivery,OrderItem...orderItems){
        /*보면 연관관계에 있는 모든 것들을 매개변수로 받아서 이용*/
        //정적영역안에서는 맴버변수를 사용하지 못한다.
        /*생성에 중요한 로직이 담겨있을 때는 만드는 것이 특히 좋다.*/
        Order order = new Order();
        order.delivery=delivery;//이런식이 더 좋다. setter의 제한
        order.setMember(member);
        for(OrderItem orderItem:orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
    //비지니스 로직//
    public void cancel(){
        if(delivery.getStatus()==DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem:orderItems){
            orderItem.cancel();
        }
    }
    //조회로직
    public int getTotalPrice(){
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }

}
