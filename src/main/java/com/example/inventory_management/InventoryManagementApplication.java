package com.example.inventory_management;

import com.example.inventory_management.model.Item;
import com.example.inventory_management.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(ItemRepository itemRepository) {
		return (args) -> {
			itemRepository.save(new Item("Chair", 10, 60.00));
			itemRepository.save(new Item("Mouse", 50, 25.99));
			itemRepository.save(new Item("Keyboard", 30, 49.99));
			itemRepository.save(new Item("Laptop", 30, 1111.11));
			System.out.println("Sample data inserted!");
		};
	}
}
