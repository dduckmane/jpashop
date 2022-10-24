package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    //불변객체
    private String city;
    private String street;
    private String zipcode;

    //jpa에서 엔티티는 기본생성자가 필수--> 프록시와 리플렉션


    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}