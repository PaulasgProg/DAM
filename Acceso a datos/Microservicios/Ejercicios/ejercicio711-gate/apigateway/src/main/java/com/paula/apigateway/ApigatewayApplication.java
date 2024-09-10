package com.paula.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	private Mono<Boolean> handleInvalidReferer(ServerWebExchange exchange) {
		// Realizar acciones específicas para el caso de 'Referer' no válido
		// Por ejemplo, puedes devolver un código de estado FORBIDDEN o redirigir a una
		// página de error
		exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
		return Mono.just(false);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Acceso ejercicio 701
				/*
				 * .route("saludo", r -> r.path("/api-saludo", "/saludo/**")
				 * .uri("lb://saludo:8701"))
				 */

				// Acceso ejercicio 702
				.route("saludo", r -> r.path("/api-saludo", "/saludo/**")
						.uri("lb://saludo:8702"))

				// Acceso ejercicio 703
				.route("productos", r -> r.path("/api-productos", "/productos/**")
						.uri("lb://productos:8703"))

				// Acceso ejercicio 704
				.route("empleados", r -> r.path("/api-empleados", "/empleados/**")
						.uri("lb://empleados:8704"))

				// Acceso ejercicio 705
				.route("libros", r -> r.path("/api-libros", "/libros/**")
						.uri("lb://libros:8705"))

				// Acceso ejercicio 706
				.route("empleadosQLRoute", r -> r.path("/empleadosQL/**")
						.filters(f -> f.rewritePath("/empleadosQL/(?<segment>.*)", "/${segment}"))
						.uri("lb://empleadosQL:8706"))
				.route("empleadosQLGraph", r -> r.path("/empleadosQL706")
						.uri("lb://empleadosQL:8706/empleadosQL706"))

				// Acceso ejercicio 707
				.route("librosQLRoute", r -> r.path("/librosQL/**")
						.filters(f -> f.rewritePath("/librosQL/(?<segment>.*)", "/${segment}"))
						.uri("lb://librosQL:8707"))
				.route("librosQLGraph", r -> r.path("/librosQL707")
						.uri("lb://librosQL:8707/librosQL707"))

				// Acceso ejercicio 709
				.route("estudiantes709", r -> r.path("/api-estudiantes709", "/estudiantes709/**")
						.uri("lb://estudiantes709:8709"))

				// Acceso ejercicio 710
				.route("peliculas710", r -> r.path("/api-peliculas710", "/peliculas710/**")
						.uri("lb://peliculas710:8710"))
				.build();
	}

}
