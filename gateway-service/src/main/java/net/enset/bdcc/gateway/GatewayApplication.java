package net.enset.bdcc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


    //@Bean
    public RouteLocator manualRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", spec -> spec.path("/api/**").uri("lb://discovery-service"))
                .build();
    }

    @Bean
    public DiscoveryClientRouteDefinitionLocator dynamicRouteLocator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties props) {
        return new DiscoveryClientRouteDefinitionLocator(rdc, props);
    }
}
