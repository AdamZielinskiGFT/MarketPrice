package org.santander.marketprice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.text.ParseException;

@Path("market/price")
public class MarketPriceService {

    @GET
    public Price getMarketPrice(@QueryParam("instrumentName") String instrumentName) throws IOException, ParseException {
        MarketPriceManager priceManager = new MarketPriceManager();
        return priceManager.getPriceByInstrumentName(instrumentName);
    }
}
