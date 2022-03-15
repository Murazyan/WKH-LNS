package wkh.lns;

import wkh.lns.models.Goods;
import wkh.lns.models.Item;
import wkh.lns.models.enums.GoodsOrigin;
import wkh.lns.models.enums.GoodsType;
import wkh.lns.models.enums.MeasurementUnit;

import java.math.BigDecimal;
import java.util.List;

public class InputData {

    public static List<Item> inputData1() {

        List<Item> items = List.of(
                new Item(1, Goods.builder()
                        .measurement(MeasurementUnit.BAG)
                        .origin(GoodsOrigin.LOCAL)
                        .name("Skittles")
                        .type(GoodsType.CANDY)
                        .price(BigDecimal.valueOf(16.00))
                        .build()),
                new Item(1, Goods.builder()
                        .measurement(MeasurementUnit.QUANTITY)
                        .origin(GoodsOrigin.LOCAL)
                        .name("Walkman")
                        .type(GoodsType.OTHER)
                        .price(BigDecimal.valueOf(99.99))
                        .build()),
                new Item(1, Goods.builder()
                        .measurement(MeasurementUnit.BAG)
                        .origin(GoodsOrigin.LOCAL)
                        .name("microwave Popcorn")
                        .type(GoodsType.POPCORN)
                        .price(BigDecimal.valueOf(0.99))
                        .build()));
        return items;
    }

    public static List<Item> inputData2() {
        List<Item> items = List.of(
                new Item(1, Goods.builder()
                        .measurement(MeasurementUnit.BAG)
                        .origin(GoodsOrigin.IMPORTED)
                        .name("Vanilla-Hazelnut Coffee")
                        .type(GoodsType.COFFEE)
                        .price(BigDecimal.valueOf(11.00))
                        .build()),
                new Item(1, Goods.builder()
                        .measurement(MeasurementUnit.QUANTITY)
                        .origin(GoodsOrigin.IMPORTED)
                        .name("Vespa")
                        .type(GoodsType.OTHER)
                        .price(BigDecimal.valueOf(15001.25))
                        .build()));
        return items;
    }

    public static List<Item> inputData3() {

        List<Item> items = List.of(
                new Item(1, Goods.builder()
                        .measurement(MeasurementUnit.BAG)
                        .origin(GoodsOrigin.IMPORTED)
                        .name("Almond Snickers")
                        .type(GoodsType.CANDY)
                        .price(BigDecimal.valueOf(75.99))
                        .build()),
                new Item(1, Goods.builder()
                        .measurement(MeasurementUnit.QUANTITY)
                        .origin(GoodsOrigin.LOCAL)
                        .name("Discman")
                        .type(GoodsType.OTHER)
                        .price(BigDecimal.valueOf(55.00))
                        .build()),
                new Item(1, Goods.builder()
                        .measurement(MeasurementUnit.QUANTITY)
                        .origin(GoodsOrigin.IMPORTED)
                        .name("Bottle of Wine")
                        .type(GoodsType.OTHER)
                        .price(BigDecimal.valueOf(10.00))
                        .build()),
                new Item(1, Goods.builder()
                        .measurement(MeasurementUnit.BAG)
                        .origin(GoodsOrigin.LOCAL)
                        .name("Fair-Trade Coffe")
                        .type(GoodsType.COFFEE)
                        .price(BigDecimal.valueOf(997.99))
                        .build()));

        return items;
    }


}
