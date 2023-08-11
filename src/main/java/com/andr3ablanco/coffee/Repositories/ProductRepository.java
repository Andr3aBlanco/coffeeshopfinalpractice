package com.andr3ablanco.coffee.Repositories;

import com.andr3ablanco.coffee.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
