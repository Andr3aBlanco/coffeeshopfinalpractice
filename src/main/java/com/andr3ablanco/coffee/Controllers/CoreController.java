package com.andr3ablanco.coffee.Controllers;


import com.andr3ablanco.coffee.Entities.Cart;
import com.andr3ablanco.coffee.Entities.Order;
import com.andr3ablanco.coffee.Entities.Product;
import com.andr3ablanco.coffee.Repositories.CartRepository;
import com.andr3ablanco.coffee.Repositories.OrderRepository;
import com.andr3ablanco.coffee.Repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CoreController {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;



    @GetMapping("/")
    public String showHome(Model model){

        List<Product> theProducts = productRepository.findAll();
        model.addAttribute("products", theProducts);

        return "home";
    }


    @GetMapping("/index")
    public String showHome2(Model model){

        List<Product> theProducts = productRepository.findAll();
        model.addAttribute("products", theProducts);

        return "home";
    }

    @GetMapping("/cart")
    public String showCart(Model model){

        List<Cart> theProducts = cartRepository.findAll();
        model.addAttribute("items", theProducts);

        return "cart";
    }


    // ADD PRODUCT TO CART
    @PostMapping("/addProductToCart") // This is the name that goes in the button not the one in the method
    public String addProductToCart(Model model, @RequestParam("productID") Long productID, @RequestParam("productName") String productName , @RequestParam("qty") int qty, HttpSession session ) {
        // calculate subtotal
            //fin product price
        Optional<Product> product = productRepository.findById(productID);

        double unitPrice = product.get().getProductPrice();
        double subtotal = unitPrice*qty;


        Cart newCartItem = new Cart(null, productID, productName, qty, subtotal);

        cartRepository.save(newCartItem);

        return "redirect:/index"; // I need this for redirect but it's the same as /
    }



    @GetMapping("/deleteItem") // This is the name that goes in the button not the one in the method
    public String deleteItem(Long id){

        cartRepository.deleteById(id);

        return "redirect:/cart"; // I need this for redirect but it's the same as /
    }


    @PostMapping("/editItem") // This is the name that goes in the button not the one in the method
    @Transactional
    public String editItem(Model model, @RequestParam("itemID") Long itemID,  @RequestParam("productID") Long productID, @RequestParam("qty") int qty, HttpSession session ) {
        // calculate subtotal
        //fin product price
        Optional<Product> product = productRepository.findById(productID);
        Optional<Cart> item = cartRepository.findById(itemID);

        double unitPrice = product.get().getProductPrice();
        double subtotal = unitPrice*qty;


        cartRepository.updateItemInCart(item.get().getId(), qty, subtotal);

        return "redirect:/index"; // I need this for redirect but it's the same as /
    }


    @GetMapping("/processOrder") // This is the name that goes in the button not the one in the method
    @Transactional
    public String processOrder(Model model, HttpSession session ) {
        // calculate subtotal
        //fin product price
        List<Cart> product = cartRepository.findAll();

        double total = cartRepository.getTotal();

        Order newOrder = new Order(null, new Date(), total);

        orderRepository.save(newOrder);




        return "redirect:/index"; // I need this for redirect but it's the same as /
    }



}
