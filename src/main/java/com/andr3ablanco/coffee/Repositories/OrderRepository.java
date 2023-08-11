package com.andr3ablanco.coffee.Repositories;

import com.andr3ablanco.coffee.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
