package wkh.lns.dto;

import lombok.*;
import wkh.lns.models.enums.GoodsOrigin;
import wkh.lns.models.enums.GoodsType;
import wkh.lns.models.enums.MeasurementUnit;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private int count;

    private String goodsName;
    private BigDecimal price;
    private GoodsType type;
    private GoodsOrigin origin;
    private MeasurementUnit measurement;

    public void addPrice(final BigDecimal taxPrice) {
        this.price = this.price.add(taxPrice);
    }
}
