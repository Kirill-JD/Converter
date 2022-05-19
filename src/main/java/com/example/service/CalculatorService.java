package com.example.service;

import com.example.model.Currency;
import com.example.model.History;
import com.example.model.User;
import com.example.repos.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorService {

    private final HistoryRepo historyRepo;
    private final List<Currency> list = new SAXService().SAXCheck();
    private final CheckService checkService;

    @Autowired
    public CalculatorService(HistoryRepo historyRepo, CheckService checkService) throws IOException, SAXException {
        this.historyRepo = historyRepo;
        this.checkService = checkService;
    }

    public String calculator(String source, String target, String amount, User user, Iterable<Currency> currencies) throws IOException, SAXException {

        String result = null;

        double a = Integer.parseInt(amount);
        double b = 0;
        double c = 0;
        Currency sou = null;
        Currency tar = null;
        checkService.check();
        if (source != null && !source.isEmpty() && target != null && !target.isEmpty()) {

            for (Currency currency : currencies) {
                boolean st;
                List<Object> arrayB = getCurrency(source, b, currency);
                List<Object> arrayC = getCurrency(target, c, currency);

                b = (double) arrayB.get(0);
                st = (Boolean) arrayB.get(1);
                if (st) {
                    sou = currency;
                }
                c = (double) arrayC.get(0);
                st = (Boolean) arrayC.get(1);
                if (st) {
                    tar = currency;
                }
            }

            double res = a * b / c;
            result = String.format("%.2f", res);

            String date = list.get(list.size() - 1).getDate();


            History history = new History(sou, tar, amount, result, date, user);

            historyRepo.save(history);
        }


        return result;
    }

    private List<Object> getCurrency(@RequestParam String value, double c, Currency currency) {
        String meaning;
        boolean st = false;
        if (currency.getCharCode().equals(value)) {
            meaning = currency.getValue();
            String [] wordTarget = meaning.split(",");
            if (wordTarget.length == 2) {
                meaning = wordTarget[0] + "." + wordTarget[1];
            } else {
                meaning = wordTarget[0];
            }
            c = Double.parseDouble(meaning);
            st = true;
        }
        List<Object> array = new ArrayList<>();
        array.add(c);
        array.add(st);
        return array;
    }
}
