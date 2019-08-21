package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.TaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TaxRateDaoImpl implements TaxRateDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TaxRateDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String SELECT_TAX_RATE_SQL =
            "select * from sales_tax_rate where state = ?";

    public BigDecimal getTax(String state){
        TaxRate taxRate = jdbcTemplate.queryForObject(SELECT_TAX_RATE_SQL, this::mapRowToTaxRate, state);

        return taxRate.getRate();
    }

    private TaxRate mapRowToTaxRate(ResultSet rs, int rowNum) throws SQLException {
        TaxRate taxRate = new TaxRate();
        taxRate.setState(rs.getString("state"));
        taxRate.setRate(rs.getBigDecimal("rate"));
        return taxRate;
    }
}
