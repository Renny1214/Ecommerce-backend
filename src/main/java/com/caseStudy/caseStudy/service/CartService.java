package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.CartRepository;
import com.caseStudy.caseStudy.doa.UserRepository;
import com.caseStudy.caseStudy.doa.product.ProductRepository;
import com.caseStudy.caseStudy.models.Cart;
import com.caseStudy.caseStudy.models.products.Product;
import com.caseStudy.caseStudy.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public boolean addItemToCart(Principal principal,Long productId){
        Optional<Product> product=productRepository.findById(productId);
        Optional<users> user=userRepository.findByEmail(principal.getName());

        if(user.isPresent() && product.isPresent()){
            if(!cartRepository.findByUserAndProducts(user.get(),product.get()).isPresent()){
                Cart cart=new Cart();
                cart.setProducts(product.get());
                cart.setQuantity(0);
                cart.setUser(user.get());

                return true;
            }
        }

        return false;
    }

    public ArrayList<Cart> getCart(Principal principal){
        Optional<users> users=userRepository.findByEmail(principal.getName());
        return users.map(value -> cartRepository.findAllByUser(value)).orElse(null);
    }

    public boolean deleteItemFromCart(Long productId,Principal principal){
        Optional<users> users=userRepository.findByEmail(principal.getName());
        Optional<Product> product=productRepository.findById(productId);

        if(users.isPresent() && product.isPresent()){
            cartRepository.deleteByUserAndProducts(users.get(),product.get());

            return true;
        }

        return false;
    }

    public String incrementOrDecrementInCart(Long productId,Principal principal,String action){
        Optional<users> users=userRepository.findByEmail(principal.getName());
        Optional<Product> product=productRepository.findById(productId);

        if(users.isPresent() && product.isPresent()){
            Optional<Cart> cart=cartRepository.findByUserAndProducts(users.get(),product.get());

            if(cart.isPresent()){
                Cart temp=cart.get();

                if(action.toLowerCase().equals("increment")){
                    temp.setQuantity(temp.getQuantity()+1);

                    cartRepository.save(temp);
                }
                else{
                    if(temp.getQuantity() > 0){
                        temp.setQuantity(temp.getQuantity()-1);

                        cartRepository.save(temp);
                    }
                }

                return temp.getQuantity()+"";
            }
        }

        return null;
    }
}
