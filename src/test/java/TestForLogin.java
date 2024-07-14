import com.example.greenpear.dao.UserDao;
import com.example.greenpear.entities.UserProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

public class TestForLogin {
    private final String USERNAMETEST = "test";
    private final String PASSWORDTEST = "testing1";

    //Verifichiamo che in caso di utente registrato, il login abbia successo:
    @Test
    public void testLoginWithValidInput() {
        int res = 0;
        UserProfile userProfile = new UserProfile(USERNAMETEST, PASSWORDTEST);
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
    public void testLoginWithInvalidUsername() {
        int res = 0;
        String name = USERNAMETEST + "invalid";
        UserProfile userProfile = new UserProfile(name, PASSWORDTEST);
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
