package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.MaintainersRepository;
import com.caseStudy.caseStudy.doa.SellerRepository;
import com.caseStudy.caseStudy.models.Maintainers;
import com.caseStudy.caseStudy.models.Sellers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class MaintainerService {

    @Autowired
    private MaintainersRepository maintainersRepository;
    @Autowired
    private SellerRepository sellerRepository;

    public boolean signUp(Maintainers maintainers){
        Optional<Maintainers> maintainers1=maintainersRepository.findByEmail(maintainers.getEmail());

        if(!maintainers1.isPresent()){
            maintainers.setPassword(new BCryptPasswordEncoder().encode(maintainers.getPassword()));
            maintainersRepository.save(maintainers);

            return true;
        }

        return false;
    }

    public List<Sellers> getSellers(Principal principal){
        Optional<Maintainers> maintainer=maintainersRepository.findByEmail(principal.getName());

        if(maintainer.isPresent()){
            return (List<Sellers>)sellerRepository.findAll();
        }

        return null;
    }

    public boolean setPaid(Long id,Principal principal){
        Optional<Maintainers> maintainer=maintainersRepository.findByEmail(principal.getName());

        if(maintainer.isPresent()){
            Optional<Sellers> sellers=sellerRepository.findById(id);

            if(sellers.isPresent()){
                Sellers sellers1=sellers.get();
                sellers1.setSalesMade(0);

                sellerRepository.save(sellers1);
                return true;
            }
        }

        return false;
    }
}
