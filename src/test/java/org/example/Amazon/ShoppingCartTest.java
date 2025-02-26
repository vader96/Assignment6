package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {
    private ShoppingCartAdaptor shoppingCart;
    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();      // Creates the database
        database.resetDatabase();       // Resets the database.
        shoppingCart = new ShoppingCartAdaptor(database);
    }

    @Test
    @DisplayName("specification-based")
    void testAddItem() {
        // Ensures that the items can be added to the cart
        Item item = new Item(ItemType.OTHER, "Notebook", 2, 10.0);
        shoppingCart.add(item);
        // Verifies that the item was added to the cart.
        assertEquals(1, shoppingCart.getItems().size());
    }

    @Test
    @DisplayName("specification-based")
    void testNumberOfItemsMethod() {
        Item item1 = new Item(ItemType.ELECTRONIC, "Mouse", 1, 25.00);
        Item item2 = new Item(ItemType.OTHER, "Notebook", 1, 10.00);
        shoppingCart.add(item1);
        shoppingCart.add(item2);

        assertEquals(2, shoppingCart.numberOfItems());
    }

    @Test
    @DisplayName("structural-based")
    void testGetItemsInCart() {
        Item item1 = new Item(ItemType.OTHER, "Book", 1, 15.0);
        Item item2 = new Item(ItemType.ELECTRONIC, "Phone", 1, 500.0);
        shoppingCart.add(item1);
        shoppingCart.add(item2);

        assertEquals(2, shoppingCart.getItems().size(), "Cart should contain two items.");
    }
}
