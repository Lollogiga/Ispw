package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.dao.UserDao;
import com.example.greenpear.entities.UserProfile;
import com.example.greenpear.utils.Role;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public class LoginController {
    private String username;
    private String password;

    private Role role;

    public LoginBean loginUser(LoginBean bean) throws SQLException, CredentialException{

        UserProfile userProfile = null;

        username = bean.getUsername();
        password = bean.getPassword();

        //Creo l'utente da inviare al Dao:
        userProfile = new UserProfile(username, password);

        try{
            UserDao loginDao = new UserDao();
            loginDao.loginUser(userProfile);

            //Se tutto va a buon fine l'utente è loggato e possiamo creare una sessione:
            bean.setRole(userProfile.getRole()); //Il graphic controller deve coscere il ruolo
            return bean;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }catch (CredentialException e){
            throw new CredentialException(e.getMessage());
        }



    }
}
