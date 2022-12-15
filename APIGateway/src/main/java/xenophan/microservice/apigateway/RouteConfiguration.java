package xenophan.microservice.apigateway;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xenophan.microservice.apigateway.filter.AuthenticationPrefilter;

@Configuration
@RefreshScope
public class RouteConfiguration {
    @Bean
    public RouteLocator routes(
            RouteLocatorBuilder builder,
            AuthenticationPrefilter authFilter) {
        return builder.routes()
                .route("authService", r -> r.path("/auth-service/**")
                        .filters(f -> f.rewritePath("/auth-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authFilter.apply(
                                                new AuthenticationPrefilter.Config())))
                        .uri("lb://AUTH-SERVICE"))
                .route("bookService", r -> r.path("/book-service/**")
                        .filters(f ->  f.rewritePath("/book-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authFilter.apply(
                                        new AuthenticationPrefilter.Config())))
                        .uri("lb://BOOK-SERVICE"))
                .route("menuService", r -> r.path("/menu-service/**")
                        .filters(f ->  f.rewritePath("/menu-service(?<segment>/?.*)", "$\\{segment}")
                                .filter(authFilter.apply(
                                        new AuthenticationPrefilter.Config())))
                        .uri("lb://MENU-SERVICE"))
                .build();
    }
}
