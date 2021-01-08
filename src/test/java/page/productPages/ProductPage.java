package page.productPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.AbstractPage;
import service.TestDataReader;

public class ProductPage extends AbstractPage {
    private static final String PAGE_LINK = TestDataReader.getTestData("test.data.first.link");

    @FindBy(xpath = "//*[@id='js-sizes']/div[2]/a[2]")
    private WebElement productSize;

    @FindBy(xpath = "//*[@id='add-to-cartspan']/button[1]")
    private WebElement addProductToBagButton;

    @FindBy(xpath = "//*[@class='atc-cta-show-cart']")
    private WebElement goToBagButton;

    @FindBy(xpath = "//*[@id='js-sku-product']/div/section/div/div/div[4]/div/div[2]/div/div[1]/ul/li[1]/a")
    private WebElement deliveryButton;

    @FindBy(xpath = "//*[@class='sku-product-name']/span")
    private WebElement productName;


    public ProductPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    protected ProductPage openPage() {
        return this;
    }

    public ProductPage selectProductSize()
    {
        waitUntilElementIsClickableAndClickAvoidModalWindow(productSize);
        productSize.click();
        return this;
    }

    public ProductPage addProductToBag(){
        waitUntilElementIsClickable(addProductToBagButton);
        addProductToBagButton.click();
        return this;
    }

    public BagPage goToBag()
    {
        waitUntilVisibilityOf(goToBagButton);
        goToBagButton.click();
        return new BagPage(driver);
    }

    public DeliveryPage goToDeliveryPage(){
        waitUntilElementIsClickable(deliveryButton);
        deliveryButton.click();
        return new DeliveryPage(driver);
    }

    public String nameRightFiltration(){
        return productName.getText();
    }
}