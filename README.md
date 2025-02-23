# 📦 Inventory Management System (Spring Boot)

This is a simple **Inventory Management System** built with **Spring Boot, MySQL, JPA, and REST APIs**.  
It allows users to **add, retrieve, update, and delete inventory items** while managing stock quantities.


## Features
✔️ Add new items to the inventory  
✔️ Retrieve all items or a specific item by ID  
✔️ Update item details (name, price, quantity)  
✔️ Increase or decrease item stock  
✔️ Delete an item  
✔️ Exception handling for missing items  
✔️ API tested with **JUnit & MockMvc**  

---

## 🛠️ TechStack
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA (Hibernate)**
- **MySQL**
- **Maven**
- **JUnit & Mockito (for testing)**

---

##  Setup Instructions

### ** Clone the Repository**
```sh
git clone https://github.com/geeta-kukreja/inventory-management.git
cd inventory-management

### ** Configure MySQL**
Create a database:
```sh
CREATE DATABASE inventory_db;

Update src/main/resources/application.properties:
properties
```sh
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=root
spring.datasource.password=

### ** Build & Run the Project**
```sh
mvn spring-boot:run
The application runs on: http://localhost:8080
### ** Example in api.http file to test and src/tests directory**






