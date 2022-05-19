package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String valuteId;
    private String charCode;
    private String name;
    private String value;
    private String date;

    public Currency() {
    }

    public Currency(String valuteId, String charCode, String name, String value, String date) {
        this.valuteId = valuteId;
        this.charCode = charCode;
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getValuteId() {
        return valuteId;
    }

    public String getCharCode() {
        return charCode;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
}
