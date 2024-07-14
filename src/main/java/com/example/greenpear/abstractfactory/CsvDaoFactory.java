package com.example.greenpear.abstractfactory;

import com.example.greenpear.dao.FoodDao;
import com.example.greenpear.dao.FoodDaoCsv;

import java.io.IOException;
import java.sql.SQLException;

public class CsvDaoFactory extends DaoFactory{

    public FoodDao getTypeDao() throws IOException, SQLException {
        return new FoodDaoCsv();
    }
}
