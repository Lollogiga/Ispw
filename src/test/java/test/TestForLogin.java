package test;

import com.example.greenpear.dao.UserDao;
import com.example.greenpear.entities.UserProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

class TestForLogin {
    private final String usernameTest = "test";
    private final String passwordTest = "testing1";

    //Verifichiamo che in caso di utente registrato, il login abbia successo:
    @Test
    void testLoginWithValidInput() {
        int res = 0;
        UserProfile userProfile = new UserProfile(usernameTest, passwordTest);
        try{
            UserDao loginDao = new UserDao();
            loginDao.loginUser(userProfile);
            res = 1; //Se il login avviene con successo, il test risulta superato
        } catch (CredentialException | SQLException e) {
            res = 0; //Il test ha avuto insuccesso
        }

        Assertions.assertEquals(1, res);
    }

    //Verifichiamo che in caso di utente non registrato, il login abbia insuccesso:
    @Test
    void testLoginWithInvalidUsername() {
        int res = 0;
        String name = usernameTest + "invalid";
        UserProfile userProfile = new UserProfile(name, passwordTest);
        try{
            UserDao loginDao = new UserDao();
            loginDao.loginUser(userProfile);
            res = 0; //Se il login avviene, il test risulta fallito
        } catch (CredentialException | SQLException e) {
            res = 1; //Il test ha avuto successo
        }

        Assertions.assertEquals(1, res);
    }
}
