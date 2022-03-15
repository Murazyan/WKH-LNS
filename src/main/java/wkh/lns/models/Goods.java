package wkh.lns.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import wkh.lns.models.enums.GoodsOrigin;
import wkh.lns.models.enums.GoodsType;
import wkh.lns.models.enums.MeasurementUnit;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Goods {

    private String name;
    private BigDecimal price;
    private GoodsType type;
    private GoodsOrigin origin;
    private MeasurementUnit measurement;
}
