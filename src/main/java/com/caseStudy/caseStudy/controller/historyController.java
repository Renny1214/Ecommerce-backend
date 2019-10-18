package com.caseStudy.caseStudy.controller;


import com.caseStudy.caseStudy.models.history;
import com.caseStudy.caseStudy.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping()
public class historyController {

    @Autowired
    HistoryService historyService;

    @GetMapping(path="/history")
    public ArrayList<history> getHistory(Principal principal)
    {
        return historyService. getHistoryFromCurrentUser(principal);
    }

    @GetMapping(path="/history/addHistory")
    public String addHistoryToList(Principal principal)
    {
        System.out.println();
        return historyService.addListToHistory(principal);
    }
}
