package com.frb.testapp.config;

import io.netty.channel.ChannelOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Slf4j
@Configuration
public class WebConfig {

	private final Duration connectionTimeout = Duration.ofSeconds(30);

	@Bean
	public WebClient webClient() {
		log.info("Setting web client timeout: {}", connectionTimeout);

		ConnectionProvider connectionProvider = ConnectionProvider.builder("myConnectionPool")
				.maxConnections(10000)
				.pendingAcquireMaxCount(1000)
				.build();

		HttpClient httpClient = HttpClient.create(connectionProvider)
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,
						connectionTimeout.toMillisPart())
				.responseTimeout(connectionTimeout);

		ReactorClientHttpConnector clientHttpConnector = new ReactorClientHttpConnector(httpClient);

		return WebClient.builder().clientConnector(clientHttpConnector).build();
	}
}
