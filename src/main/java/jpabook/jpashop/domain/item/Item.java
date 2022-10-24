package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Getter
@Setter
public abstract class Item {//추상클래스
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    @ManyToMany(mappedBy = "items")
    private List<Category>categories=new ArrayList<>();

    //비지니스 로직//
    /*stockQuantity를 여기서 가지고 있기 때문에 여기서 로직을 넣어주면 응집도가 높다.*/
    public void addStock(int quantity){
        this.stockQuantity+=quantity;
    }
    public void removeStock(int quantity){
        int resStock=this.stockQuantity-quantity;
        if(resStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity=resStock;
    }
}
