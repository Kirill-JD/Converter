package com.example.repos;

import com.example.model.Currency;
import com.example.model.History;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepo extends CrudRepository<History,Long> {

    List<History> findByDateAndSourceAndTarget(String date, Currency source, Currency target);
    List<History> findByDateAndSource(String date, Currency source);
    List<History> findByDateAndTarget(String date, Currency target);
    List<History> findBySourceAndTarget(Currency source, Currency target);
    List<History> findByDate(String date);
    List<History> findBySource(Currency source);
    List<History> findByTarget(Currency target);
}
