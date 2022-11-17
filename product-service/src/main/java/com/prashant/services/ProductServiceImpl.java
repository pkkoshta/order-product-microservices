package com.prashant.services;

import com.prashant.exception.ProductNotFoundException;
import com.prashant.model.Product;
import com.prashant.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;


    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        System.out.println(productRepository.findByName(product.getName()));
       Product productOptional = productRepository.findByName(product.getName());
        System.out.println("productOptional.get() = " + productOptional);
        if (productOptional != null) {
            productOptional.setAmount(product.getAmount());
            productOptional.setQty(product.getQty());
            productOptional.setDescription(product.getDescription());
            productOptional.setName(product.getDescription());

            return productRepository.save(productOptional);
        } else
            throw new ProductNotFoundException("Product not found..");
    }

    @Override
    public Product getByPid(int pid) {
        return productRepository.findById(pid)
                .orElseThrow(() -> new ProductNotFoundException("Product not found..."));
    }

    @Override
    public void deleteByPid(int pid) {
         productRepository.deleteById(pid);

    }

    @Override
    public List<Product> products() {
        return productRepository.findAll();
    }
}
