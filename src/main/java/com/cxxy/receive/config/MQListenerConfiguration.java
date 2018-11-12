package com.cxxy.receive.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

import javax.jms.ConnectionFactory;

/**
 * Author:liuhui
 * Description:
 * Date: 4:39 PM 2018/11/9
 */
@Configuration
@EnableJms
@EnableConfigurationProperties(ActiveMQProperties.class)
public class MQListenerConfiguration {

    /**
     * 配置带缓存功能的connectionFactory，不共同一个clientId
     */
    @Bean(name = "consumerConnectionFactory")
    public ConnectionFactory consumerConnectionFactory(ActiveMQProperties properties){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(properties.getBrokerUrl());
        activeMQConnectionFactory.setUseAsyncSend(true);
        activeMQConnectionFactory.setClientID("receive-listener");

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(activeMQConnectionFactory);
        cachingConnectionFactory.setSessionCacheSize(10);
        return cachingConnectionFactory;
    }


//    /**
//     * 持久化订阅时，需要为每一个JMSListener配置一个ListenerContainer
//     * 需设置subscriptionDurable为true
//     */
//    private DefaultJmsListenerContainerFactory createContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer,
//                                                                      ConnectionFactory connectionFactory) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        configurer.configure(factory, connectionFactory);
//        factory.setSubscriptionDurable(true);
//        return factory;
//    }
//
//    @Bean
//    public JmsListenerContainerFactory<?> consumer1JmsContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer,
//                                                                      @Qualifier("consumerConnectionFactory") ConnectionFactory consumerConnectionFactory) {
//        return createContainerFactory(configurer, consumerConnectionFactory);
//    }
}
