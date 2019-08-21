package com.trilogyed.DarylCimafrancaU1Capstone.viewmodel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class ConsoleViewModel {
    private int consoleId;
    @NotEmpty(message = "Please enter the console's model.")
    private String model;
    @NotEmpty(message = "Please enter the console's manufacturer.")
    private String manufacturer;
    @NotEmpty(message = "Please enter the console's memory amount.")
    private String memoryAmount;
    @NotEmpty(message = "Please enter the console's processor.")
    private String processor;
    @NotNull(message = "Please the enter the game's price.")
    @PositiveOrZero(message = "Price is invalid")
    @DecimalMax(value = "9999.99", inclusive = true)
    private BigDecimal price;
    @Positive(message = "Quantity must be a non-negative number.")
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
        ConsoleViewModel console = (ConsoleViewModel) c;
        return consoleId == console.consoleId &&
                model.equals(console.model) &&
                manufacturer.equals(console.manufacturer) &&
                processor.equals((console.processor))&&
                price.equals(console.price) &&
                quantity == console.quantity;

    }

    @Override
    public int hashCode() {
        return Objects.hash(consoleId, model, manufacturer, processor, price, quantity);
    }
}
