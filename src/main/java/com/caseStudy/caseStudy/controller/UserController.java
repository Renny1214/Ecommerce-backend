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


    public static Principal principal;

    @PostMapping(path = "/user" , consumes="application/json")
    public boolean signUp(@RequestBody users user)
    {
        user.setIsActive(1);
        System.out.println("func called");
        System.out.println(user.toString()
        );
        try{
            userService.checkUser(user);
            userService.add(user);
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    @GetMapping(value = "/validateUser",produces = "application/json")
    public String validateUser(Principal principal)
    {

        this.principal = principal;
        System.out.println(this.principal);
        return "\"user validated\"";
    }


    @GetMapping(path="/showUser" , produces = "application/json")
    public ArrayList<users> showUsers()
    {
        return userService.showUser();
    }


    @PostMapping(path="/editUser")
    public String editUser(@RequestBody users user)
    {
        return userService.editUser(principal,user);
    }


    @PostMapping(path="/deactivateUser")
    public String deactivateUser(@RequestBody users user)
    {
        return userService.deactivateUser(principal,user);
    }

}
