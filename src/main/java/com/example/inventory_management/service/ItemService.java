package com.example.inventory_management.service;

import com.example.inventory_management.model.Item;
import com.example.inventory_management.repository.ItemRepository;
import com.example.inventory_management.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Spring service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Add a new item
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    // Get all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Get item by ID
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    // Update an item
    public Item updateItem(Long id, Item newItem) {
        return itemRepository.findById(id).map(item -> {
            item.setName(newItem.getName());
            item.setQuantity(newItem.getQuantity());
            item.setPrice(newItem.getPrice());
            return itemRepository.save(item);
        }).orElseThrow(() -> new ItemNotFoundException(id));
    }

    // Delete an item
    public boolean deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }


    // Increase Item Quantity
    public Item increaseQuantity(Long id, int amount) {
        return itemRepository.findById(id).map(item -> {
            item.setQuantity(item.getQuantity() + amount);
            return itemRepository.save(item);
        }).orElseThrow(() -> new ItemNotFoundException(id));
    }

    // Decrease Item Quantity
    public Item decreaseQuantity(Long id, int amount) {
        return itemRepository.findById(id).map(item -> {
            if (item.getQuantity() < amount) {
                throw new IllegalArgumentException("Not enough stock available.");
            }
            item.setQuantity(item.getQuantity() - amount);
            return itemRepository.save(item);
        }).orElseThrow(() -> new ItemNotFoundException(id));
    }

    // Check if Item is In Stock
    public boolean isInStock(Long id) {
        return itemRepository.findById(id)
                .map(item -> item.getQuantity() > 0)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }
}
