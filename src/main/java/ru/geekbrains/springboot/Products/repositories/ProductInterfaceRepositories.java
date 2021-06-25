package ru.geekbrains.springboot.Products.repositories;

import ru.geekbrains.springboot.Products.model.Product;

public interface ProductInterfaceRepositories {
    void createdProduct(Product product);

    void readProduct(Long id);

    void updateProduct(Product product);

    void deletedProduct(Long id);
}
