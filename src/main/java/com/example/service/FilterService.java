package com.example.service;

import com.example.model.Currency;
import com.example.model.History;
import com.example.repos.CurrencyRepo;
import com.example.repos.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterService {

    private final CurrencyRepo currencyRepo;
    private final HistoryRepo historyRepo;
    private Currency sou;
    private Currency tar;

    @Autowired
    public FilterService(CurrencyRepo currencyRepo, HistoryRepo historyRepo) {
        this.currencyRepo = currencyRepo;
        this.historyRepo = historyRepo;
    }

    public Iterable<History> filter(String date, String source, String target) {
        Iterable<History> histories = historyRepo.findAll();
        Iterable<Currency> currencies = currencyRepo.findAll();
        for (Currency currency : currencies) {
            if (currency.getCharCode().equals(source)) {
                sou = currency;
            }
            if (currency.getCharCode().equals(target)) {
                tar = currency;
            }
        }

        String dateDMY = null;

        if (date != null && !date.isEmpty()) {
            String [] word = date.split("-");
            dateDMY = word[2] + "." + word[1] + "." + word[0];
        }

        if (date != null && !date.isEmpty()
                && source != null && !source.isEmpty()
                && target != null && !target.isEmpty()) {
            histories = historyRepo.findByDateAndSourceAndTarget(dateDMY, sou, tar);
        } else {
            if (date != null && !date.isEmpty() && source != null && !source.isEmpty()) {
                histories = historyRepo.findByDateAndSource(dateDMY, sou);
            } else {
                if (date != null && !date.isEmpty() && target != null && !target.isEmpty()) {
                    histories = historyRepo.findByDateAndTarget(dateDMY, tar);
                } else {
                    if (source != null && !source.isEmpty() && target != null && !target.isEmpty()) {
                        histories = historyRepo.findBySourceAndTarget(sou, tar);
                    } else {
                        if (date != null && !date.isEmpty()) {
                            histories = historyRepo.findByDate(dateDMY);
                        } else {
                            if (source != null && !source.isEmpty()) {
                                histories = historyRepo.findBySource(sou);
                            } else {
                                if (target != null && !target.isEmpty()) {
                                    histories = historyRepo.findByTarget(tar);
                                } else {
                                    histories = historyRepo.findAll();
                                }
                            }
                        }
                    }
                }
            }
        }

        return histories;
    }
}
