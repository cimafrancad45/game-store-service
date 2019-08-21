package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.TShirt;

import java.util.List;

public interface TShirtDao {

    TShirt addTShirt(TShirt tShirt);

    TShirt getTShirt(int id);

    List<TShirt> getAllTShirts();

    List<TShirt> getTShirtsBySize(String size);

    List<TShirt> getTShirtsByColor(String Color);

    void updateTShirt(TShirt tShirt);

    void deleteTShirt(int id);


}
