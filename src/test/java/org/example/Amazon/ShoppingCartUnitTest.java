package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ShoppingCartUnitTest {
    private ShoppingCartAdaptor shoppingCart;
    private Database fakeDatabase;

    @BeforeEach
    void setUp() {
        // Creates the mocked version of the database.
        fakeDatabase = mock(Database.class);
        shoppingCart = new ShoppingCartAdaptor(fakeDatabase);
    }

    @Test
    @DisplayName("Unit Testing")
    void testAddExecutesSql() {
        // Creates the item.
        Item item = new Item(ItemType.OTHER, "Notebook", 1, 5.0);
        // Mocks the database and stubs the withSql to return null for anything.
        when(fakeDatabase.withSql(any())).thenReturn(null);
        // Adds the item to the shopping cart.
        shoppingCart.add(item);
        // Verifies to see that the fakeDatabase was called for the method.
        verify(fakeDatabase).withSql(any());  // Ensure it was called
    }

    @Test
    @DisplayName("Unit testing")
    void testGetItemsExecutesSql() {
        List<Item> fakeItems = List.of(new Item(ItemType.OTHER, "Notebook", 1, 5.0));
        // Mocks the database and stubs the withSql to return the fakeItems for any call.
        when(fakeDatabase.withSql(any())).thenReturn(fakeItems);
        // Gets the items in the shopping cart, which will return the fakeItems.
        List<Item> items = shoppingCart.getItems();
        // Asserts to check that the contents in the Item object are true and haven't changed.
        assertEquals(1, items.size());
        assertEquals("Notebook", items.get(0).getName());
    }
}
