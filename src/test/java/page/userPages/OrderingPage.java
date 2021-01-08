package page.userPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.AbstractPage;

public class OrderingPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='field js-field cladr__input']")
    private WebElement cityField;

    @FindBy(xpath = "//*[@class='cart-validation-wrapper__alert']")
    private WebElement addRecording;

    public OrderingPage(WebDriver driver) { super(driver); }

    public OrderingPage openPage()
    {
        waitUntilAjaxCompleted();
        return this;
    }

    public OrderingPage writeCityName(String request){
        cityField.click();
        cityField.sendKeys(request);
        return new OrderingPage(driver);
    }

    public boolean checkCanToConfirmOrder(){
        return cityField.isDisplayed();
    }

}
