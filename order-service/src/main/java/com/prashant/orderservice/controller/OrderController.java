package com.prashant.orderservice.controller;

import com.prashant.orderservice.model.Order;
import com.prashant.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Value("${spring.prashant}")
    private String name;
@Autowired
    private OrderService orderService;

    @GetMapping("/hello")
    public String getHello(){
        logger.info("Inside hello call");
        System.out.println("name = " + name);
        logger.info("After hello call");
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
