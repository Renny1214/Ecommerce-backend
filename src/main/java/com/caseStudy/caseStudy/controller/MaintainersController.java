package com.caseStudy.caseStudy.controller;

import com.caseStudy.caseStudy.models.Maintainers;
import com.caseStudy.caseStudy.models.Sellers;
import com.caseStudy.caseStudy.service.MaintainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dev")
public class MaintainersController {

    @Autowired
    private MaintainerService maintainerService;

    @GetMapping(path = "/login")
    public boolean login(Principal principal){
        return true;
    }

    @PostMapping(path = "/addMaintainer",consumes = "application/json")
    public boolean addMaintainer(@RequestBody Maintainers maintainers){
        return maintainerService.signUp(maintainers);
    }

    @GetMapping(path = "/getSellers",produces = "application/json")
    public List<Sellers> getSellers(Principal principal){
        return maintainerService.getSellers(principal);
    }

    @GetMapping(path = "/setPaid/{id}")
    public boolean setPaid(@PathVariable Long id,Principal principal){
        return maintainerService.setPaid(id,principal);
    }

    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request , HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);

        if(auth!=null) {
            new SecurityContextLogoutHandler().logout(request,response,auth);
            request.getSession().invalidate();
        }
        return "\"logout successful\"";
    }
}
