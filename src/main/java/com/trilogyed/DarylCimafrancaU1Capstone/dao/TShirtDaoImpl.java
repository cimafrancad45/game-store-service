package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoImpl implements TShirtDao{
    //jdbc template
    JdbcTemplate jdbcTemplate;

    @Autowired
    public TShirtDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //statements
    private final String INSERT_TSHIRT_SQL =
            "insert into t_shirt (size, color, description, price, quantity) values(?,?,?,?,?)";
    private final String SELECT_TSHIRT_SQL =
            "select * from t_shirt where t_shirt_id = ?";
    private final String SELECT_ALL_TSHIRTS_SQL =
            "select * from t_shirt";
    private final String SELECT_TSHIRTS_BY_SIZE =
            "select * from t_shirt where size = ?";
    private final String SELECT_TSHIRTS_BY_COLOR =
            "select * from t_shirt where color = ?";
    private final String UPDATE_TSHIRT_SQL =
            "update t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ? where t_shirt_id = ?";
    private final String DELETE_TSHIRT =
            "delete from t_shirt where t_shirt_id = ?";

    public TShirt addTShirt (TShirt tShirt){
        jdbcTemplate.update(INSERT_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        tShirt.settShirtId(id);

        return tShirt;
    }

    public TShirt getTShirt (int id){
        try {
            return jdbcTemplate.queryForObject(SELECT_TSHIRT_SQL, this::mapTShirtToRow, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<TShirt> getAllTShirts (){
        return jdbcTemplate.query(SELECT_ALL_TSHIRTS_SQL, this::mapTShirtToRow);
    }

    public List<TShirt> getTShirtsBySize(String size){
        return jdbcTemplate.query(SELECT_TSHIRTS_BY_SIZE, this::mapTShirtToRow, size);
    }

    public List<TShirt> getTShirtsByColor(String color){
        return jdbcTemplate.query(SELECT_TSHIRTS_BY_COLOR, this::mapTShirtToRow, color);
    }

    public void updateTShirt(TShirt tShirt){
        jdbcTemplate.update(UPDATE_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity(),
                tShirt.getTShirtId());
    }

    public void deleteTShirt(int id){
        jdbcTemplate.update(DELETE_TSHIRT, id);
    }


    private TShirt mapTShirtToRow(ResultSet rs, int rowNum) throws SQLException {
        TShirt tShirt = new TShirt();
        tShirt.settShirtId(rs.getInt("t_shirt_id"));
        tShirt.setSize(rs.getString("size"));
        tShirt.setColor(rs.getString("color"));
        tShirt.setDescription(rs.getString("description"));
        tShirt.setPrice(rs.getBigDecimal("price"));
        tShirt.setQuantity(rs.getInt("quantity"));

        return tShirt;
    }
}
