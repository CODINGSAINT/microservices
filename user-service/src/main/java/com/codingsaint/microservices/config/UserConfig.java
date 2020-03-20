package com.codingsaint.microservices.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@EnableKafka
public class UserConfig {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UserConfig.class);
	
	@Autowired
	private KafkaProperties kafkaProperties;
    
   @Bean
   public ConsumerFactory<String, String> consumerFactory() {
       Map<String, Object> props = new HashMap<>();
       props.put(
               ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
               kafkaProperties.getBootstrapServers());
       props.put(
               ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
               StringDeserializer.class);
       props.put(
               ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
               StringDeserializer.class);
       return new DefaultKafkaConsumerFactory<>(props);
   }

   /**
    * Required Configuration for POJO to JSON
    * @return ConcurrentKafkaListenerContainerFactory
    */
   @Bean
   public ConcurrentKafkaListenerContainerFactory<String, String>
   kafkaListenerContainerFactory() {

       ConcurrentKafkaListenerContainerFactory<String, String> factory =
               new ConcurrentKafkaListenerContainerFactory<>();
       factory.setConsumerFactory(consumerFactory());
       //factory.setMessageConverter(new StringJsonMessageConverter());
       return factory;
   }
   @KafkaListener(id = "tasks", topics = "tasks",
           containerFactory = "kafkaListenerContainerFactory")
   public void consume(@Payload String payload,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String incomingTopic,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts
   ) {
       LOGGER.info("Incoming message {}-> {}", incomingTopic, payload);
   }
 

}
