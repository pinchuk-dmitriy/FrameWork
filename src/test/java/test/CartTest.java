package test;

import model.User;
import org.testng.annotations.Test;
import page.LacosteHomePage;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import model.Item;
import service.ProductCreater;
import service.UserCreater;


public class CartTest extends TestBase {

    @Test
    public void AddingOneProductToCart() {
        Item expectedItem = ProductCreater.withCredentialsFromProperty("first");

        Item item = new LacosteHomePage(driver)
                .openPage()
                .searchProduct(expectedItem.getName())
                .selectSearchedProduct()
                .selectProductSize()
                .addProductToBag()
                .goToBag()
                .getItem(1);

        assertThat(item, equalTo(expectedItem));
    }

    @Test
    public void PlacingAnOrderForGoodsWithIncorrectData() {
        User testUser = UserCreater.withCredentialsFromProperty();
        Item expectedItem = ProductCreater.withCredentialsFromProperty("first");
        boolean resultOfTest = true;
        boolean resultOfPlacingGoods = new LacosteHomePage(driver)
                .openPage()
                .goToLoginPage()
                .logInAccount(testUser)
                .goToHomePage()
                .searchProduct(expectedItem.getName())
                .selectSearchedProduct()
                .selectProductSize()
                .addProductToBag()
                .goToBag()
                .goToOrderingPage()
                .writeCityName("asdfasd")
                .checkCanToConfirmOrder();

        assertThat(resultOfTest, equalTo(resultOfPlacingGoods));
    }

    @Test
    public void DeletingProductFromBag() {
        Item expectedItem = ProductCreater.withCredentialsFromProperty("first");
        boolean resultOfTest = true;

        boolean nullBagOrNot = new LacosteHomePage(driver)
                .openPage()
                .searchProduct(expectedItem.getName())
                .selectSearchedProduct()
                .selectProductSize()
                .addProductToBag()
                .goToBag()
                .deleteItem()
                .checkIsNullBag();

        assertThat(resultOfTest, equalTo(nullBagOrNot));
    }

    @Test
    public void AddingALotProductToCart() {
        Item expectedItem = ProductCreater.withCredentialsFromProperty("first");
        boolean resultOfTest = false;

        boolean priceBagAndItemPrice = new LacosteHomePage(driver)
                .openPage()
                .searchProduct(expectedItem.getName())
                .selectSearchedProduct()
                .selectProductSize()
                .addProductToBag()
                .goToBag()
                .addManyProducts()
                .equalItemPriceAndBagPrice(expectedItem.getCost());

        assertThat(resultOfTest, equalTo(priceBagAndItemPrice));
    }

    @Test
    public void WriteIncorrectPromocode() {
        Item expectedItem = ProductCreater.withCredentialsFromProperty("first");
        boolean resultOfTest = true;

        boolean correctPromocodeOrNot = new LacosteHomePage(driver)
                .openPage()
                .searchProduct(expectedItem.getName())
                .selectSearchedProduct()
                .selectProductSize()
                .addProductToBag()
                .goToBag()
                .writePromocode("asdfasdf");

        assertThat(resultOfTest, equalTo(correctPromocodeOrNot));
    }

    @Test
    public void WriteIncorrectCityInDeliveryAdress() {
        Item expectedItem = ProductCreater.withCredentialsFromProperty("first");
        boolean resultOfTest = true;

        boolean correctCityOrNot = new LacosteHomePage(driver)
                .openPage()
                .searchProduct(expectedItem.getName())
                .selectSearchedProduct()
                .goToDeliveryPage()
                .writeIncorrectCity("asdfasdf");

        assertThat(resultOfTest, equalTo(correctCityOrNot));
    }

    @Test
    public void ProductFilteringBySelectedCategories() {
        Item expectedItem = ProductCreater.withCredentialsFromProperty("second");

        String nameOfTheFilteredProduct = new LacosteHomePage(driver)
                .openPage()
                .goToMansProducts()
                .selectCategories()
                .goToFirstProductOnPage()
                .nameRightFiltration();

        assertThat(expectedItem.getName(), equalTo(nameOfTheFilteredProduct));
    }

    @Test
    public void ConsoteManager() {
        User testUser = UserCreater.withCredentialsFromProperty();
        boolean resultOfTest = true;

        boolean resultOfPlacingGoods = new LacosteHomePage(driver)
                .openPage()
                .goToLoginPage()
                .logInAccount(testUser)
                .goToHomePage()
                .writeMessageInConsole("Hello");

        assertThat(resultOfTest, equalTo(resultOfPlacingGoods));
    }
}
