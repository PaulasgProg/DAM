package com.paula.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Permitir el acceso a swagger
				.route("usuarios", r -> r.path("/usuarios/**")
						.uri("lb://usuarios:8702"))
				// Permitir el acceso a la ruta de la api
				.route("reservas", r -> r.path("/reservas/**")
						.uri("lb://reservas:8701"))
				.route("comentarios", r -> r.path("/comentarios/**")
						.uri("lb://comentarios:8703"))
				.route("graphiql", r -> r.path("/graphiql/**")
						.uri("http://localhost:8703/graphiql"))
				.build();
	}

}
