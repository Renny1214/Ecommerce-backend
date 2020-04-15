package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.MaintainersRepository;
import com.caseStudy.caseStudy.doa.SellerRepository;
import com.caseStudy.caseStudy.models.Maintainers;
import com.caseStudy.caseStudy.models.Sellers;
import com.caseStudy.caseStudy.service.generations.OTP;
import com.caseStudy.caseStudy.service.resource.manipulation.ResourceManipulation;
import com.caseStudy.caseStudy.service.threads.MailThread;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

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
            sellers.setStatus("applied");
            sellers.setPassword(passwordEncoder.encode(sellers.getPassword()));
            sellers.setSalesMade(0);

            sellerRepository.save(sellers);
            sendEmailToAllMaintainers();
            sendSignupInfoToSellers(sellerRepository.findByEmail(sellers.getEmail()).get());

            return true;
        }

        return false;
    }
    private void sendEmailToAllMaintainers() throws IOException {
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
    private void sendSignupInfoToSellers(Sellers sellers) throws IOException{
        final String subject="LittleOak Sign up";
        final String fileName="/appliedSellerMail.html";
        StringBuilder stringBuilder=ResourceManipulation.getResource(fileName);

        MailThread mailThread=new MailThread();
        mailThread
                .setSendingMail(sellers.getEmail(),
                        subject,
                        stringBuilder.toString().replace("{}",sellers.getFirstName()));

        Thread thread=new Thread(mailThread);
        thread.start();
    }

    public Sellers getInfo(Principal principal){
        return sellerRepository.findByEmail(principal.getName()).get();
    }

    public String sendOTP(String json) throws IOException {
        JSONObject jsonObject=new JSONObject(json);
        String fileName="/otpMail.html";
        String subject="OTP for LittleOak";

        String otp=new OTP().generateOTP();

        StringBuilder stringBuilder=ResourceManipulation.getResource(fileName);
        String content=stringBuilder.toString().replace("{}",otp);

        MailThread mailThread=new MailThread();
        mailThread.setSendingMail(jsonObject.getString("email"),subject,content);

        Thread thread=new Thread(mailThread);
        thread.start();;

        return otp;
    }

}
