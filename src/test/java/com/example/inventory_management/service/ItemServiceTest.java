package com.example.inventory_management.service;

import com.example.inventory_management.model.Item;
import com.example.inventory_management.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository; // Mockingg the repository

    @InjectMocks
    private ItemService itemService; // Inject the mock into service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testAddItem() {
        Item item = new Item("Mouse", 10, 20.00);
        when(itemRepository.save(any(Item.class))).thenReturn(item); // Mock save()

        Item savedItem = itemService.addItem(item);
        assertNotNull(savedItem);
        assertEquals("Mouse", savedItem.getName());
    }

    @Test
    void testGetItemById_ItemExists() {
        Item item = new Item("Keyboard", 5, 50.00);
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Item foundItem = itemService.getItemById(1L);
        assertNotNull(foundItem);
        assertEquals("Keyboard", foundItem.getName());
    }

    @Test
    void testGetItemById_ItemNotFound() {
        when(itemRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> itemService.getItemById(1L));
        assertEquals("Item with ID 1 not found.", exception.getMessage());
    }
}
