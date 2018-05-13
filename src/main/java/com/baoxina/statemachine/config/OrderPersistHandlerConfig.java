package com.baoxina.statemachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

import com.baoxina.statemachine.OrderPersistStateChangeListener;
import com.baoxina.statemachine.PersistStateMachineHandler;
import com.baoxina.statemachine.entity.OrderEvents;
import com.baoxina.statemachine.entity.OrderStatus;
import com.baoxina.statemachine.service.OrderStateService;

@Configuration
public class OrderPersistHandlerConfig {

    @Autowired
    @Qualifier("orderStateMachine")
    private StateMachine<OrderStatus, OrderEvents> orderStateMachine;


    @Bean
    public OrderStateService persist() {
        PersistStateMachineHandler handler = persistStateMachineHandler();
        handler.addPersistStateChangeListener(persistStateChangeListener());
        return new OrderStateService(handler);
    }

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler() {
        return new PersistStateMachineHandler(orderStateMachine);
    }

    @Bean
    public OrderPersistStateChangeListener persistStateChangeListener(){
        return new OrderPersistStateChangeListener();
    }


}