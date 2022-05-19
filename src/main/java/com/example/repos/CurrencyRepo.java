package com.example.repos;

import com.example.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepo extends CrudRepository<Currency, Long> {

}
