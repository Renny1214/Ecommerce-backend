package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.UserRepository;
import com.caseStudy.caseStudy.models.users;
import com.caseStudy.caseStudy.service.generations.OTP;
import com.caseStudy.caseStudy.service.generations.Password;
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

    public String sendOTP(String emailObject) throws IOException {
        MailThread mailThread=new MailThread();
        final String emailKey="email";
        final String otpFile="/otpMail.html";
        final String subject="OTP for account creation";

        JSONObject jsonObject=new JSONObject(emailObject);
        String email=jsonObject.getString(emailKey);

        String otp=new OTP().generateOTP();
        StringBuilder stringBuilder= ResourceManipulation.getResource(otpFile);
        String temp=stringBuilder.toString().replace("{}",otp);

        mailThread.setSendingMail(email,subject,temp);
        Thread thread=new Thread(mailThread);
        thread.start();

        return otp;
    }

    public void forgotPassword(String emailJSON) throws IOException {
        MailThread mailThread=new MailThread();
        JSONObject jsonObject=new JSONObject(emailJSON);
        final String emailKey="email";
        final String subject="Little Oak Forgot Password";
        final String forgotPasswordFile="/forgotPasswordMail.html";

        String email=jsonObject.getString(emailKey);
        String password=new Password().generatePassword();
        StringBuilder stringBuilder=ResourceManipulation.getResource(forgotPasswordFile);

        String content=stringBuilder.toString().replace("{}",password);
        mailThread.setSendingMail(email,subject,content);

        Thread thread=new Thread(mailThread);
        thread.start();
    }
}
