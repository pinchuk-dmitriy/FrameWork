package page;

import model.User;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LacosteOrderingPage extends AbstractPage {

    @FindBy(xpath = "//*[@class=\"btn btn-full btn-new btn-checkout btngocheckout js-validate-cart\"]")
    private WebElement cityField;

    @FindBy(xpath = "//*[@class=\"cart-validation-wrapper__alert\"]")
    private WebElement addRecording;

    public LacosteOrderingPage(WebDriver driver) { super(driver); }

    public LacosteOrderingPage openPage()
    {
        waitUntilAjaxCompleted();
        return this;
    }

    public LacosteOrderingPage writeCityName(String request){
        cityField.click();
        cityField.sendKeys(request);
        return new LacosteOrderingPage(driver);
    }

    public boolean checkCanToConfirmOrder(){
        return cityField.isDisplayed();
    }

}
