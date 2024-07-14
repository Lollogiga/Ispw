package com.example.greenpear.abstractfactory;

import com.example.greenpear.dao.FoodDao;
import com.example.greenpear.exception.InformationErrorException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DaoFactory {

    public static synchronized DaoFactory createFoodDao() throws IOException, InformationErrorException {
        InputStream inputStream = null;
        Properties prop = new Properties();

        try{
            inputStream = new FileInputStream("src/main/resources/com/example/greenpear/properties/connectionDB.properties");
            prop.load(inputStream);
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        //In ogni caso chiudiamo l'inputStream:
        finally {
            if(inputStream != null){inputStream.close();}
        }

        String daoType = prop.getProperty("FOOD_DAO_TYPE");
        return switch (daoType){
            case "JDBC" -> new JdbcDaoFactory();
            case "CSV" -> new CsvDaoFactory();
            default -> throw new InformationErrorException("Dao type error!");
        };

    }

    public abstract FoodDao getTypeDao() throws IOException, SQLException;


}
