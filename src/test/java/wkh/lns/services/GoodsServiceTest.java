package wkh.lns.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wkh.lns.InputData;
import wkh.lns.dto.Basket;
import wkh.lns.dto.ItemResponse;
import wkh.lns.models.Item;
import wkh.lns.models.enums.GoodsOrigin;
import wkh.lns.models.enums.GoodsType;
import wkh.lns.models.enums.MeasurementUnit;
import wkh.lns.service.TaxCalculator;
import wkh.lns.service.impl.GoodsServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class GoodsServiceTest {

    private List<Item> inputItemList1;
    private GoodsServiceImpl goodsService;
    TaxCalculator taxCalculator;

    @BeforeEach
    public void beforeEach() {
        inputItemList1 = InputData.inputData1();
        taxCalculator = Mockito.mock(TaxCalculator.class);
        goodsService = new GoodsServiceImpl(taxCalculator);
    }

    @Test
    public void addTest() {
        when(taxCalculator.calculate(inputItemList1)).thenReturn(mockDataForInput1());
        Basket basket1 = goodsService.add(inputItemList1);
        assertEquals(basket1.getSaleTax(),new BigDecimal("10.00"));
        assertEquals(basket1.getTotal(), BigDecimal.valueOf(126.98));
    }

    private List<ItemResponse> mockDataForInput1() {
        List<ItemResponse> response = List.of(
                ItemResponse.builder()
                        .measurement(MeasurementUnit.BAG)
                        .origin(GoodsOrigin.LOCAL)
                        .goodsName("Skittles")
                        .type(GoodsType.CANDY)
                        .price(BigDecimal.valueOf(16.00))
                        .build(),
                ItemResponse.builder()
                        .measurement(MeasurementUnit.QUANTITY)
                        .origin(GoodsOrigin.LOCAL)
                        .goodsName("Walkman")
                        .type(GoodsType.OTHER)
                        .price(BigDecimal.valueOf(109.99))
                        .build(),
                ItemResponse.builder()
                        .measurement(MeasurementUnit.BAG)
                        .origin(GoodsOrigin.LOCAL)
                        .goodsName("microwave Popcorn")
                        .type(GoodsType.POPCORN)
                        .price(BigDecimal.valueOf(0.99))
                        .build());
        return response;
    }
}
