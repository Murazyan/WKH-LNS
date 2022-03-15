package wkh.lns.service;

import wkh.lns.dto.Basket;
import wkh.lns.models.Item;

import java.util.List;

public interface GoodsService {

    Basket add(List<Item> items);

    void printBasketData(final Basket basket);
}
