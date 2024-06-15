package com.example.greenpear.dao;

import com.example.greenpear.entities.UserProfile;
import com.example.greenpear.utils.Role;
import com.example.greenpear.utils.query.LoginQuery;

import javax.security.auth.login.CredentialException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO verificare se posso popolare user in questo modo:
public class LoginDaoImpl implements LoginDao{
    private Connection connection = null;
    private PreparedStatement preparedStatement;
    //Valore di ritorno query
    private ResultSet resultSet;

    public LoginDaoImpl() throws SQLException{
        //Apro una connessione o prendo quella già esistente:
        connection = SingletonConnection.getInstance();
    }
    @Override
    public UserProfile loginUser(UserProfile user) throws SQLException, CredentialException{

        preparedStatement = connection.prepareStatement(LoginQuery.loginUser());
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        resultSet=preparedStatement.executeQuery();

        //Se l'utente risulta nel sistema, salvo le sue informazioni e le ritorno al controller
        if(resultSet.isBeforeFirst()){
            resultSet.next();
            String email = resultSet.getString("email");
            String role = resultSet.getString("role");
            user.setEmail(email);
            user.setRole(Role.valueOf(role));
        }else{
            throw new CredentialException("The username or password is wrong");
        }
        return user;
    }
}
