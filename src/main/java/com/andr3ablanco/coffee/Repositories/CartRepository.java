package com.andr3ablanco.coffee.Repositories;

import com.andr3ablanco.coffee.Entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Long> {

    @Modifying
    @Query("UPDATE Cart c SET c.productQty = :productQty, c.subtotal = :subtotal WHERE c.id = :id")
    void updateItemInCart(@Param("id") Long id, @Param("productQty") int productQty, @Param("subtotal") double subtotal);

    @Query("SELECT SUM(c.subtotal) FROM Cart c")
    double getTotal();

}
