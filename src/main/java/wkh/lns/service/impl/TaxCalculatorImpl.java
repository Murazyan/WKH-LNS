package wkh.lns.service.impl;

import wkh.lns.dto.ItemDto;
import wkh.lns.mapper.ItemMapper;
import wkh.lns.models.Item;
import wkh.lns.models.enums.GoodsOrigin;
import wkh.lns.models.enums.GoodsType;
import wkh.lns.service.TaxCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TaxCalculatorImpl implements TaxCalculator {

    private final int BASIC_TAX;
    private final int IMPORTED_TAX;
    private final ItemMapper itemMapper;

    public TaxCalculatorImpl(final ItemMapper itemMapper) {
        BASIC_TAX = 10;
        IMPORTED_TAX = 5;
        this.itemMapper = itemMapper;
    }


    @Override
    public List<ItemDto> calculate(final List<Item> items) {
        final List<ItemDto> itemList = new ArrayList<>();
        if (items != null)
            for (Item item : items) {
                final ItemDto itemDto = itemMapper.apply(item);
                if (item.getGoods().getType() != GoodsType.OTHER) {
                    itemDto.setPrice(item.getGoods().getPrice());
                } else {
                    final BigDecimal price = item.getGoods().getPrice().multiply(BigDecimal.valueOf(item.getCount()))
                            .add(getPriceWithTax(item.getGoods().getPrice(), item.getCount(), BASIC_TAX));
                    itemDto.setPrice(price);
                }
                if (item.getGoods().getOrigin() == GoodsOrigin.IMPORTED) {
                    if (itemDto.getPrice() == null) {
                        final BigDecimal price = item.getGoods().getPrice().multiply(BigDecimal.valueOf(item.getCount()))
                                .add(getPriceWithTax(item.getGoods().getPrice(), item.getCount(), IMPORTED_TAX));
                        itemDto.setPrice(price);
                    } else
                        itemDto.addPrice(getPriceWithTax(item.getGoods().getPrice(), item.getCount(), IMPORTED_TAX));
                }
                itemList.add(itemDto);
            }
        return itemList;
    }

    private BigDecimal getPriceWithTax(final BigDecimal goodsPrice, final int count, final int taxPercent) {
        final BigDecimal basicTax = goodsPrice.multiply(BigDecimal.valueOf(taxPercent))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return basicTax.multiply(BigDecimal.valueOf(count));
    }


}
