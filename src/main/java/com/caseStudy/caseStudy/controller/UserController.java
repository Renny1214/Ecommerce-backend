package com.caseStudy.caseStudy.controller;

import com.caseStudy.caseStudy.models.users;
import com.caseStudy.caseStudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/user")
    public boolean signUp(@RequestBody users user) {
        user.setIsActive(1);
        System.out.println(user.toString());

        return userService.add(user);
    }

    @GetMapping(value = "/validateUser",produces = "application/json")
    public String validateUser(Principal principal)
    {
        return "\"user validated\"";
    }

    @PostMapping(value = "/sendOTP", consumes = "application/json")
    public String sendOTP(@RequestBody String email){
        try{
            return userService.sendOTP(email);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(path="/showUser" , produces = "application/json")
    public ArrayList<users> showUsers()
    {
        return userService.showUser();
    }

    @PostMapping(path="/editUser")
    public String editUser(@RequestBody users user,Principal principal)
    {
        return userService.editUser(principal,user);
    }

    @PostMapping(path="/deactivateUser")
    public String deactivateUser(@RequestBody users user,Principal principal) {
        return userService.deactivateUser(principal,user);
    }

}
