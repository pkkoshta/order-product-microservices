package com.prashant.controller;

import com.prashant.dto.ProductDTO;
import com.prashant.model.Product;
import com.prashant.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/hello")
    public String getHello(){
        return "Hi welcome to product service..";
    }

    @PutMapping
    public Product editProduct(@RequestBody ProductDTO productDTO){
        Product product =
                new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getQty(), productDTO.getAmount());
        return productService.updateProduct(product);
    }

    @GetMapping("/{pid}")
    public Product getById(@PathVariable int pid){
        return productService.getByPid(pid);
    }

    @DeleteMapping("/{pid}")
    public void deleteProduct(@PathVariable int pid){
        productService.deleteByPid(pid);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDTO productDTO){
        Product product =
                new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getQty(), productDTO.getAmount());
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> products(){
        return productService.products();
    }
}
