package wkh.lns.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wkh.lns.InputData;
import wkh.lns.dto.ItemResponse;
import wkh.lns.models.Item;
import wkh.lns.service.impl.TaxCalculatorImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxCalculatorTest {

    private List<Item> inputItemList1;
    private List<Item> inputItemList2;
    private TaxCalculatorImpl taxCalculator;

    @BeforeEach
    public void beforeEach() {
        inputItemList1 = InputData.inputData1();
        inputItemList2 = InputData.inputData2();
        taxCalculator = new TaxCalculatorImpl();
        taxCalculator.BASIC_TAX = 10;
        taxCalculator.IMPORTED_TAX = 5;
    }

    @Test
    public void calculateTest() {
        List<ItemResponse> itemResponse1 = taxCalculator.calculate(inputItemList1);
        List<ItemResponse> itemResponse2 = taxCalculator.calculate(inputItemList2);

        assertEquals(itemResponse1.size(), 3);
        assertEquals(itemResponse2.size(), 2);

        pricesCheckForInput1(itemResponse1);
        pricesCheckForInput2(itemResponse2);
    }

    private void pricesCheckForInput1(List<ItemResponse> itemResponse1) {
        assertEquals(itemResponse1.get(0).getPrice(), BigDecimal.valueOf(16.00));
        assertEquals(itemResponse1.get(1).getPrice(), BigDecimal.valueOf(109.99));
        assertEquals(itemResponse1.get(2).getPrice(), BigDecimal.valueOf(0.99));
    }

    private void pricesCheckForInput2(List<ItemResponse> itemResponse2) {
        assertEquals(itemResponse2.get(0).getPrice(), BigDecimal.valueOf(11.55));
        assertEquals(itemResponse2.get(1).getPrice(), BigDecimal.valueOf(17251.44));
   }
}
