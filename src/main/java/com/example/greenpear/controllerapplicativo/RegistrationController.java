package com.example.greenpear.controllerapplicativo;

import com.example.greenpear.bean.RegisterBean;
import com.example.greenpear.dao.RegisterDaoImpl;
import com.example.greenpear.entities.UserProfile;
import com.example.greenpear.utils.Role;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public class RegistrationController {
    private String username;
    private String email;
    private String password;
    private Role role;

    public void registerNewUser(RegisterBean bean) throws SQLException, CredentialException {

        UserProfile userProfile = null;

        username = bean.getUsername();
        email = bean.getEmail();
        password = bean.getPassword();
        role = bean.getRole();

        //Creo l'utente da inviare al Dao
        userProfile = new UserProfile(username, email, password, role);

        try{
            RegisterDaoImpl registerDao = new RegisterDaoImpl();
            registerDao.registerNewUser(userProfile);
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }catch (CredentialException e){
            throw new CredentialException(e.getMessage());
        }
    }

    /*private void registration(Registration newUser) throws  SQLException, CredentialException{
        //Devo usare una dao per ottenere la connessione e registrare l'utente nel sistema:
        RegisterDaoImpl registerDao = new RegisterDaoImpl();

        if(!registerDao.registerNewUser(newUser)){
            //La registrazione ha avuto esisto negativo:
            throw new CredentialException("Email alredy Exists");
        }
        //Altrimenti è andata a buon fine
    }*/
}
