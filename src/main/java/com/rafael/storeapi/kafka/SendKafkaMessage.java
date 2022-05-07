package com.rafael.storeapi.kafka;

import com.rafael.storeapi.dto.PurchaseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendKafkaMessage {

    private final KafkaTemplate<String, PurchaseDTO> kafkaTemplate;

    public static final String KAFKA_TOPIC = "PURCHASES";

    public void sendMessage(PurchaseDTO purchase) {
        kafkaTemplate.send(KAFKA_TOPIC, purchase);
    }
}
