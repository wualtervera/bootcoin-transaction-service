package com.nttdata.bootcointransactionservice.infrestructure.config;

import com.nttdata.bootcointransactionservice.domain.BootcoinTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaProducerConfig {
    @Value("${custom.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, BootcoinTransaction> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory(config);
    }

    @Bean
    public KafkaTemplate<String, BootcoinTransaction> walletTransactionKafkaTemplate() {
        KafkaTemplate<String, BootcoinTransaction> kafkaTemplate = new KafkaTemplate<>(producerFactory());

        kafkaTemplate.setProducerListener(new ProducerListener<String, BootcoinTransaction>() {
            @Override
            public void onSuccess(ProducerRecord<String, BootcoinTransaction> producerRecord, RecordMetadata recordMetadata) {
                log.info("Success to publish message: {}  offset:  {}", producerRecord.value(),
                        recordMetadata.offset());
            }

            @Override
            public void onError(ProducerRecord<String, BootcoinTransaction> producerRecord, RecordMetadata recordMetadata,
                                Exception exception) {
                log.warn("Error to publish message [{}] exception: {}", producerRecord.value(), exception.getMessage());
            }
        });
        return kafkaTemplate;
    }


}