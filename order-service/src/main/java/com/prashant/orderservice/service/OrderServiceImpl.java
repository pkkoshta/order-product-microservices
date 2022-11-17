package com.prashant.orderservice.service;

import com.prashant.orderservice.dto.Product;
import com.prashant.orderservice.model.Order;
import com.prashant.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class OrderServiceImpl implements OrderService{

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
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);

        Product productDTO = restTemplate
                .getForObject("http://product-service/product/"+pid, Product.class);
        System.out.println(productDTO);
        Order order = new Order();
        order.setProductId(productDTO.getPid());
        order.setProductName(productDTO.getName());
        order.setAmount(productDTO.getAmount());
        order.setStatus("CREATED");

        return  orderRepository.save(order);

    }
}
