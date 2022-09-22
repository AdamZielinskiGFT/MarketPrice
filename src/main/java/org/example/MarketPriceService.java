package org.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;

@Path("market/price")
public class MarketPriceService {


    @GET
    public Price getMarketPrice(@QueryParam("instrumentName") String instrumentName) throws IOException {
        MarketPriceManager priceManager = new MarketPriceManager();
        return priceManager.getPriceByInstrumentName(instrumentName);
    }
}
