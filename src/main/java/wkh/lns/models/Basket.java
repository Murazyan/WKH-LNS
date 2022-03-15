package wkh.lns.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Basket {

    private List<Item> items;
    private BigDecimal total;
    private BigDecimal saleTax;

    public Basket(final List<Item> items) {
        this.items = items;
    }
}
