package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LacosteHomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://lacoste.ru/";

    @FindBy(xpath = "//*[@id=\"search-render\"]/form/input[1]")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id=\"topbar\"]/div/ul[3]/li[3]/section/a")
    private WebElement goToLoginPageButton;

    @FindBy(xpath = "//*[@id=\"sidenav\"]/div/div/ul[1]/li[1]/section/a/span")
    private WebElement menProductsButton;

    @FindBy(xpath = "//*[@id=\"sidenav\"]/div/div/ul[1]/li[1]/section/div/div/div[1]/ul/li[1]/ul/li[5]/h4/a")
    private WebElement parisPoloClothes;

    @FindBy(xpath = "//*[@class=\"consultant-icon\"]")
    private WebElement chatButton;

    @FindBy(xpath = "//*[@id=\"retailcrm-consultant-app\"]/div[2]/div[3]/div/textarea")
    private WebElement chatTextField;

    @FindBy(xpath = "//*[@id=\"retailcrm-consultant-app\"]/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/div[2]/div/div/div[1]/div")
    private WebElement writedMessage;

    public LacosteHomePage(WebDriver driver) { super(driver); }

    public LacosteHomePage openPage()
    {
        driver.navigate().to(HOMEPAGE_URL);
        waitUntilAjaxCompleted();
        return this;
    }

    public LacosteSearchResultPage searchProduct(String request) {
        searchField.sendKeys(request);
        searchField.sendKeys(Keys.ENTER);
        return new LacosteSearchResultPage(driver);
    }

    public LacosteLoginPage goToLoginPage(){
        goToLoginPageButton.click();
        return new LacosteLoginPage(driver);
    }

    public LacosteMenProductsPage goToMansProducts(){
        menProductsButton.click();
        waitUntilVisibilityOf(parisPoloClothes);
        parisPoloClothes.click();
        return new LacosteMenProductsPage(driver);
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
