package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.Invoice;
import com.trilogyed.DarylCimafrancaU1Capstone.dto.ProcessingFee;
import com.trilogyed.DarylCimafrancaU1Capstone.dto.TaxRate;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    List<Invoice> getAllInvoices();

    void updateInvoice(Invoice invoice);

    void deleteInvoice(int id);

}
