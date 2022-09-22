package org.santander.marketprice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarketPriceManagerTest {

    private MarketPriceManager marketPriceManager;
    @BeforeEach
    public void setUp(){
        marketPriceManager = new MarketPriceManager();
    }

    @Test
    public void shouldProperlySaveAndLoadFile() throws IOException, ParseException {
        marketPriceManager.savePricesToFile("106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001 \n… \n107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002");
        Price price = marketPriceManager.getPriceFromFile("EUR/JPY");
        assertEquals(107L, price.getId());
        assertEquals("EUR/JPY", price.getInstrumentName());
        assertEquals(119.60d, price.getBid());
        assertEquals(119.90d, price.getAsk());
        assertEquals("2020-06-01 12:01:02.002", price.getTimestamp().toString());
    }

}