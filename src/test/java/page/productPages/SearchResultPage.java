package page.productPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.AbstractPage;

public class SearchResultPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='search-result-items']/li[2]/a")
    private WebElement searchProduct;

    public SearchResultPage(WebDriver driver) { super(driver); }

    @Override
    protected SearchResultPage openPage() {
        return this;
    }

    public ProductPage selectSearchedProduct()
    {
        waitUntilElementIsClickable(searchProduct);
        searchProduct.click();
        return new ProductPage(driver);
    }

}
