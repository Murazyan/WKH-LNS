package wkh.lns.service.impl;

import wkh.lns.dto.BasketDto;
import wkh.lns.dto.ItemDto;
import wkh.lns.mapper.BasketMapper;
import wkh.lns.models.Basket;
import wkh.lns.models.Item;
import wkh.lns.service.GoodsService;
import wkh.lns.service.TaxCalculator;

import java.math.BigDecimal;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {

    private final TaxCalculator taxCalculator;
    private final BasketMapper basketMapper;

    public GoodsServiceImpl(final TaxCalculator taxCalculator, final BasketMapper basketMapper) {
        this.taxCalculator = taxCalculator;
        this.basketMapper = basketMapper;
    }

    @Override
    public BasketDto add(final List<Item> items) {
        final Basket basket = new Basket(items);
        List<ItemDto> itemsDto = taxCalculator.calculate(items);
        var total = itemsDto.stream()
                .map(ItemDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var purePrice = items.stream()
                .map(item -> item.getGoods().getPrice().multiply(BigDecimal.valueOf(item.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        basket.setTotal(total);
        basket.setSaleTax(total.subtract(purePrice));

        return basketMapper.apply(basket, itemsDto);
    }

}
