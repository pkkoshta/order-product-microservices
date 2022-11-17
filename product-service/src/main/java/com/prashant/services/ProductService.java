package com.prashant.services;

import com.prashant.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product updateProduct(Product product);

    Product getByPid(int pid);

    void deleteByPid(int pid);

    List<Product> products();


}
