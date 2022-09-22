package org.example;

import java.sql.Timestamp;

public class MarketPriceUtils {
    
    private static final String PRICES_FILE_SEPARATOR=",";
    private static final int NUMBER_OF_PRICE_PROPERTIES=5;
    private static final double MARGIN = 0.001;


    static Price convertLineToMarketPrice(String priceLine){
        String[] marketPriceElements = priceLine.split(PRICES_FILE_SEPARATOR);
        if(marketPriceElements.length != NUMBER_OF_PRICE_PROPERTIES) {
            return null;
        }
        return Price.builder()
                .id(Long.parseLong(marketPriceElements[0]))
                .instrumentName(marketPriceElements[1])
                .bid(Double.valueOf(marketPriceElements[2]))
                .ask(Double.valueOf(marketPriceElements[3]))
                .timestamp(Timestamp.valueOf(marketPriceElements[4]))
                .build();
    }

    static Price getPriceWithMargin(Price price){
        price.setAsk(price.getAsk() + price.getAsk() * MARGIN);
        price.setBid(price.getBid() + price.getBid() * MARGIN);
        return price;
    }
}
