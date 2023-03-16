package com.reviewsystem.config;

import com.reviewsystem.dto.MovieRequestDTO;
import com.reviewsystem.dto.deserializer.MovieDTODeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;

	@Bean
	public ConsumerFactory<String, MovieRequestDTO> movieConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		config.put(ConsumerConfig.CLIENT_ID_CONFIG, "movie-consumer");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "review-movie-consumer-group");
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MovieDTODeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, MovieRequestDTO> movieListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, MovieRequestDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(movieConsumerFactory());
		return factory;
	}
}
