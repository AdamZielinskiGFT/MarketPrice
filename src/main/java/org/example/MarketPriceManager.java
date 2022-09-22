package org.example;

import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor
public class MarketPriceManager {

    private static final String PRICES_FILE_NAME="prices.txt";

    public Price getPriceByInstrumentName(String instrumentName) throws IOException {
        if(instrumentName == null || instrumentName.isEmpty())
            return null;
        Price price = getPriceFromFile(instrumentName);
        if(price == null || price.getAsk() == null || price.getBid() == null)
            return null;
        return MarketPriceUtils.getPriceWithMargin(price);
    }

    public void savePricesToFile(String csvWithPrices) throws IOException {
        File marketPricesFile = new File(PRICES_FILE_NAME);
        if(!marketPricesFile.exists())
            marketPricesFile.createNewFile();
        FileOutputStream marketPricesFileStream = new FileOutputStream(marketPricesFile, false);
        marketPricesFileStream.write(csvWithPrices.getBytes(StandardCharsets.UTF_8));
        marketPricesFileStream.close();
    }

    private Price getPriceFromFile(String instrumentName) throws IOException {
        FileInputStream fileStream = new FileInputStream(PRICES_FILE_NAME);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));

        while(reader.ready()){
            String priceLine = reader.readLine();
            if (priceLine.contains(instrumentName)) {
                return MarketPriceUtils.convertLineToMarketPrice(priceLine);
            }
        }
        return null;
    }

}
