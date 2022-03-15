package wkh.lns.service.impl;

import wkh.lns.dto.BasketDto;
import wkh.lns.models.enums.GoodsOrigin;
import wkh.lns.models.enums.MeasurementUnit;
import wkh.lns.service.BasketPrinter;

public class BasketPrinterImpl implements BasketPrinter {


    @Override
    public void print(final BasketDto basket) {
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
