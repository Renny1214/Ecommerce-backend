package com.caseStudy.caseStudy.controller;

import com.caseStudy.caseStudy.models.Sellers;
import com.caseStudy.caseStudy.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @GetMapping(path = "/sellerLogin")
    public String login(Principal principal){
        System.out.println("Seller Login :=> seller :"+principal.getName());
        return "seller authenticated";
    }

    @PostMapping(path = "/signUp", consumes = "application/json")
    public String signUp(@RequestBody Sellers sellers){
        return sellerService.signUp(sellers)+"";
    }

    @GetMapping(path = "/logout")
    public boolean logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();

        if(auth != null){
            new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,auth);
            httpServletRequest.getSession().invalidate();
            return true;
        }

        return false;
    }

    @GetMapping(path = "/getSellerInfo", produces = "application/json")
    public Sellers getInfo(Principal principal){
        return sellerService.getInfo(principal);
    }
}
