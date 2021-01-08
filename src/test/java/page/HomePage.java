package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.productPages.CatalogProductsPage;
import page.productPages.SearchResultPage;
import page.userPages.LoginPage;

public class HomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://lacoste.ru/";

    @FindBy(xpath = "//*[@itemprop='query-input']")
    private WebElement searchField;

    @FindBy(xpath = "//*[@class='no-submenu topbar-item']")
    private WebElement goToLoginPageButton;

    @FindBy(xpath = "//*[@id='sidenav']/div/div/ul[1]/li[1]/section/a/span")
    private WebElement menProductsButton;

    @FindBy(xpath = "//*[@href='/catalog/paris_polo_muzhchiny/']")
    private WebElement parisPoloClothes;

    @FindBy(xpath = "//*[@class='consultant-icon']/div")
    private WebElement chatButton;

    @FindBy(xpath = "//*[@class='form__input-area']")
    private WebElement chatTextField;

    @FindBy(xpath = "//*[@class='message-content']")
    private WebElement writedMessage;

    public HomePage(WebDriver driver) { super(driver); }

    public HomePage openPage()
    {
        driver.navigate().to(HOMEPAGE_URL);
        waitUntilAjaxCompleted();
        return this;
    }

    public SearchResultPage searchProduct(String request) {
        searchField.sendKeys(request);
        searchField.sendKeys(Keys.ENTER);
        return new SearchResultPage(driver);
    }

    public LoginPage goToLoginPage(){
        goToLoginPageButton.click();
        return new LoginPage(driver);
    }

    public CatalogProductsPage goToMansProducts(){
        menProductsButton.click();
        waitUntilVisibilityOf(parisPoloClothes);
        parisPoloClothes.click();
        return new CatalogProductsPage(driver);
    }

    public boolean writeMessageInConsole(String message){
        chatButton.click();
        waitUntilVisibilityOf(chatTextField);
        chatTextField.click();
        chatTextField.sendKeys(message);
        chatTextField.sendKeys(Keys.ENTER);
        waitUntilVisibilityOf(writedMessage);
        return writedMessage.isDisplayed();
    }
}
