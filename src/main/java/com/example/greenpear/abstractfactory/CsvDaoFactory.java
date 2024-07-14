package com.example.greenpear.abstractfactory;

import com.example.greenpear.dao.FoodDao;
import com.example.greenpear.dao.FoodDaoCsv;

import java.io.IOException;

public class CsvDaoFactory extends DaoFactory{

    public FoodDao getTypeDao() throws IOException {
        return new FoodDaoCsv();
    }
}
