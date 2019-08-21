package com.trilogyed.DarylCimafrancaU1Capstone.viewmodel;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {
    private int invoiceId;
    @NotEmpty(message = "Please enter customer name.")
    private String name;
    @NotEmpty(message = "Please enter customer street.")
    private String street;
    @NotEmpty(message = "Please enter customer city.")
    private String city;
    @NotEmpty(message = "Please enter customer state.")
    private String state;
    @NotEmpty(message = "Please enter customer zip code.")
    private String zipCode;
    @NotEmpty(message = "Please enter invoice item type.")
    private String itemType;
    @NotEmpty(message = "Please enter item id.")
    @Min(value = 0, message = "ID cannot be negative.")
    private int itemId;
    @NotEmpty(message = "Please enter item's unit price")
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal unitPrice;
    @NotEmpty(message = "Please enter quantity")
    @Min(value = 0, message = "Quantity cannot be negative.")
    private int quantity;
    @NotNull(message = "Subtotal cannot be blank.")
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal subtotal;
    @NotNull(message = "Tax cannot be blank.")
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal tax;
    @NotNull(message = "Processing fee cannot be blank")
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal processingFee;
    @NotNull(message = "Total cannot be blank.")
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal total;

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipCode;
    }

    public void setZipcode(String zipcode) {
        this.zipCode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object i) {
        if (this == null) return true;
        if (i == null || getClass() != i.getClass()) return false;
        InvoiceViewModel invoice = (InvoiceViewModel) i;

        return invoiceId == invoice.invoiceId &&
                name.equals(invoice.name)&&
                street.equals(invoice.street)&&
                city.equals(invoice.city)&&
                state.equals(invoice.state)&&
                zipCode.equals(invoice.zipCode)&&
                itemType.equals(invoice.itemType)&&
                itemId == invoice.itemId &&
                unitPrice.equals(invoice.unitPrice)&&
                quantity == invoice.quantity &&
                subtotal.equals(invoice.subtotal)&&
                tax.equals(invoice.tax)&&
                processingFee.equals(invoice.processingFee)&&
                total.equals(invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, name, street, city, state, zipCode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }
}
