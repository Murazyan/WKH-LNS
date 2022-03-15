package wkh.lns.service.impl;

import wkh.lns.dto.ItemResponse;
import wkh.lns.models.Item;
import wkh.lns.models.enums.GoodsOrigin;
import wkh.lns.models.enums.GoodsType;
import wkh.lns.service.TaxCalculator;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TaxCalculatorImpl implements TaxCalculator {

    public int BASIC_TAX;
    public int IMPORTED_TAX;

    public TaxCalculatorImpl() {
        try {
            final FileReader reader = new FileReader(this.getClass().getResource("/application.properties").getFile());
            final Properties p = new Properties();
            p.load(reader);
            BASIC_TAX = Integer.parseInt(p.getProperty("tax.basic", "10"));
            IMPORTED_TAX = Integer.parseInt(p.getProperty("tax.imported", "5"));
        } catch (IOException e) {
            System.out.println("Unable to read from properties");
            e.printStackTrace();
        }
    }


    @Override
    public List<ItemResponse> calculate(final List<Item> items) {
        final List<ItemResponse> itemList = new ArrayList<>();
        if (items != null)
            for (Item item : items) {
                final ItemResponse itemResponse = map(item);
                if (item.getGoods().getType() != GoodsType.OTHER) {
                    itemResponse.setPrice(item.getGoods().getPrice());
                }
                if (item.getGoods().getType() == GoodsType.OTHER) {
                    final BigDecimal price = item.getGoods().getPrice().multiply(BigDecimal.valueOf(item.getCount()))
                            .add(getPriceWithTax(item.getGoods().getPrice(), item.getCount(), BASIC_TAX));
                    itemResponse.setPrice(price);
                }
                if (item.getGoods().getOrigin() == GoodsOrigin.IMPORTED) {
                    if (itemResponse.getPrice() == null) {
                        final BigDecimal price = item.getGoods().getPrice().multiply(BigDecimal.valueOf(item.getCount()))
                                .add(getPriceWithTax(item.getGoods().getPrice(), item.getCount(), IMPORTED_TAX));
                        itemResponse.setPrice(price);
                    } else
                        itemResponse.addPrice(getPriceWithTax(item.getGoods().getPrice(), item.getCount(), IMPORTED_TAX));
                }
                itemList.add(itemResponse);
            }
        return itemList;
    }

    private BigDecimal getPriceWithTax(final BigDecimal goodsPrice, final int count, final int taxPercent) {
        final BigDecimal basicTax = goodsPrice.multiply(BigDecimal.valueOf(taxPercent))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return basicTax.multiply(BigDecimal.valueOf(count));
    }


    private ItemResponse map(final Item item) {
        final ItemResponse itemResponse = new ItemResponse();
        itemResponse.setCount(item.getCount());
        itemResponse.setGoodsName(item.getGoods().getName());
        itemResponse.setMeasurement(item.getGoods().getMeasurement());
        itemResponse.setType(item.getGoods().getType());
        itemResponse.setOrigin(item.getGoods().getOrigin());
        return itemResponse;
    }
}
