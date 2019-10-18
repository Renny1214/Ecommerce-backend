package com.caseStudy.caseStudy.doa;

import com.caseStudy.caseStudy.models.products;
import com.caseStudy.caseStudy.models.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserRepositoryClass {

    @Autowired
    UserRepository userRepository;


    public void add(users user)
    {
        userRepository.save(user);
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

    public boolean checkUser(users user) throws Exception{
        ArrayList<users> usersList=(ArrayList<users>) userRepository.findAll();

        for(int i=0;i<usersList.size();i++){
            if(usersList.get(i).getEmail().equals(user.getEmail())){
                throw new Exception();
            }
        }

        return true;
    }
}
