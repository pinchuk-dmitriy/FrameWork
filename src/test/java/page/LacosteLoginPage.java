package page;

import model.User;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LacosteLoginPage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://lacoste.ru/";

    @FindBy(xpath = "//*[@id=\"vee-USER_LOGIN\"]/input")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"vee-USER_PASSWORD\"]/input")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div/div/div[3]/div[1]/button")
    private WebElement enterButton;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/section/div[2]/section/section[1]/div/div/div/div[3]/span")
    private WebElement dataOfBirth;

    @FindBy(xpath = "//*[@id=\"sidenav\"]/div/div/div/a")
    private WebElement homePageButton;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/section/div[2]/section/section[1]/div/div/div/a")
    private WebElement editButton;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/section/div[2]/section/section[1]/div/div/div/div[5]/span")
    private WebElement cityField;

    @FindBy(xpath = "//*[@class=\"is-filled field js-field cladr__input\"]")
    private WebElement nameCity;

    @FindBy(xpath = "//*[@id=\"vee-changedCityValidator\"]/div[2]/div[2]/div[2]/div")
    private WebElement searchedCity;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/section/div[2]/section/section[1]/div/div[1]/div[2]/div/div/form/div[14]/button[1]")
    private WebElement addProtertiesButton;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/section/div[2]/section/section[1]/div/div/div/a")
    private WebElement editProtertiesButton;
    public LacosteLoginPage(WebDriver driver) { super(driver); }

    public LacosteLoginPage openPage()
    {
        driver.navigate().to(HOMEPAGE_URL);
        waitUntilAjaxCompleted();
        return this;
    }

    public LacosteLoginPage logInAccount(User user){
        waitUntilVisibilityOf(emailField);
        emailField.sendKeys(user.getEmail());

        waitUntilVisibilityOf(passwordField);
        passwordField.sendKeys(user.getPassword());

        waitUntilElementIsClickable(enterButton);
        enterButton.click();

        return new LacosteLoginPage(driver);
    }

    public LacosteHomePage goToHomePage(){
        homePageButton.click();
        return new LacosteHomePage(driver);
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
