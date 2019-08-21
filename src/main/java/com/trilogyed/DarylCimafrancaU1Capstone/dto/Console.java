package com.trilogyed.DarylCimafrancaU1Capstone.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Console {
    private int consoleId;
    private String model;
    private String manufacturer;
    private String memoryAmount;
    private String processor;
    private BigDecimal price;
    private int quantity;

    public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
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
    public boolean equals(Object c) {
        if (this == null) return true;
        if (c == null || getClass() != c.getClass()) return false;
        Console console = (Console) c;
        return getConsoleId() == this.getConsoleId() &&
                Objects.equals(getModel(), console.getModel()) &&
                Objects.equals(getManufacturer(), console.getManufacturer()) &&
                Objects.equals(getProcessor(), console.getProcessor()) &&
                Objects.equals(getPrice(), console.getPrice()) &&
                getQuantity() == console.getQuantity();

    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsoleId(), getModel(), getManufacturer(), getProcessor(), getPrice(), getQuantity());
    }
}
