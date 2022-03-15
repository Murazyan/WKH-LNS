package wkh.lns.service;

import wkh.lns.dto.ItemResponse;
import wkh.lns.models.Item;

import java.util.List;

public interface TaxCalculator {


    List<ItemResponse> calculate(List<Item> items);

}
