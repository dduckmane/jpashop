package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * toOne관계*/
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order>orderV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto>orderV2() {
       return orderRepository.findAllByString(new OrderSearch())
                .stream().map(SimpleOrderDto::new)
                .collect(toList());
       //원래는 한번더 객체로 감싸야한다.
    }
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto>orderV3() {
        return orderRepository.findAllWithMemberDelivery()
                .stream().map(SimpleOrderDto::new)
                .collect(toList());
    }
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto>orderV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

 @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        //생성자도 괜찮은 방법이다.
        public SimpleOrderDto(Order order) {
            orderId=order.getId();
            name=order.getMember().getName();
            orderDate=order.getOrderDate();
            orderStatus=order.getStatus();
            address=order.getDelivery().getAddress();
        }
    }
    }
