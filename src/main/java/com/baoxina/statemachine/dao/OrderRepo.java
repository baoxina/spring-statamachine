package com.baoxina.statemachine.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baoxina.statemachine.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {


    Order findByOrderId(Integer order);
}