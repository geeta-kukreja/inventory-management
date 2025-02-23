package com.example.inventory_management.controller;

import com.example.inventory_management.model.Item;
import com.example.inventory_management.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Add an item
    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    // Get all items
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // Get item by ID
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    // Update an item
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item newItem) {
        return itemService.updateItem(id, newItem);
    }

    // Delete an item
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        boolean deleted = itemService.deleteItem(id);
        return deleted ? "Item deleted successfully" : "Item not found";
    }

    //  Increase Item Quantity
    @PutMapping("/{id}/increase")
    public Item increaseQuantity(@PathVariable Long id, @RequestParam int amount) {
        return itemService.increaseQuantity(id, amount);
    }

    //  Decrease Item Quantity
    @PutMapping("/{id}/decrease")
    public Item decreaseQuantity(@PathVariable Long id, @RequestParam int amount) {
        return itemService.decreaseQuantity(id, amount);
    }

    // Check if Item is In Stock
    @GetMapping("/{id}/in-stock")
    public boolean isInStock(@PathVariable Long id) {
        return itemService.isInStock(id);
    }
}
