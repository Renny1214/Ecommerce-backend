package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.SellerRepository;
import com.caseStudy.caseStudy.models.Sellers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public boolean signUp(Sellers sellers){
        if(!sellerRepository.findByEmail(sellers.getEmail()).isPresent()){
            sellers.setActive(true);

            sellerRepository.save(sellers);
            return true;
        }

        return false;
    }

}
