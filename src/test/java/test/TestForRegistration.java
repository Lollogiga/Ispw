package test;

import com.example.greenpear.dao.UserDao;
import com.example.greenpear.entities.UserProfile;
import com.example.greenpear.utils.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

class TestForRegistration {
    private final String usernameTest = "test";
    private final String emailTest = "@gmail.com";
    private final String passwordTest = "testing1";

    //Testiamo che in caso di valori invalidi (username già esistente) venga generata l'eccezione "CredentialException":
    @Test
    void testRegistrationWithInvalidUsername() {
        int res = 0;
        UserProfile userProfile = new UserProfile(usernameTest, generateRandomEmail(), passwordTest, randomRole());
        try{
            UserDao registerDao = new UserDao();
            registerDao.registerNewUser(userProfile);
            res = 0; //Se la registrazione avviene con successo, il test risulta fallito
        } catch (CredentialException e) {
            res = 1; //Il test ha avuto successo
        } catch (SQLException e) {
            res = 0; //Si verifica un'eccezione diversa, il test risulta fallito
        }
        Assertions.assertEquals(1, res);
    }

    //Testiamo che per valori validi, la registrazione va a buon fine:
    @Test
     void testRegistrationWithValidInput() {
        int res = 0;
        UserProfile userProfile = new UserProfile(generateRandomUsername(), generateRandomEmail(), passwordTest, randomRole());
        try{
            UserDao registerDao = new UserDao();
            registerDao.registerNewUser(userProfile);
            //Se la registrazione va a buon fine:
            res = 1;
        } catch (SQLException | CredentialException e) {
            res = 0;
        }
        Assertions.assertEquals(1, res);
    }


    private Role randomRole() {
        if (System.currentTimeMillis() % 2 == 0) {
            return Role.DIETITIAN;
        } else{
            return Role.PATIENT;
        }
    }

    private String generateRandomUsername(){
        return usernameTest + System.currentTimeMillis();
    }

    private String generateRandomEmail() {
        return usernameTest + System.currentTimeMillis() + emailTest;
    }


}
