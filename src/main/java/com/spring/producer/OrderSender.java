package com.spring.producer;

import com.spring.pojo.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void  sendOrder(Order order){
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend("order-exchange",
                "order.kang",
                order,
                correlationData);
    }
}
