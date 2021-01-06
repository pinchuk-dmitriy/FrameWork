package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LacosteDeliveryPage extends AbstractPage {

    @FindBy(xpath = "//*[@class=\"cladr__input\"]")
    private WebElement cityField;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div/div[4]/div/div[2]")
    private WebElement stateCityField;

    public LacosteDeliveryPage(WebDriver driver) { super(driver); }

    public LacosteDeliveryPage openPage()
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
