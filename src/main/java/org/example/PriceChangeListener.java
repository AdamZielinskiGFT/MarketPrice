package org.example;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;
import java.io.IOException;

public class PriceChangeListener implements MessageListener {

    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String msg = ((TextMessage) message).getText();
                System.out.println("Received message: " + msg);
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

//    public Employee receiveMessage() throws JMSException {
//        Map map = (Map) this.jmsTemplate.receiveAndConvert();
//        return new Employee((String) map.get("name"), (Integer) map.get("age"));
//    }

}
