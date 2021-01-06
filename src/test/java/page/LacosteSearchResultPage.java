package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LacosteSearchResultPage extends AbstractPage {

    @FindBy(xpath = "//*[@id=\"search-result-items\"]/li[2]/a")
    private WebElement searchProduct;

    public LacosteSearchResultPage(WebDriver driver) { super(driver); }

    @Override
    protected LacosteSearchResultPage openPage() {
        return this;
    }

    public LacosteProductPage selectSearchedProduct()
    {
        waitUntilElementIsClickable(searchProduct);
        searchProduct.click();
        return new LacosteProductPage(driver);
    }

}
