package com.cagkankantarci.lessonservice.producer;

import com.cagkankantarci.lessonservice.model.Lesson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class LessonProducer {


    @Autowired
    KafkaTemplate<Long, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendLessonTopic(Lesson lesson) throws JsonProcessingException {

        Long id = lesson.getId();
        String value = objectMapper.writeValueAsString(lesson);

        CompletableFuture<SendResult<Long, String>> completableFuture = kafkaTemplate.sendDefault(id, value);

        completableFuture.thenApply(result -> {
            handleSuccess(id, value, result);
            return result;
        });

        completableFuture.exceptionally(throwable -> {
            handleFailure(throwable);
            return null;
        });
    }

    private void handleSuccess(Long id, String value, SendResult<Long, String> result) {
        System.out.println("Message gonderildi. Key: " + id + " Value: " + value + " partition: " + result.getRecordMetadata().partition());
    }


    private void handleFailure(Throwable throwable) {
        System.out.println("Message gonderilirken hata alindi. " + throwable.getMessage());

        try {
            throw throwable;
        } catch (Throwable throwable1) {
            System.err.println("Error in OnFailure: " + throwable1.getMessage());
        }
    }
}
