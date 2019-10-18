package com.caseStudy.caseStudy.controller;

import com.caseStudy.caseStudy.doa.UserRepositoryClass;
import com.caseStudy.caseStudy.models.products;
import com.caseStudy.caseStudy.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/logout")
public class LoginController {

    @Autowired
    UserRepositoryClass userRepositoryClass;


    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request , HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);

        if(auth!=null)
        {
            new SecurityContextLogoutHandler().logout(request,response,auth);
            request.getSession().invalidate();
        }
        return "\"logout successful\"";
    }

    @GetMapping(path= "/userInfo/{email}/{gmail}/{com}")
    public Optional<users> getUserByEmail(@PathVariable("email") String emailAddress,
                                          @PathVariable("gmail") String emailCompany,
                                          @PathVariable("com") String emailDomain)
    {
        return userRepositoryClass.getByEmail(emailAddress+"@"+emailCompany+"."+emailDomain);
    }



}
