package com.example.backservice.service;

import com.example.backservice.model.Item;
import com.example.backservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> buildItemsByString(String entity) {
        List<Item> items = new ArrayList<>();
        entity = entity.replaceAll("\\[","").replaceAll("]", "");
        String[] itemsString = entity.split(",");
        Arrays.stream(itemsString).forEach(i -> {
            String[] fields = i.split("-");
            Item item = new Item();
            item.setItemId(Integer.valueOf(fields[0]));
            item.setQuantity(Integer.valueOf(fields[1]));
            item.setPrice(Double.valueOf(fields[2]));
            items.add(item);
        } );
        return items;
    }

    public void saveAll(List<Item> items) {
        items.forEach(i -> repository.save(i));
    }
}
