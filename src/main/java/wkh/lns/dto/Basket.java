package wkh.lns.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Basket {

    private List<ItemResponse> items;
    private BigDecimal total;
    private BigDecimal saleTax;
}
