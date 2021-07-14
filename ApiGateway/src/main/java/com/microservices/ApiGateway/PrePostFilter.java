package com.microservices.ApiGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class PrePostFilter {

	final Logger logger = LoggerFactory.getLogger(PrePostFilter.class);

	@Bean
	public GlobalFilter secondGlobalFilter() {
		return (exchange, chain) -> {
			logger.info("This is second pre filter executed........!");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				logger.info("This is second post filter executed.....!");
			}));
		};
	}
	
	@Bean
	public GlobalFilter thirdGlobalFilter() {
		return (exchange, chain) -> {
			logger.info("This is third pre filter executed........!");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				logger.info("This is third post filter executed.....!");
			}));
		};
	}
}
