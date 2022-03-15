package wkh.lns.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BasketDto {

    private List<ItemDto> items;
    private BigDecimal total;
    private BigDecimal saleTax;
}
