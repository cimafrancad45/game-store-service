package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.TaxRate;

import java.math.BigDecimal;

public interface TaxRateDao {

    BigDecimal getTax(String state);

}
