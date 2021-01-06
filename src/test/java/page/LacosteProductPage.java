package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import service.TestDataReader;

public class LacosteProductPage extends AbstractPage {
    private static final String PAGE_LINK = TestDataReader.getTestData("test.data.first.link");

    @FindBy(xpath = "//*[@id=\"js-sizes\"]/div[2]/a[2]")
    private WebElement productSize;

    @FindBy(xpath = "//*[@id=\"add-to-cartspan\"]/button[1]")
    private WebElement addProductToBagButton;

    @FindBy(xpath = "//*[@id=\"atc-ctn\"]/div/section[2]/a")
    private WebElement goToBagButton;

    @FindBy(xpath = "//*[@id=\"js-sku-product\"]/div/section/div/div/div[4]/div/div[2]/div/div[1]/ul/li[1]/a")
    private WebElement deliveryButton;

    @FindBy(xpath = "//*[@id=\"js-sku-product\"]/div/section[1]/div/div/div[2]/div/div/div/h1/span")
    private WebElement productName;


    public LacosteProductPage(WebDriver driver)
    {
        super(driver);
    }

    @Override
    protected LacosteProductPage openPage() {
        return this;
    }

    public LacosteProductPage selectProductSize()
    {
        waitUntilElementIsClickableAndClickAvoidModalWindow(productSize);
        productSize.click();
        return this;
    }

    public LacosteProductPage addProductToBag(){
        waitUntilElementIsClickable(addProductToBagButton);
        addProductToBagButton.click();
        return this;
    }

    public LacosteBagPage goToBag()
    {
        waitUntilVisibilityOf(goToBagButton);
        goToBagButton.click();
        return new LacosteBagPage(driver);
    }

    public LacosteDeliveryPage goToDeliveryPage(){
        waitUntilElementIsClickable(deliveryButton);
        deliveryButton.click();
        return new LacosteDeliveryPage(driver);
    }

    public String nameRightFiltration(){
        return productName.getText();
    }
}