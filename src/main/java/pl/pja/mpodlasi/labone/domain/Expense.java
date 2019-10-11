package pl.pja.mpodlasi.labone.domain;

import java.util.*;

public class Expense {
    private int id;
    private double value;
    private Date date;

    public Expense(int id, double value, Date date) {
        if (id < 0) throw new IllegalArgumentException("Id should be natural integer.");
        this.id = id;
        this.date = date;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) {
        this.date = (Date) date.clone();
    }
}

