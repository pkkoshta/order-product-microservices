package com.prashant.repository;

import com.prashant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByPid(Integer pid);

    Product findByName(String name);
}
