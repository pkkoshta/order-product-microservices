package com.prashant.orderservice.controller;

import com.prashant.orderservice.model.Order;
import com.prashant.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@NoArgsConstructor
public class OrderController {

    @Value("${spring.prashant}")
    private String name;

    private OrderService orderService;

    @GetMapping("/hello")
    public String getHello(){
        System.out.println("name = " + name);
        return "Hi welcome to order service..";
    }

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/{pid}")
    public ResponseEntity<Order> getProductId(@PathVariable Integer pid){
        return new ResponseEntity<>(orderService.orderByProductId(pid), HttpStatus.ACCEPTED);
    }




}
