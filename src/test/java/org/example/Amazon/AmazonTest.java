package org.example.Amazon;

import org.example.Amazon.Cost.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AmazonTest {
    private Amazon amazon;
    private ShoppingCart mockCart;
    private List<PriceRule> rules;

    @BeforeEach
    void setUp() {
        mockCart = mock(ShoppingCart.class);        // Sets up the mockCart each time
        rules = List.of(new RegularCost(), new DeliveryPrice(), new ExtraCostForElectronics());
        amazon = new Amazon(mockCart, rules);
    }

    @Test
    @DisplayName("specification-based")
    void testCalculateTotalPriceWithRules() {
        // This test method is to test whether the calculate() method correctly applies the PriceRules and returns the correct totalPrice.
        List<Item> items = List.of(
                new Item(ItemType.OTHER, "Book", 2, 15.00),
                new Item(ItemType.ELECTRONIC, "Laptop", 1, 20.00)
        );
        when(mockCart.getItems()).thenReturn(items);
        double regularCost = (2 * 15.00) + (1 * 20.00);     // Tests class regularCost
        double deliveryCost = 5;                            // DeliveryCost is 5 b/c of 3 items.
        double extraElectronicCost = 7.50;                  // Only one electronic device.
        double expectedPrice = regularCost + deliveryCost + extraElectronicCost;
        assertEquals(expectedPrice, amazon.calculate());    // Checks to see that the method calculate() correctly applied the PriceRules.
    }

    @Test
    @DisplayName("specification-based")
    void testAddToCartWithEmpty() {
        // This test method is to ensure that when calculate() gets called, it handles the empty cart correctly.
        when(mockCart.getItems()).thenReturn(List.of());
        // Verifies that the empty cart correctly returns 0.0.
        assertEquals(0.0, amazon.calculate());
    }

    @Test
    @DisplayName("structural-based")
    void testAddToCart() {
        // This test method is to ensure that the cart can add items to it.
        Item item = new Item(ItemType.ELECTRONIC, "Laptop", 1, 1000.00);
        amazon.addToCart(item);
        // Verifies that the mockCart added the item to itself.
        verify(mockCart, times(1)).add(item);
    }
}
