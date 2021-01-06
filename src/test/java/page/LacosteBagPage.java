package page;

import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static util.Resolver.resolveTemplate;

public class LacosteBagPage extends AbstractPage {
    public static final String HOMEPAGE_URL = "https://www.calvinklein.us/AjaxOrderItemDisplayView";

    String itemNameTemplate = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[1]/div[2]/section/div/div/div[2]/a";
    String itemCostTemplate = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[1]/div[2]/section/div/div/div[2]/div[2]/div/span[2]";
    String itemSizeTemplate = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[1]/div[2]/section/div/div/div[2]/div[1]/div[2]";
    String itemColorTemplate = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[1]/div[2]/section/div/div/div[2]/div[1]/div[1]";

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[2]/div[1]/div[2]/a")
    private WebElement orderingButton;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[1]/div[2]/section/div/div/ul/li/span")
    private WebElement deleteItemButton;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[1]/div[2]/h3")
    private WebElement nullBagLabel;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[1]/div[2]/section/div/div/div[2]/div[3]/select")
    private WebElement selectManyProductsButton;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[1]/div[2]/section/div/div/div[2]/div[3]/select/option[2]")
    private WebElement selectTwoProductsButton;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[2]/div[1]/div[1]/div/ul/li[4]/span[2]")
    private WebElement priceOfProducts;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/form/div/input")
    private WebElement writePromocodeField;

    @FindBy(xpath = "//*[@id=\"content-container\"]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div/a")
    private WebElement canselFailButton;

    @Override
    protected LacosteBagPage openPage() {
        return this;
    }

    public LacosteBagPage(WebDriver driver){
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

    public LacosteOrderingPage goToOrderingPage(){
        orderingButton.click();
        return new LacosteOrderingPage(driver);
    }

    public LacosteBagPage deleteItem(){
        deleteItemButton.click();
        return new LacosteBagPage(driver);
    }

    public boolean checkIsNullBag(){
        waitUntilVisibilityOf(nullBagLabel);
        return nullBagLabel.isDisplayed();
    }

    public LacosteBagPage addManyProducts(){
        selectManyProductsButton.click();
        waitUntilVisibilityOf(selectTwoProductsButton);
        selectTwoProductsButton.click();
        return new LacosteBagPage(driver);
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