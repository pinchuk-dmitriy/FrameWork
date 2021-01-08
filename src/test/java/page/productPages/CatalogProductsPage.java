package page.productPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.AbstractPage;
import service.TestDataReader;

public class CatalogProductsPage extends AbstractPage {
    private static final String PAGE_LINK = TestDataReader.getTestData("test.data.first.link");

    @FindBy(xpath = "//*[@index='5']/button")
    private WebElement colorClothesButton;

    @FindBy(xpath = "//*[@id='content-container']/div/div/div/div/div/div/div[2]/section/div/div/div[1]/div[2]/div/div/form/div[6]/div/div/div[1]/label")
    private WebElement beigeColorButton;

    @FindBy(xpath = "//*[@id='search-result-items']/li[1]/a")
    private WebElement firstProduct;


    public CatalogProductsPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    protected CatalogProductsPage openPage() {
        return this;
    }

    public CatalogProductsPage selectCategories(){
        waitUntilElementIsClickable(colorClothesButton);
        colorClothesButton.click();
        waitUntilVisibilityOf(beigeColorButton);
        beigeColorButton.click();
        return new CatalogProductsPage(driver);
    }

    public ProductPage goToFirstProductOnPage(){
        waitUntilVisibilityOf(firstProduct);
        firstProduct.click();
        return new ProductPage(driver);
    }
}