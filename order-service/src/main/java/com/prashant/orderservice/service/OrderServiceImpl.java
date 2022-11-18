package com.prashant.orderservice.service;

import com.prashant.orderservice.controller.OrderController;
import com.prashant.orderservice.dto.Product;
import com.prashant.orderservice.model.Order;
import com.prashant.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderServiceImpl implements OrderService{

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order orderByProductId(int pid) {
        logger.info("Inside orderByProductId "+ pid);
        Product productDTO = restTemplate
                .getForObject("http://product-service/product/"+pid, Product.class);
        System.out.println(productDTO);
        logger.info("Inside orderByProductId {}"+ productDTO);

        Order order = new Order();
        order.setProductId(productDTO.getPid());
        order.setProductName(productDTO.getName());
        order.setAmount(productDTO.getAmount());
        order.setStatus("CREATED");
        logger.error("throw exception");
        return  orderRepository.save(order);

    }
}
