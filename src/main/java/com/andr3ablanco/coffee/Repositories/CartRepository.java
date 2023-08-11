package com.andr3ablanco.coffee.Repositories;

import com.andr3ablanco.coffee.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
