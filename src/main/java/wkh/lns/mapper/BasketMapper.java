package wkh.lns.mapper;

import wkh.lns.dto.BasketDto;
import wkh.lns.dto.ItemDto;
import wkh.lns.models.Basket;

import java.util.List;

public class BasketMapper {


    public BasketDto apply(final Basket basket, List<ItemDto> itemDto) {
        final BasketDto basketDto = new BasketDto();
        basketDto.setItems(itemDto);
        basketDto.setTotal(basket.getTotal());
        basketDto.setSaleTax(basket.getSaleTax());
        return basketDto;
    }
}
