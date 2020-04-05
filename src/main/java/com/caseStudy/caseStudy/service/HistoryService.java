package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.HistoryRepository;
import com.caseStudy.caseStudy.models.Cart;
import com.caseStudy.caseStudy.models.history;
import com.caseStudy.caseStudy.models.products;
import com.caseStudy.caseStudy.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    UserService userService;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    public ArrayList<history> getHistoryFromCurrentUser(Principal principal) {
        Optional<users> user = userService.getByEmail(principal.getName());
        ArrayList<history> history = historyRepository.findAllByUser(user);
        return history;
    }

    public String addListToHistory(Principal principal) {
        ArrayList<Cart> cart = cartService.getEmail(principal);
        Optional<users> user = userService.getByEmail((principal.getName()));
        ArrayList<products> products = new ArrayList<>();

        for (int i = 0; i < cart.size(); i++) {
            products.add(cart.get(i).getProducts());
            cartService.deleteItemFromCartObject(cart.get(i));
        }
        int i=0;
        int j=0;
       while(i<products.size()&&j<cart.size()){
            history history = new history();
            Date date = new Date();
            history.setDate(date);
            history.setUser(user.get());
            history.setProducts(products.get(i));
            history.setQuantity(cart.get(j).getQuantity());
            historyRepository.save(history);
            i++;
            j++;
        }

        return "\"history done!\"";
    }


}
