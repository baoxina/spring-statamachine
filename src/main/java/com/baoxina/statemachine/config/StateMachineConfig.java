package com.baoxina.statemachine.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import com.baoxina.statemachine.entity.OrderEvents;
import com.baoxina.statemachine.entity.OrderStatus;

@Configuration
@EnableStateMachine(name="orderStateMachine")
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderEvents> {

	@Override
	public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvents> states) throws Exception {
		states.withStates()
				// 定义初始状态
				.initial(OrderStatus.WAIT_PAYMENT)
				// 定义所有状态集合
				.states(EnumSet.allOf(OrderStatus.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvents> transitions) throws Exception {
		transitions.withExternal().source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER)
				.event(OrderEvents.PAYED).and().withExternal().source(OrderStatus.WAIT_DELIVER)
				.target(OrderStatus.WAIT_RECEIVE).event(OrderEvents.DELIVERY).and().withExternal()
				.source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH).event(OrderEvents.RECEIVED);
	}
}