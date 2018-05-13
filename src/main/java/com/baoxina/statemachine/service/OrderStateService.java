package com.baoxina.statemachine.service;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.baoxina.statemachine.PersistStateMachineHandler;
import com.baoxina.statemachine.dao.OrderRepo;
import com.baoxina.statemachine.entity.Order;
import com.baoxina.statemachine.entity.OrderEvents;

@Component
public class OrderStateService {

    private PersistStateMachineHandler handler;


    public OrderStateService(PersistStateMachineHandler handler) {
        this.handler = handler;
    }

    @Autowired
    private OrderRepo repo;


    public String listDbEntries() {
        List<Order> orders = repo.findAll();
        StringJoiner sj = new StringJoiner(",");
        for (Order order : orders) {
            sj.add(order.toString());
        }
        return sj.toString();
    }


    public boolean change(int order, OrderEvents event) {
        Order o = repo.findByOrderId(order);
        return handler.handleEventWithState(MessageBuilder.withPayload(event).setHeader("order", order).build(), o.getStatus());
    }

}