package com.caseStudy.caseStudy.controller;

import com.caseStudy.caseStudy.models.users;
import com.caseStudy.caseStudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/logout")
public class LoginController {

    @Autowired
    UserService userService;


    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request , HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);

        if(auth!=null) {
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
        return userService.getByEmail(emailAddress+"@"+emailCompany+"."+emailDomain);
    }

    @PostMapping(path = "/forgotPassword",consumes = "application/json")
    public boolean forgotPassword(@RequestBody String emailJSON){
        try{
            userService.forgotPassword(emailJSON);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
