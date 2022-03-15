package wkh.lns.service;

import wkh.lns.dto.ItemDto;
import wkh.lns.models.Item;

import java.util.List;

public interface TaxCalculator {


    List<ItemDto> calculate(List<Item> items);

}
