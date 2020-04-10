package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.CartRepository;
import com.caseStudy.caseStudy.doa.HistoryRepository;
import com.caseStudy.caseStudy.doa.UserRepository;
import com.caseStudy.caseStudy.models.Cart;
import com.caseStudy.caseStudy.models.history;
import com.caseStudy.caseStudy.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    public ArrayList<history> getHistory(Principal principal){
        Optional<users> user=userRepository.findByEmail(principal.getName());
        return user.map(users -> historyRepository.findAllByUser(users)).orElse(null);
    }

    public boolean addProductToHistory(Principal principal){
        Optional<users> user=userRepository.findByEmail(principal.getName());
        history historyObject=new history();

        if(user.isPresent()){
            ArrayList<Cart> carts=cartRepository.findAllByUser(user.get());
            for(int i=0;i<carts.size();i++){
                Cart cart=carts.get(i);

                historyObject.setProducts(cart.getProducts());
                historyObject.setQuantity(cart.getQuantity());
                historyObject.setUser(user.get());
                historyObject.setDate(new Date());

                historyRepository.save(historyObject);
            }

            return true;
        }

        return false;
    }
}
