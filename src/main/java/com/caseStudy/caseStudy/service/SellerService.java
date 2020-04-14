package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.MaintainersRepository;
import com.caseStudy.caseStudy.doa.SellerRepository;
import com.caseStudy.caseStudy.models.Maintainers;
import com.caseStudy.caseStudy.models.Sellers;
import com.caseStudy.caseStudy.service.resource.manipulation.ResourceManipulation;
import com.caseStudy.caseStudy.service.threads.MailThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    private MaintainersRepository maintainersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean signUp(Sellers sellers) throws IOException {
        if(!sellerRepository.findByEmail(sellers.getEmail()).isPresent()){
            sellers.setActive(true);
            sellers.setPassword(passwordEncoder.encode(sellers.getPassword()));
            sellers.setSalesMade(0);

            sellerRepository.save(sellers);
            sendEmailToAllMaintainers(sellerRepository.findByEmail(sellers.getEmail()));

            return true;
        }

        return false;
    }
    private void sendEmailToAllMaintainers(Optional<Sellers> sellers) throws IOException {
        ArrayList<Maintainers> maintainers=(ArrayList<Maintainers>)maintainersRepository
                .findAll();

        final String subject="New Seller added";
        final String fileName="/sellerConfirmationMail.html";
        StringBuilder stringBuilder= ResourceManipulation.getResource(fileName);

        for(int i=0;i<maintainers.size();i++){
            MailThread mailThread=new MailThread();
            mailThread.setSendingMail(maintainers.get(i).getEmail(),subject,stringBuilder.toString());

            Thread thread=new Thread(mailThread);
            thread.start();
        }
    }

    public Sellers getInfo(Principal principal){
        return sellerRepository.findByEmail(principal.getName()).get();
    }

}
