package pl.pja.mpodlasi.labone.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Expense {
    private int id;
    @ApiModelProperty(notes = "How much you spend")
    private double value;
    @ApiModelProperty(notes = "when did you spend all that cash")
    private Date date;
    @ApiModelProperty(notes = "for what")
    private String description;

    public Expense(int id, double value, Date date, String description) {
        if (id < 0) throw new IllegalArgumentException("Id should be natural integer.");
        this.id = id;
        this.date = date;
        this.value = value;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = (Date) date.clone();
    }
}

