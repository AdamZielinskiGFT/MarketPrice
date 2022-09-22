//package org.example;
//
//import org.springframework.jms.support.converter.MessageConversionException;
//
//import javax.jms.JMSException;
//import javax.jms.MapMessage;
//import javax.jms.Message;
//import java.sql.Timestamp;
//
//public class PriceMessageConverter {
//
//
//    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
//        MapMessage mapMessage = (MapMessage) message;
//        return Price.builder()
//                .id(mapMessage.getLong("id"))
//                .instrumentName(mapMessage.getString("instrumentName"))
//                .bid(mapMessage.getDouble("bid"))
//                .ask(mapMessage.getDouble("ask"))
//                .timestamp(Timestamp.valueOf(mapMessage.getString("timestamp")))
//                .build();
//    }
//}
