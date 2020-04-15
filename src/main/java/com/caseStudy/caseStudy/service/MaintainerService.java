package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.MaintainersRepository;
import com.caseStudy.caseStudy.doa.SellerRepository;
import com.caseStudy.caseStudy.models.Maintainers;
import com.caseStudy.caseStudy.models.Sellers;
import com.caseStudy.caseStudy.service.resource.manipulation.ResourceManipulation;
import com.caseStudy.caseStudy.service.threads.MailThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class MaintainerService {

    @Autowired
    private MaintainersRepository maintainersRepository;
    @Autowired
    private SellerRepository sellerRepository;

    public boolean signUp(Maintainers maintainers){
        Optional<Maintainers> maintainers1=maintainersRepository.findByEmail(maintainers.getEmail());

        if(!maintainers1.isPresent()){
            maintainers.setPassword(new BCryptPasswordEncoder().encode(maintainers.getPassword()));
            maintainersRepository.save(maintainers);

            return true;
        }

        return false;
    }

    public List<Sellers> getSellers(Principal principal){
        Optional<Maintainers> maintainer=maintainersRepository.findByEmail(principal.getName());

        if(maintainer.isPresent()){
            return (List<Sellers>)sellerRepository.findAllByStatus("approved");
        }

        return null;
    }

    public boolean setPaid(Long id,Principal principal){
        Optional<Maintainers> maintainer=maintainersRepository.findByEmail(principal.getName());

        if(maintainer.isPresent()){
            Optional<Sellers> sellers=sellerRepository.findById(id);

            if(sellers.isPresent()){
                Sellers sellers1=sellers.get();
                sellers1.setSalesMade(0);

                sellerRepository.save(sellers1);
                return true;
            }
        }

        return false;
    }

    public List<Sellers> getRequests(Principal principal){
        if(maintainersRepository.findByEmail(principal.getName()).isPresent()){
            return sellerRepository.findAllByStatus("applied");
        }

        return null;
    }

    public boolean acceptRequest(Principal principal,Long id) throws IOException {
        if(maintainersRepository.findByEmail(principal.getName()).isPresent()){

            Optional<Sellers> sellers=sellerRepository.findById(id);
            if(sellers.isPresent()){
                Sellers temp=sellers.get();
                temp.setStatus("approved");

                sellerRepository.save(temp);
                sendAcceptanceMail(sellers.get());
                return true;
            }
        }

        return false;
    }
    private void sendAcceptanceMail(Sellers sellers) throws IOException {
        final String subject="Partner at LittleOak";
        final String fileName="/approvedSellerMail.html";
        final StringBuilder stringBuilder= ResourceManipulation.getResource(fileName);

        String content=stringBuilder.toString().replace("{}",sellers.getFirstName());

        MailThread mailThread=new MailThread();
        mailThread.setSendingMail(sellers.getEmail(),subject,content);

        Thread thread=new Thread(mailThread);
        thread.start();
    }

    public boolean rejectRequest(Principal principal,Long id){
        if(maintainersRepository.findByEmail(principal.getName()).isPresent()){

            if(sellerRepository.findById(id).isPresent()){
                sellerRepository.deleteById(id);

                return true;
            }
        }

        return false;
    }
}
