package ru.geekbrains.springboot.Products.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.springboot.Products.model.Product;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ProductRepositories implements ProductInterfaceRepositories {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "aaa", 17.5),
                new Product(2L, "bbb", 10.0),
                new Product(3L, "ccc", 33.3),
                new Product(4L, "ddd", 34.1),
                new Product(5L, "eee", 100.25),
                new Product(6L, "fff", 66.6),
                new Product(7L, "ggg", 22.3),
                new Product(8L, "hhh", 11.3)
        ));
    }

    @Override
    public void createdProduct(Product product) {

    }

    @Override
    public void readProduct(Long id) {
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deletedProduct(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public Product saveOrUpdate(Product p) {
        if (p.getId() != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(p.getId())) {
                    products.set(i, p);
                    return p;
                }
            }
        }

        Long newId = products.stream().mapToLong(Product::getId).max().orElse(0L) + 1L;
        p.setId(newId);
        products.add(p);
        return p;
    }

    public List<Product> get() {
        return products;
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findById(Long id){
        return get().stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
