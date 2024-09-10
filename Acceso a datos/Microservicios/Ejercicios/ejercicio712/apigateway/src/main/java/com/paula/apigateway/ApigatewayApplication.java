package com.paula.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("autores", r -> r.path("/autor/**").uri("lb://autores:8713"))
				.route("libros", r -> r.path("/libro/**").uri("lb://libros:8714"))
				.route("pedidos", r -> r.path("/pedido/**").uri("lb://pedidos:8715"))
				.build();
	}

}
