package wkh.lns.service.impl;

import wkh.lns.dto.Basket;
import wkh.lns.dto.ItemResponse;
import wkh.lns.models.Item;
import wkh.lns.models.enums.GoodsOrigin;
import wkh.lns.models.enums.MeasurementUnit;
import wkh.lns.service.GoodsService;
import wkh.lns.service.TaxCalculator;

import java.math.BigDecimal;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {

    private final TaxCalculator taxCalculator;

    public GoodsServiceImpl(final TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @Override
    public Basket add(final List<Item> items) {
        final Basket basket = new Basket();
        basket.setItems(taxCalculator.calculate(items));
        var total = basket.getItems().stream()
                .map(ItemResponse::getPrice)    // map
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var purePrice = items.stream()
                .map(item -> item.getGoods().getPrice().multiply(BigDecimal.valueOf(item.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        basket.setTotal(total);
        basket.setSaleTax(total.subtract(purePrice));
        return basket;
    }

    @Override
    public void printBasketData(final Basket basket) {
        basket.getItems().forEach(itemResponse -> {
            var itemLine = "" + itemResponse.getCount();
            itemLine += itemResponse.getOrigin() == GoodsOrigin.IMPORTED ? " imported " : " ";
            itemLine += itemResponse.getMeasurement() != MeasurementUnit.QUANTITY ? itemResponse.getMeasurement() + " of " : " ";
            itemLine += itemResponse.getGoodsName() + ": " + itemResponse.getPrice();
            System.out.println(itemLine);
        });
        System.out.println("Sales Taxes: " + basket.getSaleTax());
        System.out.println("Total: " + basket.getTotal());
    }
}
