package com.trilogyed.DarylCimafrancaU1Capstone.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class TaxRate {
    private String state;
    private BigDecimal rate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object tr) {
        if (this == null) return true;
        if (tr == null || getClass() != tr.getClass()) return false;
        TaxRate taxRate = (TaxRate) tr;

        return Objects.equals(getState(), taxRate.getState()) &&
                Objects.equals(getRate(), taxRate.getRate());

    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(),getRate());
    }
}
