package com.example.greenpear.dao;

import com.example.greenpear.entities.UserProfile;
import com.example.greenpear.utils.Role;
import com.example.greenpear.utils.query.LoginQuery;
import com.example.greenpear.utils.query.RegistrationQuery;

import javax.security.auth.login.CredentialException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private java.sql.Connection connection = null;
    private PreparedStatement preparedStatement;
    //Valore di ritorno query:
    private ResultSet resultSet;

    public UserDao() throws SQLException {
        //Apro una connessione o prendo quella già esistente
        connection = Connection.getInstance();
    }

    public UserProfile loginUser(UserProfile user) throws SQLException, CredentialException {

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

    public void registerNewUser(UserProfile newUser) throws SQLException, CredentialException {
        //Prima di registrare un nuovo utente devo verificare se l'email esiste già
        if(emailExists(newUser)) {
            throw new CredentialException("Email alredy exists");
        }else if(usernameExist(newUser)) {
            throw new CredentialException("Username alredy exists");
        }else{

            //Se non esiste, lo registro:
            preparedStatement = connection.prepareStatement(RegistrationQuery.addUser());
            preparedStatement.setString(1, newUser.getUsername());
            preparedStatement.setString(2, newUser.getEmail());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setInt(4, newUser.getRole().getId());
            preparedStatement.executeUpdate();
        }
    }

    private boolean usernameExist(UserProfile newUser) throws SQLException{
        preparedStatement = connection.prepareStatement(RegistrationQuery.verifyUsernameExists());
        preparedStatement.setString(1, newUser.getUsername());
        resultSet = preparedStatement.executeQuery();
        //IsBeforeFirst ritorna true se l'email esiste già:
        return resultSet.isBeforeFirst();
    }


    private boolean emailExists(UserProfile newUser) throws SQLException {
        preparedStatement = connection.prepareStatement(RegistrationQuery.verifyEmailExists());
        preparedStatement.setString(1,newUser.getEmail());
        resultSet = preparedStatement.executeQuery();
        //IsBeforeFirst ritorna true se l'email esiste già:
        return resultSet.isBeforeFirst();
    }
}
