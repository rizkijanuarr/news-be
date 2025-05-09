package com.kiki.newsbe.configs;

import com.kiki.newsbe.annotations.swagger.*;
import com.kiki.newsbe.controller.advices.BaseController;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.reflections.Reflections;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class SwaggerConfig {
    @Value("${spring.application.version}")
    private String versionData;

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("TemanWeb REST API")
                        .description("This Api Collection For Spring boot data")
                        .version(versionData)
                        .contact(new Contact().name("TemanWeb")
                                .email("www.temanweb.com"))
                        .license(new License().name("License of API")
                                .url("API license URL")));
    }

    @Bean
    @DependsOn("getListEndpointByType")
    public GroupedOpenApi customGroupedOpenApiDefault(
            Map<SwaggerTypeGroup, List<String>> listEndpointByType) throws IOException, ClassNotFoundException {
        List<String> strings = listEndpointByType.get(SwaggerTypeGroup.DEFAULT);
        String[] pathsArray = {""};
        if (!strings.isEmpty())
            pathsArray = strings.toArray(new String[0]);

        return GroupedOpenApi.builder()
                .group(SwaggerTypeGroup.DEFAULT.getValue())
                .pathsToMatch(pathsArray)
                .build();

    }

    @Bean
    @DependsOn("getListEndpointByType")
    public GroupedOpenApi customGroupedOpenApiAppsWeb(Map<SwaggerTypeGroup, List<String>> listEndpointByType) throws IOException, ClassNotFoundException {
        List<String> strings = listEndpointByType.get(SwaggerTypeGroup.APPS_WEB);
        String[] pathsArray = {""};
        if (!strings.isEmpty())
            pathsArray = strings.toArray(new String[0]);
        return GroupedOpenApi.builder()
                .group(SwaggerTypeGroup.APPS_WEB.getValue())
                .pathsToMatch(pathsArray)
                .build();
    }

    @Bean
    @DependsOn("getListEndpointByType")
    public GroupedOpenApi customGroupedOpenApiAppsMobile(
            Map<SwaggerTypeGroup, List<String>> listEndpointByType) throws IOException, ClassNotFoundException {
        List<String> strings = listEndpointByType.get(SwaggerTypeGroup.APPS_MOBILE);
        String[] pathsArray = {""};
        if (!strings.isEmpty())
            pathsArray = strings.toArray(new String[0]);

        return GroupedOpenApi.builder()
                .group(SwaggerTypeGroup.APPS_MOBILE.getValue())
                .pathsToMatch(pathsArray)
                .build();

    }


    @Bean("getListEndpointByType")
    public Map<SwaggerTypeGroup, List<String>> getListEndpointByType() throws IOException,
            ClassNotFoundException {
        Reflections reflections = new Reflections("com.kiki.newsbe.controller");
        Class<BaseController> baseControllerClass = BaseController.class;
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(baseControllerClass);
        Map<SwaggerTypeGroup, List<String>> swaggerTypeGroupListMap = SwaggerTypeGroup.swaggerTypeGroupListMapGetDefault();
        List<String> endpointList = new ArrayList<>();

        for (Class<?> clazz : annotatedClasses) {
            String controllerPath = null;
            BaseController clazzAnnotation = clazz.getAnnotation(BaseController.class);
            if(clazzAnnotation!=null) {
                controllerPath = clazz.getAnnotation(baseControllerClass).value();
            }
            for (Method declaredMethod : clazz.getDeclaredMethods()) {
                GetEndpoint getEndpoint = declaredMethod.getAnnotation(GetEndpoint.class);
                DeleteEndpoint deleteEndpoint = declaredMethod.getAnnotation(DeleteEndpoint.class);
                OptionsEndpoint optionsEndpoint = declaredMethod.getAnnotation(OptionsEndpoint.class);
                PatchEndpoint patchEndpoint = declaredMethod.getAnnotation(PatchEndpoint.class);
                PostEndpoint postEndpoint = declaredMethod.getAnnotation(PostEndpoint.class);
                PutEndpoint putEndpoint = declaredMethod.getAnnotation(PutEndpoint.class);
                String methodPath = null;
                SwaggerTypeGroup group = SwaggerTypeGroup.DEFAULT;

                if (getEndpoint != null) {
                    group = getEndpoint.group();
                    String[] value = getEndpoint.value();
                    if (value.length > 0)
                        methodPath = value[0];
                } else if (postEndpoint != null) {
                    group = postEndpoint.group();
                    String[] value = postEndpoint.value();
                    if (value.length > 0)
                        methodPath = value[0];

                } else if (putEndpoint != null) {
                    group = putEndpoint.group();
                    String[] value = putEndpoint.value();
                    if (value.length > 0)
                        methodPath = value[0];

                } else if (patchEndpoint != null) {
                    group = patchEndpoint.group();
                    String[] value = patchEndpoint.value();
                    if (value.length > 0)
                        methodPath = value[0];

                } else if (deleteEndpoint != null) {
                    group = deleteEndpoint.group();
                    String[] value = deleteEndpoint.value();
                    if (value.length > 0)
                        methodPath = value[0];
                } else if (optionsEndpoint != null) {
                    group = optionsEndpoint.group();
                    String[] value = optionsEndpoint.value();
                    if (value.length > 0)
                        methodPath = value[0];
                } else {
                    continue;
                }
                swaggerTypeGroupListMap
                        .computeIfAbsent(group, k -> new ArrayList<>());
                String endpointCollection = null;
                if (methodPath != null)
                    endpointCollection = "/" + controllerPath + "/" + methodPath;
                else
                    endpointCollection = "/" + controllerPath;
                endpointCollection = endpointCollection.replace("///", "/");
                String fullPath = (endpointCollection).replace("//", "/");
                endpointList.add(fullPath);
                swaggerTypeGroupListMap.get(group).add(fullPath);
            }
        }
        return swaggerTypeGroupListMap;
    }
}