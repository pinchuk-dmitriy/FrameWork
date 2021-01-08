package page.productPages;

import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.AbstractPage;
import page.userPages.OrderingPage;

import static util.Resolver.resolveTemplate;

public class BagPage extends AbstractPage {
    public static final String HOMEPAGE_URL = "https://www.calvinklein.us/AjaxOrderItemDisplayView";

    String itemNameTemplate = "//*[@class='cart-product-name']";
    String itemCostTemplate = "//*[@class='cart-sale-price']";
    String itemSizeTemplate = "//*[@class='item-size']";
    String itemColorTemplate = "//*[@class='item-color']";

    @FindBy(xpath = "//*[@class='btn btn-full btn-new btn-checkout btngocheckout js-validate-cart']")
    private WebElement orderingButton;

    @FindBy(xpath = "//*[@class='js-remove-lineitem cart-product-action']")
    private WebElement deleteItemButton;

    @FindBy(xpath = "//*[@class='cart-empty-title']")
    private WebElement nullBagLabel;

    @FindBy(xpath = "//*[@class='cart-product-quantity']/select")
    private WebElement selectManyProductsButton;

    @FindBy(xpath = "//*[@value='2']")
    private WebElement selectTwoProductsButton;

    @FindBy(xpath = "//*[@class='cart-price-total']/span[2]")
    private WebElement priceOfProducts;

    @FindBy(xpath = "//*[@class='checkout-input js-field']")
    private WebElement writePromocodeField;

    @FindBy(xpath = "//*[@class='promocode-message__delete']")
    private WebElement canselFailButton;

    @Override
    protected BagPage openPage() {
        return this;
    }

    public BagPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Item getItem(int number) {
        WebElement itemName = waitUntilPresenceOfElement(By.xpath(resolveTemplate(itemNameTemplate, number)));
        WebElement itemCost = driver.findElements(By.xpath(resolveTemplate(itemCostTemplate, number))).get(number - 1);
        WebElement itemSize = driver.findElements(By.xpath(resolveTemplate(itemSizeTemplate, number))).get(number - 1);
        WebElement itemColor = driver.findElements(By.xpath(resolveTemplate(itemColorTemplate, number))).get(number - 1);

        String name = itemName.getText();
        String size = itemSize.getText().toLowerCase();
        String color = itemColor.getText().toLowerCase();
        String cost = itemCost.getText().toLowerCase();

        return Item.of(name, size, color, cost);
    }

    public OrderingPage goToOrderingPage(){
        orderingButton.click();
        return new OrderingPage(driver);
    }

    public BagPage deleteItem(){
        deleteItemButton.click();
        return new BagPage(driver);
    }

    public boolean checkIsNullBag(){
        waitUntilVisibilityOf(nullBagLabel);
        return nullBagLabel.isDisplayed();
    }

    public BagPage addManyProducts(){
        selectManyProductsButton.click();
        waitUntilVisibilityOf(selectTwoProductsButton);
        selectTwoProductsButton.click();
        return new BagPage(driver);
    }

    public boolean equalItemPriceAndBagPrice(String itemCost){
        return itemCost.equals(priceOfProducts.getText());
    }

    public boolean writePromocode(String promocode){
        writePromocodeField.click();
        writePromocodeField.sendKeys(promocode);
        writePromocodeField.sendKeys(Keys.ENTER);
        waitUntilVisibilityOf(canselFailButton);
        return canselFailButton.isDisplayed();
    }

}