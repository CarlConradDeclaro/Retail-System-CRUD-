# Retail System
<h4 align="center">Visitor's count 👀</h4>
<p align="center"><img src="https://profile-counter.glitch.me/Retail-System-CRUD-/count.svg" alt="CarlConradDeclaro :: Visitor's Count" /></p>

## Introduction
The Retail System is a Java-based application designed to manage products in a retail store. It provides essential functionality for adding, deleting, creating, and updating product information.

## Features
- **Add Product**: Allows users to add new products to the system, including details such as product name, price, and quantity.

- **Delete Product**: Enables the removal of products from the system based on their unique product ID.

- **Create Product**: Offers the ability to create a new product with specified attributes and add it to the system.

- **Update Product**: Allows users to modify existing product information, including price and quantity.

## Usage
Here's how you can use the Retail System:

1. **Adding a Product**:
   - Provide the product name, price, and quantity.
   - The system assigns a unique product ID.
   - The product is added to the system.

2. **Deleting a Product**:
   - Specify the product ID of the product to be removed.
   - The product is removed from the system.

3. **Creating a Product**:
   - Provide all necessary product information, including product name, price, and quantity.
   - The product is added to the system.

4. **Updating a Product**:
   - Specify the product ID of the product to be updated.
   - Modify the product's price and/or quantity.
   - The product information is updated in the system.

## Example Code
```java
// Sample code to add a product
Product newProduct = new Product("Sample Product", 19.99, 50);
retailSystem.addProduct(newProduct);

// Sample code to delete a product
int productIdToDelete = 123;
retailSystem.deleteProduct(productIdToDelete);

// Sample code to create a product
Product createdProduct = retailSystem.createProduct("New Product", 24.99, 100);

// Sample code to update a product
int productIdToUpdate = 456;
retailSystem.updateProduct(productIdToUpdate, 29.99, 75);
