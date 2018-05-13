package com.baoxina.statemachine.config;

import javax.annotation.Resource;

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

	@Resource(name="orderPersistStateChangeListener")
	private OrderPersistStateChangeListener orderPersistStateChangeListener;
	
    @Autowired
    @Qualifier("orderStateMachine")
    private StateMachine<OrderStatus, OrderEvents> orderStateMachine;


    @Bean
    public OrderStateService persist() {
        PersistStateMachineHandler handler = persistStateMachineHandler();
        handler.addPersistStateChangeListener(orderPersistStateChangeListener);
        return new OrderStateService(handler);
    }

    @Bean
    public PersistStateMachineHandler persistStateMachineHandler() {
        return new PersistStateMachineHandler(orderStateMachine);
    }


}