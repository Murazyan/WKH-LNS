package wkh.lns.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wkh.lns.InputData;
import wkh.lns.dto.ItemDto;
import wkh.lns.mapper.ItemMapper;
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
        taxCalculator = new TaxCalculatorImpl(new ItemMapper());
    }

    @Test
    public void calculateTest() {
        List<ItemDto> itemDto1 = taxCalculator.calculate(inputItemList1);
        List<ItemDto> itemDto2 = taxCalculator.calculate(inputItemList2);

        assertEquals(itemDto1.size(), 3);
        assertEquals(itemDto2.size(), 2);

        pricesCheckForInput1(itemDto1);
        pricesCheckForInput2(itemDto2);
    }

    private void pricesCheckForInput1(List<ItemDto> itemDto1) {
        assertEquals(itemDto1.get(0).getPrice(), BigDecimal.valueOf(16.00));
        assertEquals(itemDto1.get(1).getPrice(), BigDecimal.valueOf(109.99));
        assertEquals(itemDto1.get(2).getPrice(), BigDecimal.valueOf(0.99));
    }

    private void pricesCheckForInput2(List<ItemDto> itemDto2) {
        assertEquals(itemDto2.get(0).getPrice(), BigDecimal.valueOf(11.55));
        assertEquals(itemDto2.get(1).getPrice(), BigDecimal.valueOf(17251.44));
    }
}
