package com.example.service;

import com.example.model.Currency;
import com.example.repos.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

@Service
public class CheckService {
    private final CurrencyRepo currencyRepo;
    @Autowired
    public CheckService(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    public void check() throws IOException, SAXException {
        List<Currency> list = new SAXService().SAXCheck();
        boolean matching = false;

        Iterable<Currency> currencies = currencyRepo.findAll();

        if (!currencies.iterator().hasNext()) {
            currencies = list;
            currencyRepo.saveAll(currencies);
        } else {
            for (Currency currency : currencies) {
                if (currency.getDate().equals(list.get(list.size() - 1).getDate())) {
                    matching = true;
                    break;
                }
            }
            if (!matching) {
                currencies = list;
                currencyRepo.saveAll(currencies);
            }
        }
    }
}
