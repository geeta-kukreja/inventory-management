package com.example.inventory_management.controller;

import com.example.inventory_management.exception.GlobalExceptionHandler;
import com.example.inventory_management.exception.ItemNotFoundException;
import com.example.inventory_management.model.Item;
import com.example.inventory_management.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ItemControllerTest {

    private MockMvc mockMvc; // Simulates HTTP requests

    @Mock
    private ItemService itemService; // Mock the service layer

    @InjectMocks
    private ItemController itemController; // Inject mock into the controller

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Add global exception handler
                .build();

    }

    // Test adding an item
    @Test
    void testAddItem() throws Exception {
        Item item = new Item("Laptop", 10, 1200.00);
        when(itemService.addItem(any(Item.class))).thenReturn(item);

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Laptop\", \"quantity\": 10, \"price\": 1200.00}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));
    }

    // Test retrieving all items
    @Test
    void testGetAllItems() throws Exception {
        Item item1 = new Item("Laptop", 10, 1200.00);
        Item item2 = new Item("Mouse", 20, 25.00);

        when(itemService.getAllItems()).thenReturn(Arrays.asList(item1, item2));

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[1].name").value("Mouse"));
    }

    // Test retrieving an item by ID
    @Test
    void testGetItemById_ItemExists() throws Exception {
        Item item = new Item("Keyboard", 5, 50.00);
        when(itemService.getItemById(1L)).thenReturn(item);

        mockMvc.perform(get("/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Keyboard"));
    }

    //  Test retrieving an item that does not exist (expect 404)
    @Test
    void testGetItemById_ItemNotFound() throws Exception {
        when(itemService.getItemById(1L)).thenThrow(new ItemNotFoundException(1L));

        mockMvc.perform(get("/items/1"))
                .andExpect(status().isNotFound());
    }

    // Test for increasing item quantity
    @Test
    void testIncreaseItemQuantity() throws Exception {
        Item item = new Item("Laptop", 10, 1200.00);
        item.setQuantity(15); // Simulating increase

        when(itemService.increaseQuantity(1L, 5)).thenReturn(item);

        mockMvc.perform(put("/items/1/increase?amount=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(15));
    }

    // Test deleting an item
    @Test
    void testDeleteItem() throws Exception {
        when(itemService.deleteItem(1L)).thenReturn(true); // Mock successful deletion

        mockMvc.perform(delete("/items/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Item deleted successfully"));

        when(itemService.deleteItem(2L)).thenReturn(false); // Mock item not found

        mockMvc.perform(delete("/items/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Item not found"));
    }

}
