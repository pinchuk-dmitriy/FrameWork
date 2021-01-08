package test;

import model.User;
import org.testng.annotations.Test;
import page.HomePage;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import service.UserCreater;


public class UserTest extends TestBase {
    @Test
    public void LoginUser() {
        User testUser = UserCreater.withCredentialsFromProperty();
        String resultOfTest = new HomePage(driver)
                .openPage()
                .goToLoginPage()
                .logInAccount(testUser)
                .checkCorrectData();

        assertThat(resultOfTest, equalTo(testUser.getDateOfBirth()));
    }

    @Test
    public void EditCityInAccount() {
        User testUser = UserCreater.withCredentialsFromProperty();
        String newCityName = "Москва";
        boolean rightResult = true;
        boolean resultOfTest = new HomePage(driver)
                .openPage()
                .goToLoginPage()
                .logInAccount(testUser)
                .writeNewName(newCityName);

        assertThat(resultOfTest, equalTo(rightResult));
    }

}
