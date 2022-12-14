package org.santander.marketprice;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MarketPriceUtils {
    
    private static final String PRICES_FILE_SEPARATOR=",";
    private static final int NUMBER_OF_PRICE_PROPERTIES=5;
    private static final double MARGIN = 0.001;
    static final String INVALID_FILE_FORMAT_MESSAGE="Invalid file format";


    static Price convertLineToMarketPrice(String priceLineFromCsv) throws ParseException {

        String[] marketPriceElements = priceLineFromCsv.split(PRICES_FILE_SEPARATOR);
        if(marketPriceElements.length != NUMBER_OF_PRICE_PROPERTIES) {
            throw new ParseException(INVALID_FILE_FORMAT_MESSAGE, 0);
        }

        try {
            return Price.builder()
                    .id(Long.parseLong(removeSpaces(marketPriceElements[0])))
                    .instrumentName(removeSpaces(marketPriceElements[1]))
                    .bid(Double.valueOf(removeSpaces(marketPriceElements[2])))
                    .ask(Double.valueOf(removeSpaces(marketPriceElements[3])))
                    .timestamp(reverseFileTimestampFormat(marketPriceElements[4]))
                    .build();
        }
        catch (Exception ex){
            throw new ParseException(INVALID_FILE_FORMAT_MESSAGE, 0);
        }
    }

    static Price getPriceWithMargin(Price price){
        if(price == null || price.getAsk() == null || price.getBid() == null)
            return null;
        price.setAsk(price.getAsk() + price.getAsk() * MARGIN);
        price.setBid(price.getBid() - price.getBid() * MARGIN);
        return price;
    }

    private static Timestamp reverseFileTimestampFormat(String timestampFromFile) throws ParseException {
        Date parsedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS").parse(timestampFromFile);
        if(parsedDate.getTime() < 0)
            throw new ParseException(INVALID_FILE_FORMAT_MESSAGE, 0);
        return new Timestamp(parsedDate.getTime());
    }

    private static String removeSpaces(String textWithSpaces){
        return textWithSpaces.replaceAll("\\s+","");
    }
}
