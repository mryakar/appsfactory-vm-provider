package de.appsfactory.vmprovider.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author orcun.altindal
 */
@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String AUTH_WHITELIST_SWAGGER_RESOURCES = "/swagger-resources/**";

    public static final String AUTH_WHITELIST_SWAGGER_API_DOCS = "/v3/api-docs/**";

    public static final String AUTH_WHITELIST_SWAGGER_UI = "/swagger-ui/**";

    /**
     * ApiInfo, security context ve security sheme leri içinde barındıran bir API Docket bean'i oluşturan method.
     *
     * @return API Docket objesi.
     */
    @Bean
    public Docket swaggerDocket() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
//                .securityContexts(Lists.newArrayList(securityContext()))
//                .securitySchemes(Lists.newArrayList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("de.appsfactory.vmprovider.resource"))
                .paths(PathSelectors.any())
                .build();
    }

//    /**
//     * Authorize end-point'ine yapılacak olan request içerisindeki header bölümünü belirler.
//     *
//     * @return Springfox ApiKey objesi.
//     */
//    private ApiKey apiKey() {
//        return new ApiKey(AUTHORIZATION_HEADER, AUTHORIZATION_HEADER, "header");
//    }

//    /**
//     * JWT security context oluşturmak için kullanılan method.
//     *
//     * @return SecurityContext objesi.
//     */
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .build();
//    }
//
//    /**
//     * Configure security context with a global AuthorizationScope.
//     *
//     * @return SecurityReference list.
//     */
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Lists.newArrayList(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
//    }

    /**
     * Retrieve general information about the service.
     *
     * @return ApiInfo object which includes basic information.
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Vehicle Manufacturer API",
                "API that provides CRUD operations for vehicle manufactures",
                "v1.0.0",
                "Terms of service",
                new Contact(
                        "AppsFactory",
                        "appsfactory.de",
                        "ahmetyakar@pm.me"
                ),
                "",
                "", Collections.emptyList()
        );
    }
}
