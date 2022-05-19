package com.example.controller;

import com.example.model.Currency;
import com.example.model.User;
import com.example.repos.CurrencyRepo;
import com.example.service.CalculatorService;
import com.example.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import java.io.IOException;

@Controller
public class MainController {

    private final CurrencyRepo currencyRepo;
    private final CheckService checkService;
    private final CalculatorService calculatorService;

    @Autowired
    public MainController(CurrencyRepo currencyRepo,
                          CheckService checkService,
                          CalculatorService calculatorService) {
        this.currencyRepo = currencyRepo;
        this.checkService = checkService;
        this.calculatorService = calculatorService;
    }


    @GetMapping("/")
    public String home() throws IOException, SAXException {

        checkService.check();
        return "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String source,
                       @RequestParam(required = false, defaultValue = "") String target,
                       @RequestParam(required = false, defaultValue = "0") String amount,
                       Model model) {

        Iterable<Currency> currencies = currencyRepo.findAll();

        model.addAttribute("source", source);
        model.addAttribute("target", target);
        model.addAttribute("amount", amount);
        model.addAttribute("currencies", currencies);

        return "main";
    }

    @PostMapping("/main")
    public String total(@AuthenticationPrincipal User user,
                        @RequestParam(required = false, defaultValue = "") String source,
                        @RequestParam(required = false, defaultValue = "") String target,
                        @RequestParam(required = false, defaultValue = "0") String amount,
                        Model model) throws IOException, SAXException {

        Iterable<Currency> currencies = currencyRepo.findAll();
        String result = calculatorService.calculator(source, target, amount, user, currencies);

        model.addAttribute("source", source);
        model.addAttribute("target", target);
        model.addAttribute("currencies", currencies);
        model.addAttribute("result", result);
        model.addAttribute("amount", amount);

        return "main";
    }
}
