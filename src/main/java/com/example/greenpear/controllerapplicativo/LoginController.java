package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.dao.LoginDaoImpl;
import com.example.greenpear.dao.RegisterDaoImpl;
import com.example.greenpear.entities.Session;
import com.example.greenpear.entities.UserProfile;
import com.example.greenpear.utils.Role;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public class LoginController {
    private String username;
    private String password;

    private Role role;

    public void loginUser(LoginBean bean) throws SQLException, CredentialException{

        UserProfile userProfile = null;

        username = bean.getUsername();
        password = bean.getPassword();

        //Creo l'utente da inviare al Dao:
        userProfile = new UserProfile(username, password);

        try{
            LoginDaoImpl loginDao = new LoginDaoImpl();
            loginDao.loginUser(userProfile);

            //Se tutto va a buon fine l'utente è loggato e possiamo creare una sessione:
            bean.setRole(userProfile.getRole()); //Il graphic controller deve coscere il ruolo
            Session session = Session.getInstance();
            session.setUserProfile(userProfile);
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }catch (CredentialException e){
            throw new CredentialException(e.getMessage());
        }



    }
}
