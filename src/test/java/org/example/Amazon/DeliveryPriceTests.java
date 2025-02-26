package org.example.Amazon;

import org.example.Amazon.Cost.DeliveryPrice;
import org.example.Amazon.Cost.ItemType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryPriceTests {
    private final DeliveryPrice deliveryPrice = new DeliveryPrice();

    @Test
    @DisplayName("structural-based")
    void testFourToTenItems() {
        List<Item> items = List.of(
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00)
        );
        assertEquals(12.5, deliveryPrice.priceToAggregate(items));
    }

    @Test
    @DisplayName("structural-based")
    void testMoreThanTenItems() {
        // Create a list of ten books
        List<Item> items = List.of(
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00),
                new Item(ItemType.OTHER, "Book", 4, 5.00)
                );
        assertEquals(20.0, deliveryPrice.priceToAggregate(items));
    }
}
