package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }
//    @Transactional
//    public void updateItem(Long itemId, String name,int price,int stockQuantity){
//        Item findItem = itemRepository.findOne(itemId);
//        findItem.setName(name);
//        findItem.setStockQuantity(price);
//        findItem.setPrice(stockQuantity);
//    }
    @Transactional
    public void updateItem(Long itemId, UpdateItemDto updateItemDto){
        Item findItem = itemRepository.findOne(itemId);
        /*findItem.change(name,price,quantity)이런식으로 setter의 사용을 줄여라*/
        findItem.setName(updateItemDto.getName());
        findItem.setStockQuantity(updateItemDto.getStockQuantity());
        findItem.setPrice(updateItemDto.getPrice());
    }
    public List<Item>findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

}
