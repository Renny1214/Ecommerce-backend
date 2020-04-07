package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.SellerRepository;
import com.caseStudy.caseStudy.models.Sellers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean signUp(Sellers sellers){
        if(!sellerRepository.findByEmail(sellers.getEmail()).isPresent()){
            sellers.setActive(true);
            sellers.setPassword(passwordEncoder.encode(sellers.getPassword()));

            sellerRepository.save(sellers);
            return true;
        }

        return false;
    }

    public Sellers getInfo(Principal principal){
        return sellerRepository.findByEmail(principal.getName()).get();
    }

}
