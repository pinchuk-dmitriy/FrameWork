package page.productPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.AbstractPage;

public class DeliveryPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='cladr__input']")
    private WebElement cityField;

    @FindBy(xpath = "//*[@class='delivery-calculator__plug']")
    private WebElement stateCityField;

    public DeliveryPage(WebDriver driver) { super(driver); }

    public DeliveryPage openPage()
    {
        waitUntilAjaxCompleted();
        return this;
    }

    public boolean writeIncorrectCity(String city){
        waitUntilVisibilityOf(cityField);
        cityField.click();
        cityField.sendKeys(city);
        cityField.sendKeys(Keys.ENTER);
        return stateCityField.isDisplayed();
    }

}
