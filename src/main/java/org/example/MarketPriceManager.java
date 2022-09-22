package org.example;

import lombok.NoArgsConstructor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;

@NoArgsConstructor
public class MarketPriceManager {

    private static final String PRICES_FILE_NAME="prices.txt";

    public Price getPriceByInstrumentName(String instrumentName) throws IOException, ParseException {
        if(instrumentName == null || instrumentName.isEmpty())
            return null;
        return MarketPriceUtils.getPriceWithMargin(getPriceFromFile(instrumentName));
    }

    public void savePricesToFile(String csvWithPrices) throws IOException {
        File marketPricesFile = new File(PRICES_FILE_NAME);
        if(!marketPricesFile.exists())
            marketPricesFile.createNewFile();
        FileOutputStream marketPricesFileStream = new FileOutputStream(marketPricesFile, false);
        marketPricesFileStream.write(csvWithPrices.getBytes(StandardCharsets.UTF_8));
        marketPricesFileStream.close();
    }

    Price getPriceFromFile(String instrumentName) throws IOException, ParseException {
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
