package com.example.greenpear.dao;

import com.example.greenpear.entities.Registration;
import com.example.greenpear.utils.query.*;

import javax.security.auth.login.CredentialException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDaoImpl implements RegisterDao{

    private Connection connection = null;
    private PreparedStatement preparedStatement;

    //Valore di ritorno query
    private ResultSet resultSet;

    public RegisterDaoImpl() throws SQLException{
        //Apro una nuova connessione o prendo quella già esistente
        connection = SingletonConnection.getInstance();
    }
    @Override
    public void registerNewUser(Registration newUser) throws SQLException, CredentialException {
        //Prima di registrare un nuovo utente devo verificare se l'email esiste già
        if(emailExists(newUser.getEmail()) == false){
            //Se non esiste, lo registro:
            preparedStatement = connection.prepareStatement(RegistrationQuery.addUser());
            preparedStatement.setString(1, newUser.getUsername());
            preparedStatement.setString(2, newUser.getEmail());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setInt(4, newUser.getRole().getId());
            preparedStatement.executeUpdate();
        }
        else{
            //L'utente esiste, genero eccezione:
            throw new CredentialException("Email alredy exists");
        }
    }

    @Override
    public boolean emailExists(String email) throws SQLException, CredentialException {
        preparedStatement = connection.prepareStatement(RegistrationQuery.verifyEmailExists());
        preparedStatement.setString(1,email);
        resultSet = preparedStatement.executeQuery();
        //IsBeforeFirst ritorna true se l'email esiste già:
        return resultSet.isBeforeFirst();
    }
}
