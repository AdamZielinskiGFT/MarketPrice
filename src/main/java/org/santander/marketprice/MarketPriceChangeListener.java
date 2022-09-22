package org.santander.marketprice;

import lombok.NoArgsConstructor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

@NoArgsConstructor
public class MarketPriceChangeListener implements MessageListener {

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String msg = ((TextMessage) message).getText();
                if (msg == null) {
                    throw new IllegalArgumentException("Null value received...");
                }
                MarketPriceManager priceManager = new MarketPriceManager();
                priceManager.savePricesToFile(msg);
            } catch (JMSException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
