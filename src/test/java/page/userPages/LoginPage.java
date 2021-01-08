package page.userPages;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.AbstractPage;
import page.HomePage;

public class LoginPage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://lacoste.ru/";

    @FindBy(xpath = "//*[@id='vee-USER_LOGIN']/input")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='vee-USER_PASSWORD']/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class='column-half']/button")
    private WebElement enterButton;

    @FindBy(xpath = "//*[@class='successCall']/div[3]/span")
    private WebElement dataOfBirth;

    @FindBy(xpath = "//*[@class='link-logo nav-ico nuxt-link-active']")
    private WebElement homePageButton;

    @FindBy(xpath = "//*[@class='editLink']")
    private WebElement editButton;

    @FindBy(xpath = "//*[@class='successCall']/div[5]/span")
    private WebElement cityField;

    @FindBy(xpath = "//*[@class='is-filled field js-field cladr__input']")
    private WebElement nameCity;

    @FindBy(xpath = "//*[@class='cladr__variants-item']")
    private WebElement searchedCity;

    @FindBy(xpath = "//*[@class='btn saveLink']")
    private WebElement addProtertiesButton;

    @FindBy(xpath = "//*[@class='editLink']")
    private WebElement editProtertiesButton;
    public LoginPage(WebDriver driver) { super(driver); }

    public LoginPage openPage()
    {
        driver.navigate().to(HOMEPAGE_URL);
        waitUntilAjaxCompleted();
        return this;
    }

    public LoginPage logInAccount(User user){
        waitUntilVisibilityOf(emailField);
        emailField.sendKeys(user.getEmail());

        waitUntilVisibilityOf(passwordField);
        passwordField.sendKeys(user.getPassword());

        waitUntilElementIsClickable(enterButton);
        enterButton.click();

        return new LoginPage(driver);
    }

    public HomePage goToHomePage(){
        homePageButton.click();
        return new HomePage(driver);
    }

    public String checkCorrectData(){
        waitUntilVisibilityOf(dataOfBirth);
        return dataOfBirth.getText();
    }

    public boolean writeNewName(String newNameCity){

        waitUntilVisibilityOf(editProtertiesButton);
        editProtertiesButton.click();
        nameCity.click();
        nameCity.clear();
        nameCity.sendKeys(newNameCity);
        waitUntilVisibilityOf(searchedCity);
        searchedCity.click();
        boolean isClickedButton = addProtertiesButton.isDisplayed();
        addProtertiesButton.click();
        return isClickedButton;
    }

}
