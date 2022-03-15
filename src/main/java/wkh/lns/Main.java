package wkh.lns;

import wkh.lns.dto.Basket;
import wkh.lns.service.impl.GoodsServiceImpl;
import wkh.lns.service.impl.TaxCalculatorImpl;

public class Main {

    public static void main(String[] args) {

        System.out.println("*** WKH - LNS ***");
        final GoodsServiceImpl goodService = new GoodsServiceImpl(new TaxCalculatorImpl());

        final Basket basket1 = goodService.add(InputData.inputData1());
        final Basket basket2 = goodService.add(InputData.inputData2());
        final Basket basket3 = goodService.add(InputData.inputData3());

        System.out.println("Output1:");
        goodService.printBasketData(basket1);

        System.out.println("Output2");
        goodService.printBasketData(basket2);

        System.out.println("Output3");
        goodService.printBasketData(basket3);
    }


}
