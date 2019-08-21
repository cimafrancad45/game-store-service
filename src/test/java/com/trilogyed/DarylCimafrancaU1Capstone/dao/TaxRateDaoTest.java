package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.TaxRate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaxRateDaoTest {

    @Autowired
    TaxRateDao taxRateDao;

    @Test
    public void getTax(){

        BigDecimal taxRate = taxRateDao.getTax("NY");

        Assert.assertEquals(new BigDecimal("0.06"),taxRate);
    }
}
