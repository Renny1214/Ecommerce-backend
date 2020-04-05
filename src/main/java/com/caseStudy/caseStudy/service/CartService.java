package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.CartRepository;
import com.caseStudy.caseStudy.models.Cart;
import com.caseStudy.caseStudy.models.products;
import com.caseStudy.caseStudy.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    UserService userService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    public ArrayList<Cart> getCartFromCurrentUser(Principal principal) {
        Optional<users> user = userService.getByEmail(principal.getName());
        ArrayList<Cart> cart = cartRepository.findAllByUser(user);
        return cart;
    }

    public ArrayList<Cart> getEmail(Principal principal) {
        String email = principal.getName();
        Optional<users> user = userService.getByEmail(email);
        return cartRepository.findAllByUser(user);
    }

    public String addItemToCart(Principal principal, Long productId) {

        Optional<products> product = productService.getById(productId);
        Optional<users> user = userService.getByEmail((principal.getName()));
        ArrayList<Cart> cart = getCartFromCurrentUser(principal);

        for (int i = 0; i < cart.size(); i++) {
            Cart cartObject = cart.get(i);
            if (cartObject.getProducts() == product.get()) {
                cartObject.setQuantity(cartObject.getQuantity() + 1);
                cartRepository.save(cartObject);
                return "\"Quantity increased\"";
            }
        }

        Cart cartObject = new Cart();
        cartObject.setQuantity(1);
        cartObject.setProducts(product.get());
        cartObject.setUser(user.get());

        cartRepository.save(cartObject);
        return "\"item added to cart\"";
    }

    @Transactional
    public String deleteItemFromCart(Long id, Principal principal) {
        Optional<products> product = productService.getById(id);
        Optional<users> user = userService.getByEmail(principal.getName());
        cartRepository.deleteByUserAndProducts(user, product);
        return "\"deletion completed\"";
    }

    public String incrementInCart(Long id, Principal principal) {
        Optional<users> user = userService.getByEmail(principal.getName());
        Optional<products> product = productService.getById(id);
        Optional<Cart> cart = cartRepository.findByUserAndProducts(user, product);
        cart.get().setQuantity(cart.get().getQuantity() + 1);
        cartRepository.save(cart.get());
        return "\"increased quantity\"";
    }
    public String decrementInCart(Long id, Principal principal) {
        Optional<users> user = userService.getByEmail(principal.getName());
        Optional<products> product = productService.getById(id);
        Optional<Cart> cart= cartRepository.findByUserAndProducts(user,product);
        cart.get().setQuantity(cart.get().getQuantity()-1);
        cartRepository.save(cart.get());
        return "\"decreased quantity\"";
    }

    public void deleteItemFromCartObject(Cart cart)
    {
        cartRepository.delete(cart);
    }
}
