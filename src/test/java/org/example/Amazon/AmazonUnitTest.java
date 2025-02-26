package org.example.Amazon;

import org.example.Amazon.Cost.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class AmazonUnitTest {
    private Amazon amazon;
    private ShoppingCart fakeCart;
    private List<PriceRule> fakeRules;

    @BeforeEach
    void setUp() {
        fakeCart = mock(ShoppingCart.class);
        fakeRules = List.of(mock(RegularCost.class), mock(DeliveryPrice.class), mock(ExtraCostForElectronics.class));
        amazon = new Amazon(fakeCart, fakeRules);
    }

    @Test
    @DisplayName("Unit testing")
    void testAddToCart() {
        // This unit test confirms that the mocked cart added the item to itself.
        Item item = new Item(ItemType.ELECTRONIC, "Phone", 1, 10.0);
        // Adds the item to the cart.
        amazon.addToCart(item);
        // Verifies that the cart added the item.
        verify(fakeCart).add(item);
    }

    @Test
    @DisplayName("Unit Testing")
    void testCalculatePrices() {
        List<Item> fakeItems = List.of(new Item(ItemType.ELECTRONIC, "Tablet", 1, 10.0));
        when(fakeCart.getItems()).thenReturn(fakeItems);
        for (PriceRule rule : fakeRules) {
            when(rule.priceToAggregate(any())).thenReturn(10.0);
        }
        amazon.calculate();
        for (PriceRule rule : fakeRules) {
            verify(rule).priceToAggregate(any());
        }
    }
}
