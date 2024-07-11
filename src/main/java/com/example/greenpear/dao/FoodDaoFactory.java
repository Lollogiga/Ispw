package com.example.greenpear.dao;

import com.example.greenpear.exception.InformationErrorException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class FoodDaoFactory {

    public FoodDao createFoodDao() throws IOException, SQLException, InformationErrorException {
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
            case "JDBC" -> new FoodDaoJdbc();
            case "CSV" -> new FoodDaoCsv();
            default -> throw new InformationErrorException("Dao type error!");
        };

    }

}
