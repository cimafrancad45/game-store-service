package com.trilogyed.DarylCimafrancaU1Capstone.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class TShirt {
    private int tShirtId;
    private String size;
    private String color;
    private String description;
    private BigDecimal price;
    private int quantity;

    public int getTShirtId() {
        return tShirtId;
    }

    public void settShirtId(int tShirtId) {
        this.tShirtId = tShirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object t) {
        if (this == null) return true;
        if (t == null || getClass() != t.getClass()) return false;
        TShirt tshirt = (TShirt) t ;

        return getTShirtId() == tshirt.getTShirtId() &&
                Objects.equals(getSize(), tshirt.getSize()) &&
                Objects.equals(getColor(), tshirt.getColor()) &&
                Objects.equals(getDescription(), tshirt.getDescription()) &&
                Objects.equals(getPrice(), tshirt.getPrice()) &&
                getQuantity() == tshirt.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTShirtId(), getSize(),getColor(),getDescription(),getPrice(),getQuantity());
    }
}
