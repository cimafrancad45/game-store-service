package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.ProcessingFee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFeeDaoTest {

    @Autowired
    ProcessingFeeDao processingFeeDao;

    @Test
    public void getProcessingFee(){

        BigDecimal processingFee = processingFeeDao.getProcessingFee("Consoles");

        Assert.assertEquals(new BigDecimal("14.99"), processingFee);

    }

}
