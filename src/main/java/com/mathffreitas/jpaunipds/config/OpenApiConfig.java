package com.mathffreitas.jpaunipds.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI jpaUnipdsOpenAPI(@Value("${server.servlet.context-path:}") String contextPath) {
        String serverUrl = contextPath.isBlank() ? "/" : contextPath;

        return new OpenAPI()
                .info(new Info()
                        .title("JPA UNIPDS API")
                        .description("Conference management REST API with OpenAPI documentation and HATEOAS links")
                        .version("v1.0.0"))
                .addServersItem(new Server().url(serverUrl));
    }

    @Bean
    public OperationCustomizer pageableSortCustomizer() {
        return (operation, handlerMethod) -> {
            if (operation.getParameters() == null) {
                return operation;
            }

            operation.getParameters().stream()
                    .filter(parameter -> "sort".equals(parameter.getName()))
                    .forEach(parameter -> {
                        parameter.setExample("id,asc");
                        parameter.setSchema(new ArraySchema().items(new StringSchema().example("id")));
                    });

            return operation;
        };
    }
}
