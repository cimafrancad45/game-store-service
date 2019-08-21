package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.ProcessingFee;

import java.math.BigDecimal;

public interface ProcessingFeeDao {

    BigDecimal getProcessingFee(String itemType);
}
