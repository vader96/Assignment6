package org.example.Amazon;

import org.example.Amazon.Cost.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmazonIntegrationTest {
    private Amazon amazon;
    private Database database;
    private ShoppingCartAdaptor shoppingCart;

    @BeforeEach
    void setUp() {
        // Sets up the new Database, ShoppingCart, PriceRule and Amazon instance to see whether they all work together.
        database = new Database();
        database.resetDatabase();
        shoppingCart = new ShoppingCartAdaptor(database);
        List<PriceRule> rules = List.of(new RegularCost(), new DeliveryPrice(), new ExtraCostForElectronics());
        amazon = new Amazon(shoppingCart, rules);
    }

    @Test
    @DisplayName("integration tests")
    void testCartStoresAndRetrieveItems() {
        // This test case is to see whether the cart store the items and doesn't mess up the order.
        // Adds the items to the shopping cart.
        Item item1 = new Item(ItemType.OTHER, "Notebook", 1, 5.0);
        Item item2 = new Item(ItemType.ELECTRONIC, "Headphone", 1, 10.0);
        shoppingCart.add(item1);
        shoppingCart.add(item2);

        // Retrieves the items and puts them into a list.
        List<Item> retrievedItems = shoppingCart.getItems();

        // Should be a size of two and checks to see that the order in which they were inserted hasn't changed.
        assertEquals(2, retrievedItems.size());
        assertEquals("Notebook", retrievedItems.get(0).getName());
        assertEquals("Headphone", retrievedItems.get(1).getName());
    }

    @Test
    @DisplayName("integration tests")
    void testAmazonCalculate() {
        // This test case is to see whether Amazon can integrate with real data and whether everything works smoothly.
        // Adds the items to the shoppingCart
        shoppingCart.add(new Item(ItemType.OTHER, "Notebook", 1, 20.0));
        shoppingCart.add(new Item(ItemType.ELECTRONIC, "Phone", 1, 40.0));

        // Calculates the total Cost accounting for everything.
        double expectedRegularCost = (1 * 20.0) + (1 * 40.0);
        double expectedDeliveryCost = 5;
        double expectedElectronicFee = 7.50;
        double expectedTotal = expectedRegularCost + expectedDeliveryCost + expectedElectronicFee;

        // Asserts that the calculated value is accurate to what is expected.
        assertEquals(expectedTotal, amazon.calculate());
    }

    @Test
    @DisplayName("integration tests")
    void testDatabaseClearsShoppingCart() {
        // Test case to help see whether the real database and shopping cart can work together smoothly.
        // Adds the items into the shopping cart.
        shoppingCart.add(new Item(ItemType.OTHER, "Notebook", 1, 20.0));
        shoppingCart.add(new Item(ItemType.ELECTRONIC, "Phone", 1, 40.0));

        // Resets the database, which means that everything should be deleted now from the shopping cart.
        database.resetDatabase();

        // Asserts that the shopping cart doesn't actually have items in it still.
        assertEquals(0, shoppingCart.getItems().size());
    }
}
