package wkh.lns.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wkh.lns.dto.ItemDto;
import wkh.lns.models.Goods;
import wkh.lns.models.Item;
import wkh.lns.models.enums.GoodsOrigin;
import wkh.lns.models.enums.GoodsType;
import wkh.lns.models.enums.MeasurementUnit;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemMapperTest {

    private ItemMapper itemMapper;

    @BeforeEach
    public void beforeEach() {
        itemMapper = new ItemMapper();
    }

    @Test
    public void mapTest() {
        ItemDto itemDto = itemMapper.apply(new Item(1, Goods.builder()
                .measurement(MeasurementUnit.BAG)
                .type(GoodsType.CANDY)
                .origin(GoodsOrigin.LOCAL)
                .name("Snickers")
                .price(new BigDecimal("10.00"))
                .build()));
        assertEquals(itemDto.getCount(), 1);
        assertEquals(itemDto.getMeasurement(), MeasurementUnit.BAG);
        assertEquals(itemDto.getOrigin(), GoodsOrigin.LOCAL);
        assertEquals(itemDto.getType(), GoodsType.CANDY);
        assertEquals(itemDto.getPrice(), null);
        assertEquals(itemDto.getGoodsName(), "Snickers");

    }
}
