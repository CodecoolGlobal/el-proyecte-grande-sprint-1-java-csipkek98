package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.OrdersRepository;
import com.WizardsOfTheCoast.magic.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public void addOrder(Orders order){
        ordersRepository.save(order);
    }

    public Orders getOrderById(Long orderId){
        return ordersRepository.findById(orderId).get();
    }
}
