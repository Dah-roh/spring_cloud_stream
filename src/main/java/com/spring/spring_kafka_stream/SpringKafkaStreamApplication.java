package com.spring.spring_kafka_stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringKafkaStreamApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger("KafkaApp");

	@Value("${message.topic.name}")
	private String topicName;


	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public SpringKafkaStreamApplication(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaStreamApplication.class, args);
	}


	@Override
	public void run(String... strings){
		kafkaTemplate.send(topicName, "Hello Geek!");
		LOG.info("Published message to topic: {}.", topicName);
	}

	@KafkaListener(topics = "javacodegeeks", groupId = "jcg-group")
	public void listen(String message) {
		LOG.info("Received message in JCG group: {}", message);
	}
}

