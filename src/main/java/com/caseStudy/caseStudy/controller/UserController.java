package com.caseStudy.caseStudy.controller;

import com.caseStudy.caseStudy.doa.UserRepositoryClass;
import com.caseStudy.caseStudy.models.products;
import com.caseStudy.caseStudy.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class UserController {

    @Autowired
    UserRepositoryClass userRepositoryClass;


    public static Principal principal;

    @PostMapping(path = "/user" , consumes="application/json")
    public boolean signUp(@RequestBody users user)
    {
        user.setIsActive(1);
        System.out.println("func called");
        System.out.println(user.toString()
        );
        try{
            userRepositoryClass.checkUser(user);
            userRepositoryClass.add(user);
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
        return userRepositoryClass.showUser();
    }


    @PostMapping(path="/editUser")
    public String editUser(@RequestBody users user)
    {
        return userRepositoryClass.editUser(principal,user);
    }


    @PostMapping(path="/deactivateUser")
    public String deactivateUser(@RequestBody users user)
    {
        return userRepositoryClass.deactivateUser(principal,user);
    }

}
