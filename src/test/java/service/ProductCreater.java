package service;

import model.Item;

import static util.Resolver.resolveTemplate;

public class ProductCreater {
    private static final String ITEM_NAME = "test.data.%s.name";
    private static final String ITEM_SIZE = "test.data.%s.size";
    private static final String ITEM_COLOR = "test.data.%s.color";
    private static final String ITEM_COST = "test.data.%s.cost";


    public static Item withCredentialsFromProperty(String orderNumber){
        orderNumber = orderNumber.toLowerCase();

        String itemName = resolveTemplate(ITEM_NAME, orderNumber);
        String itemSize = resolveTemplate(ITEM_SIZE, orderNumber);
        String itemColor = resolveTemplate(ITEM_COLOR, orderNumber);
        String itemCost = resolveTemplate(ITEM_COST, orderNumber);

            return Item.of(TestDataReader.getTestData(itemName),
                    TestDataReader.getTestData(itemSize),
                    TestDataReader.getTestData(itemColor),
                    TestDataReader.getTestData(itemCost));
    }
}
