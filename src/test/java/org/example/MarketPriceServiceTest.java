package org.example;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

class MarketPriceServiceTest {

    private static final Logger logger = LogManager.getLogger(MarketPriceServiceTest.class);
    private MarketPriceService marketPriceService;

    @BeforeEach
    public void setUp(){
        marketPriceService = new MarketPriceService();
    }

    @Test
    void getMarketPrice() throws IOException, ParseException {
        Price marketPrice = marketPriceService.getMarketPrice("EUR/USD");
        logger.info("Market ask price: " + marketPrice.getAsk() + " and market bid price: " + marketPrice.getBid());
    }
}