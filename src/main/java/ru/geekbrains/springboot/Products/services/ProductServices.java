package ru.geekbrains.springboot.Products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.springboot.Products.exception.ResourceNotFoundException;
import ru.geekbrains.springboot.Products.model.Product;
import ru.geekbrains.springboot.Products.repositories.ProductRepositories;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServices {

    @Autowired
    private ProductRepositories productRepositories;

    public Product findById(Long id) {
        return productRepositories.findById(id).orElseThrow(() -> new ResourceNotFoundException("Указанный продукт под номером " + id + " не обнаружен"));
    }

    public List<Product> findAll() {
        return productRepositories.findAll();
    }

    public List<Product> findAll(Double minCost, Double maxCost) {
        List<Product> out = findAll();
        if (minCost != null) {
            out = out.stream().filter(p -> p.getCost() >= minCost).collect(Collectors.toList());
        }
        if (maxCost != null) {
            out = out.stream().filter(p -> p.getCost() <= maxCost).collect(Collectors.toList());
        }
        return out;
    }

    public Product saveOrUpdate(Product p){
        return productRepositories.saveOrUpdate(p);
    }

    public void deleteById(Long id){
        productRepositories.deletedProduct(id);
    }
}
