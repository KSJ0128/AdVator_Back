package kdt.advator.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${swagger.server-url}")
    private String serverUrl;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo())
                .servers(List.of(
                        new Server().url(serverUrl).description("Server")
                ));
    }

    private Info apiInfo() {
        return new Info()
                .title("AdVator") // API의 제목
                .description("엘리베이터TV 광고 중개 플랫폼 “애드베이터(AdVator)") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
