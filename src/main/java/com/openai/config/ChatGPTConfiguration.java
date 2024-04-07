package com.openai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author madhankumar
 */
@Configuration
public class ChatGPTConfiguration {

	@Value("${openai.api.key}")
	private String apiKey;

	/**
	 * Configures and returns a RestTemplate bean with an interceptor to add an Authorization header with the API key.
	 * @return A RestTemplate configured with an interceptor to add an Authorization header.
	 */
	@Bean
	RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + apiKey);
			return execution.execute(request, body);
		});

		return restTemplate;
	}

}
