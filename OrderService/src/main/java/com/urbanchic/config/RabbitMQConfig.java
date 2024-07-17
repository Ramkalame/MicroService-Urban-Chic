package com.urbanchic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //Exchange Name
    @Value("${producer.exchange-name}")
    private String orderExchangeName;

    //Queue Name for purchase order mail
    @Value("${producer.purchase-order-mail.queue-name}")
    private String purchaseOrderMailQueue;

    //Routing key for purchase order mail queue
    @Value("${producer.purchase-order-mail.routing-key}")
    private String purchaseOrderMailQueueRoutingKey;

    //Queue Name for purchase order sms
    @Value("${producer.purchase-order-sms.queue-name}")
    private String purchaseOrderSmsQueue;

    //Routing key for purchase order sms queue
    @Value("${producer.purchase-order-sms.routing-key}")
    private String purchaseOrderSmsQueueRoutingKey;

    //Exchange to route messages to respective queues
    @Bean
    public TopicExchange orderExchange(){
        return  new TopicExchange(orderExchangeName);
    }

    //Queue for purchasing order mail
    @Bean
    public Queue purchaseOrderMailQueue(){
        return new Queue(purchaseOrderMailQueue);
    }

    //Binding of purchase order mail queue and its routing key to the exchange
    @Bean
    public Binding purchaseOrderMailQueueBinding(){
        return BindingBuilder
                .bind(purchaseOrderMailQueue())
                .to(orderExchange())
                .with(purchaseOrderMailQueueRoutingKey);
    }

    @Bean
    public Queue purchaseOrderSmsQueue(){
        return new Queue(purchaseOrderSmsQueue);
    }

    @Bean
    public Binding purchaseOrderSmsQueueBinding(){
        return BindingBuilder
                .bind(purchaseOrderSmsQueue())
                .to(orderExchange())
                .with(purchaseOrderSmsQueueRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
