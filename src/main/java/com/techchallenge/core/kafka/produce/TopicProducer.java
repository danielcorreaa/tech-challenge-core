package com.techchallenge.core.kafka.produce;

import com.techchallenge.core.exceptions.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.ExecutionException;

@Log4j2
public class TopicProducer<T> {

    private KafkaTemplate<String, T> produce;

    private String topic;

    public TopicProducer(KafkaTemplate<String, T> produce, String topic) {
        this.produce = produce;
        this.topic = topic;
    }

    public SendResult<String, T> produce(T message){
        return produce(null, message);
    }
    public SendResult<String, T> produce(String key, T message) {
        SendResult<String, T> result;
        try {
            result = produce.send(topic, key,  message).get();
            log.info("Message produced");
            return result;
        }catch (InterruptedException | ExecutionException ex){
            log.error("Fail to producing message", ex);
            throw new BusinessException("Fail to producing message", ex);
        }

    }
}
