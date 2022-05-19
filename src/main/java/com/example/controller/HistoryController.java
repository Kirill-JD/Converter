package com.example.controller;

import com.example.model.History;
import com.example.model.User;
import com.example.repos.CurrencyRepo;
import com.example.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistoryController {

    private final CurrencyRepo currencyRepo;
    private final FilterService filterService;

    @Autowired
    public HistoryController(CurrencyRepo currencyRepo, FilterService filterService) {
        this.currencyRepo = currencyRepo;
        this.filterService = filterService;
    }

    @GetMapping("/history")
    public String historyPage(@AuthenticationPrincipal User user,
                              @RequestParam(required = false, defaultValue = "") String date,
                              @RequestParam(required = false, defaultValue = "") String source,
                              @RequestParam(required = false, defaultValue = "") String target,
                              Model model) {

        Iterable<History> histories = filterService.filter(date, source, target);

        model.addAttribute("user", user);
        model.addAttribute("source", source);
        model.addAttribute("target", target);
        model.addAttribute("date", date);
        model.addAttribute("histories", histories);
        model.addAttribute("currencies", currencyRepo.findAll());

        return "history";
    }
}
