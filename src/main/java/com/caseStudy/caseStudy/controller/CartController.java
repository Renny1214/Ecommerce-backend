package com.caseStudy.caseStudy.controller;

import com.caseStudy.caseStudy.models.Cart;
import com.caseStudy.caseStudy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping()
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping(path="/cart")
    public ArrayList<Cart> getCart(Principal principal)
    {
        return cartService.getEmail(principal);
    }
    @GetMapping(path="/cart/addItem/productId/{id}")
    public String addItemToCart(@PathVariable("id") Long productId,Principal principal)
    {
        return cartService.addItemToCart(principal,productId);
    }

    @GetMapping(path="/cart/deleteItem/productId/{id}")
    public String deleteItemFromCart(@PathVariable("id") Long id,Principal principal)
    {
        return cartService.deleteItemFromCart(id,principal);
    }

    @GetMapping(path="/cart/increment/productId/{id}")
    public String incrementInCart(@PathVariable("id") Long id,Principal principal)
    {
        return cartService.incrementInCart(id,principal);
    }

    @GetMapping(path="/cart/decrement/productId/{id}")
    public String decrementInCart(@PathVariable("id") Long id,Principal principal)
    {
        return cartService.decrementInCart(id,principal);
    }
}
