package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.UserRepository;
import com.caseStudy.caseStudy.models.users;
import com.caseStudy.caseStudy.service.generations.OTP;
import com.caseStudy.caseStudy.service.resource.manipulation.ResourceManipulation;
import com.caseStudy.caseStudy.service.threads.MailThread;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean add(users user)
    {
        if(!userRepository.findByEmail(user.getEmail()).isPresent()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        else{
            return false;
        }

        return true;
    }

    public ArrayList<users> showUser()
    {
        return (ArrayList<users>)userRepository.findAll();
    }


    public Optional<users> getByEmail(String email)
    {
        System.out.println("getting by email");
        return userRepository.findByEmail(email);
    }

    public String editUser(Principal principal,users user) {
        Optional<users> users = getByEmail(principal.getName());
        user.setId(users.get().getId());
        user.setIsActive(1);
        userRepository.save(user);
        return "\"user updated successfully\"";
    }

    public String deactivateUser(Principal principal, users user) {
        Optional<users> users = getByEmail(principal.getName());
        user.setId(users.get().getId());
        user.setIsActive(0);
        userRepository.save(user);
        return "\"user deactivated successfully\"";
    }

    public String sendOTP(Object emailObject) throws IOException {
        MailThread mailThread=new MailThread();
        final String emailKey="email";
        final String otpFile="/otpMail.json";
        final String subject="OTP for account creation";

        JSONObject jsonObject=new JSONObject(emailObject.toString());
        String email=jsonObject.getString(emailKey);

        String otp=new OTP().generateOTP();
        StringBuilder stringBuilder= ResourceManipulation.getResource(otpFile);
        stringBuilder.append(otp);

        mailThread.setSendingMail(email,subject,stringBuilder.toString());
        Thread thread=new Thread(mailThread);
        thread.start();

        return otp;
    }
}
