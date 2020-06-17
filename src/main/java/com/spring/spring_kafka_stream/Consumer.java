package com.spring.spring_kafka_stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    //the sink.input from the sink.class for enabling binding is used as the streamlistener target
    @StreamListener(target = Sink.INPUT)
    public void consume(String message) {

        logger.info("recieved a string message : " + message);
    }

}