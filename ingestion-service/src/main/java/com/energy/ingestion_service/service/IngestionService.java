package com.energy.ingestion_service.service;

import com.energy.ingestion_service.dto.EnergyUsageDto;
import com.energy.kafka.event.EnergyUsageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IngestionService {

    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

    @Autowired
    public IngestionService(KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void ingestEnergyUsage(EnergyUsageDto energyUsageDto) {
        //convert to event
        EnergyUsageEvent event=EnergyUsageEvent.builder()
                .deviceId(energyUsageDto.deviceId())
                .energyConsumed(energyUsageDto.energyConsumed())
                .timestamp(energyUsageDto.timestamp())
                .build();

        // send kafka topic
        kafkaTemplate.send("energy-usage", event);
        log.info("Energy usage event : {}",event);
    }
}
