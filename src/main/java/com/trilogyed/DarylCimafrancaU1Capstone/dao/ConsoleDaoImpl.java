package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsoleDaoImpl implements ConsoleDao {
    //jdbc template
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_CONSOLE_SQL =
            "insert into console (model, manufacturer, memory_amount, processor, price, quantity) values (?,?,?,?,?,?)";

    private final String SELECT_CONSOLE_SQL =
            "select * from console where console_id = ?";

    private final String SELECT_ALL_CONSOLES_SQL =
            "select * from console";

    private final String UPDATE_CONSOLE_SQL =
            "update console set model = ?, manufacturer = ?, memory_amount= ?, processor = ?, price = ?, quantity = ? where console_id = ?";

    private final String DELETE_CONSOLE_SQL =
            "delete from console where console_id = ?";

    private final String SELECT_CONSOLE_BY_MANUFACTURER =
            "select * from console where manufacturer = ?";

    @Autowired
    public ConsoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Console addConsole(Console console){
        jdbcTemplate.update(INSERT_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        console.setConsoleId(id);

        return console;
    }

    @Override
    public Console getConsole(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CONSOLE_SQL, this::mapConsoleToRow, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Console> getAllConsoles() {
        return jdbcTemplate.query(SELECT_ALL_CONSOLES_SQL, this::mapConsoleToRow);
    }

    @Override
    public void updateConsole(Console console) {
        jdbcTemplate.update(UPDATE_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity(),
                console.getConsoleId()
        );
    }

    @Override
    public void deleteConsole(int id) {
        jdbcTemplate.update(DELETE_CONSOLE_SQL, id);
    }

    @Override
    public List<Console> getConsoleByManufacturer(String manufacturer) {
        return jdbcTemplate.query(SELECT_CONSOLE_BY_MANUFACTURER, this::mapConsoleToRow, manufacturer);
    }

    private Console mapConsoleToRow(ResultSet rs, int rowNum) throws SQLException {
        Console console = new Console();
        console.setConsoleId(rs.getInt("console_id"));
        console.setModel(rs.getString("model"));
        console.setManufacturer((rs.getString("manufacturer")));
        console.setMemoryAmount(rs.getString("memory_amount"));
        console.setProcessor(rs.getString("processor"));
        console.setPrice(rs.getBigDecimal("price"));
        console.setQuantity(rs.getInt("quantity"));
        return console;
    }

}
