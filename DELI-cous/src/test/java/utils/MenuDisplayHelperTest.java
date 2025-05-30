package utils;

import enums.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;


public class MenuDisplayHelperTest {

    /*
     * Test meat pricing for all sizes - regular vs extra
     * Regular: Small=$1.00, Medium=$2.00, Large=$3.00
     * Extra: Small=$0.50, Medium=$1.00, Large=$1.50
     */
    @Test
    void testMeatPricing() throws Exception {
        java.lang.reflect.Method getPrice = MenuDisplayHelper.class.getDeclaredMethod(
                "getPrice", Enum.class, Size.class, boolean.class);
        getPrice.setAccessible(true);

        // Replace with your actual Meat enum value, e.g.: Meat.TURKEY
        Meat meat = Meat.values()[0]; // Uses first meat option

        // Regular meat prices
        assertEquals(new BigDecimal("1.00"), getPrice.invoke(null, meat, Size.SMALL, false));
        assertEquals(new BigDecimal("2.00"), getPrice.invoke(null, meat, Size.MEDIUM, false));
        assertEquals(new BigDecimal("3.00"), getPrice.invoke(null, meat, Size.LARGE, false));

        // Extra meat prices
        assertEquals(new BigDecimal("0.50"), getPrice.invoke(null, meat, Size.SMALL, true));
        assertEquals(new BigDecimal("1.00"), getPrice.invoke(null, meat, Size.MEDIUM, true));
        assertEquals(new BigDecimal("1.50"), getPrice.invoke(null, meat, Size.LARGE, true));
    }

    /**
     * Test cheese pricing for all sizes - regular vs extra
     * Regular: Small=$0.75, Medium=$1.50, Large=$2.25
     * Extra: Small=$0.30, Medium=$0.60, Large=$0.90
     */
    @Test
    void testCheesePricing() throws Exception {
        java.lang.reflect.Method getPrice = MenuDisplayHelper.class.getDeclaredMethod(
                "getPrice", Enum.class, Size.class, boolean.class);
        getPrice.setAccessible(true);

        // Replace with your actual Cheese enum value, e.g.: Cheese.CHEDDAR
        Cheese cheese = Cheese.values()[0]; // Uses first cheese option

        // Regular cheese prices
        assertEquals(new BigDecimal("0.75"), getPrice.invoke(null, cheese, Size.SMALL, false));
        assertEquals(new BigDecimal("1.50"), getPrice.invoke(null, cheese, Size.MEDIUM, false));
        assertEquals(new BigDecimal("2.25"), getPrice.invoke(null, cheese, Size.LARGE, false));

        // Extra cheese prices
        assertEquals(new BigDecimal("0.30"), getPrice.invoke(null, cheese, Size.SMALL, true));
        assertEquals(new BigDecimal("0.60"), getPrice.invoke(null, cheese, Size.MEDIUM, true));
        assertEquals(new BigDecimal("0.90"), getPrice.invoke(null, cheese, Size.LARGE, true));
    }
}