package org.example;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class MarketPriceUtilsTest {


    @Test
    public void shouldAddOnePromilToAskAndSubstructOnePromilFromBid(){

        Price price = Price.builder()
                .id(1)
                .instrumentName("EUR/USD")
                .bid(1.00d)
                .ask(1.00d)
                .timestamp(Timestamp.valueOf("2022-06-12 12:01:01"))
                .build();
        Price priceWithMargin = MarketPriceUtils.getPriceWithMargin(price);
        assertEquals(1.001d, priceWithMargin.getAsk());
        assertEquals(0.999d, priceWithMargin.getBid());
    }

    @Test
    public void shouldReturnNullWhenAskIsNull(){

        Price price = Price.builder()
                .id(1)
                .instrumentName("EUR/USD")
                .bid(1.00d)
                .ask(null)
                .timestamp(Timestamp.valueOf("2022-06-12 12:01:01"))
                .build();
        Price priceWithMargin = MarketPriceUtils.getPriceWithMargin(price);
        assertNull(priceWithMargin);
    }

    @Test
    public void shouldReturnNullWhenBidIsNull(){

        Price price = Price.builder()
                .id(1)
                .instrumentName("EUR/USD")
                .bid(null)
                .ask(1.00d)
                .timestamp(Timestamp.valueOf("2022-06-12 12:01:01"))
                .build();
        Price priceWithMargin = MarketPriceUtils.getPriceWithMargin(price);
        assertNull(priceWithMargin);
    }

    @Test
    void shouldConvertPriceFromStringLineToPriceOject() throws ParseException {
        String columnsLine="106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";
        Price price = MarketPriceUtils.convertLineToMarketPrice(columnsLine);
        assertEquals(106L, price.getId());
        assertEquals("EUR/USD", price.getInstrumentName());
        assertEquals(1.1000d, price.getBid());
        assertEquals(1.2000d, price.getAsk());
        assertEquals("2020-06-01 12:01:01.001", price.getTimestamp().toString());
    }

    @Test
    void shouldThrowParserExceptionWhenInvalidNumberOfColumnsInCsv(){
        String sixColumnsLine="106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001, INVALID";
        String emptyColumnsLine="";

        Exception exception = assertThrows(ParseException.class, () ->
                MarketPriceUtils.convertLineToMarketPrice(sixColumnsLine));
        assertEquals(MarketPriceUtils.INVALID_FILE_FORMAT_MESSAGE, exception.getMessage());

        Exception exception2 = assertThrows(ParseException.class, () ->
                MarketPriceUtils.convertLineToMarketPrice(emptyColumnsLine));
        assertEquals(MarketPriceUtils.INVALID_FILE_FORMAT_MESSAGE, exception2.getMessage());
    }

    @Test
    void shouldThrowParserExceptionWhenInvalidCsvFormat() {
        String columnsLine="abcd, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";
        String columnsLine2="106, EUR/USD, 1.1000,1.2000,2020-06-01 12:01:01:001";

        Exception exception = assertThrows(ParseException.class, () ->
                MarketPriceUtils.convertLineToMarketPrice(columnsLine));
        assertEquals(MarketPriceUtils.INVALID_FILE_FORMAT_MESSAGE, exception.getMessage());

        Exception exception2 = assertThrows(ParseException.class, () ->
                MarketPriceUtils.convertLineToMarketPrice(columnsLine2));
        assertEquals(MarketPriceUtils.INVALID_FILE_FORMAT_MESSAGE, exception2.getMessage());
    }

}