package ru.geekbrains.springboot.Products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboot.Products.model.Product;
import ru.geekbrains.springboot.Products.services.ProductServices;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices productServices;

    @GetMapping
    private String showAll(Model model,
                           @RequestParam(required = false, name = "min_cost") Double minCost,
                           @RequestParam(required = false, name = "max_cost") Double maxCost
    ){
        model.addAttribute("products", productServices.findAll(minCost, maxCost));
        return "products";
    }

    @GetMapping("/test")
    @ResponseBody
    public Product getById(@RequestParam Long id){
        return productServices.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductByid(@PathVariable Long id){
        productServices.deleteById(id);
        return "redirect:/products";
    }
}
