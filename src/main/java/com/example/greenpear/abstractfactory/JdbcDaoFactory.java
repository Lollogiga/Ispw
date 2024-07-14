package com.example.greenpear.abstractfactory;

import com.example.greenpear.dao.FoodDao;
import com.example.greenpear.dao.FoodDaoJdbc;

import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory{

    public FoodDao getTypeDao() throws SQLException {
        return new FoodDaoJdbc();
    }
}
