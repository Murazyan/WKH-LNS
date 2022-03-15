package wkh.lns.mapper;

import wkh.lns.dto.ItemDto;
import wkh.lns.models.Item;

import java.util.function.Function;

public class ItemMapper implements Function<Item, ItemDto> {

    @Override
    public ItemDto apply(final Item item) {
        final ItemDto itemDto = new ItemDto();
        itemDto.setCount(item.getCount());
        itemDto.setGoodsName(item.getGoods().getName());
        itemDto.setMeasurement(item.getGoods().getMeasurement());
        itemDto.setType(item.getGoods().getType());
        itemDto.setOrigin(item.getGoods().getOrigin());
        return itemDto;
    }
}
