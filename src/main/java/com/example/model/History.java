package com.example.model;

import javax.persistence.*;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String amount;

    @ManyToOne
    @JoinColumn(name = "source")
    private Currency source;

    @ManyToOne
    @JoinColumn(name = "target")
    private Currency target;

    private String result;
    private String date;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public History() {
    }

    public History(Currency source, Currency target, String amount, String result, String date, User user) {
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.result = result;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Currency getSource() {
        return source;
    }

    public void setSource(Currency source) {
        this.source = source;
    }

    public Currency getTarget() {
        return target;
    }

    public void setTarget(Currency target) {
        this.target = target;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
