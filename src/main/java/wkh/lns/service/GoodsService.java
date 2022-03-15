package wkh.lns.service;

import wkh.lns.dto.BasketDto;
import wkh.lns.models.Item;

import java.util.List;

public interface GoodsService {

    BasketDto add(List<Item> items);
}
