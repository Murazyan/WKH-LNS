package wkh.lns;

import wkh.lns.dto.BasketDto;
import wkh.lns.mapper.BasketMapper;
import wkh.lns.mapper.ItemMapper;
import wkh.lns.service.BasketPrinter;
import wkh.lns.service.impl.BasketPrinterImpl;
import wkh.lns.service.impl.GoodsServiceImpl;
import wkh.lns.service.impl.TaxCalculatorImpl;

public class Main {

    public static void main(String[] args) {

        System.out.println("*** WKH - LNS ***");
        final GoodsServiceImpl goodService = new GoodsServiceImpl(new TaxCalculatorImpl(new ItemMapper()), new BasketMapper());
        final BasketPrinter basketPrinter = new BasketPrinterImpl();

        final BasketDto basket1 = goodService.add(InputData.inputData1());
        final BasketDto basket2 = goodService.add(InputData.inputData2());
        final BasketDto basket3 = goodService.add(InputData.inputData3());

        System.out.println("Output1:");
        basketPrinter.print(basket1);

        System.out.println("Output2");
        basketPrinter.print(basket2);

        System.out.println("Output3");
        basketPrinter.print(basket3);
    }


}
