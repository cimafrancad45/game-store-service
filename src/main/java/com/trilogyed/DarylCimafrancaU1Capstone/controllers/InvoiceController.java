package com.trilogyed.DarylCimafrancaU1Capstone.controllers;

import com.trilogyed.DarylCimafrancaU1Capstone.exception.NotFoundException;

import com.trilogyed.DarylCimafrancaU1Capstone.service.GameStoreServiceLayer;
import com.trilogyed.DarylCimafrancaU1Capstone.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {
    @Autowired
    GameStoreServiceLayer serviceLayer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody @Valid InvoiceViewModel invoiceViewModel) {
        return serviceLayer.saveInvoice(invoiceViewModel);
    }


}
