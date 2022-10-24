package jpabook.jpashop.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
public class UpdateItemDto {
    private String name;
    private int price;
    private int stockQuantity;

    private UpdateItemDto() {
    }

    public static UpdateItemDto createDto(String name, int price, int stockQuantity){
        UpdateItemDto updateItemDto = new UpdateItemDto();
        updateItemDto.name=name;
        updateItemDto.price=price;
        updateItemDto.stockQuantity=stockQuantity;
        return updateItemDto;
    }

}
