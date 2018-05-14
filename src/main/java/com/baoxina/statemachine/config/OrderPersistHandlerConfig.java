package com.baoxina.statemachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;

import com.baoxina.statemachine.PersistStateMachineHandler;
import com.baoxina.statemachine.entity.OrderEvents;
import com.baoxina.statemachine.entity.OrderStatus;

@Configuration
public class OrderPersistHandlerConfig {

	
    @Autowired
    @Qualifier("orderStateMachine")
    private StateMachine<OrderStatus, OrderEvents> orderStateMachine;

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler() {
        return new PersistStateMachineHandler(orderStateMachine);
    }


}