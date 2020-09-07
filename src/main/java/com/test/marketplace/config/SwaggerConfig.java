package com.test.marketplace.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.test.marketplace.constants.SwaggerConstants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:lang/lang-${marketplace.lang}.properties")
public class SwaggerConfig {
	
	@Value("${swagger.titulo}")
	private String titulo;
	
	@Value("${swagger.descripcion}")
	private String descripcion;
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
        .select()                                       
        .apis(RequestHandlerSelectors.basePackage(SwaggerConstants.PACKAGE_CONTROLLER))
        .paths(regex("/.*"))              
        .build()
        .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
    	Contact contacto = new Contact(SwaggerConstants.NAME_CONTACT, SwaggerConstants.CONTACT_URL, SwaggerConstants.CONTACT_EMAIL);
    	return new ApiInfoBuilder()
        .title(titulo)
        .description(descripcion)
        .termsOfServiceUrl(SwaggerConstants.TERMS_URL)
        .contact(contacto)
        .license(SwaggerConstants.LICENCE)
        .licenseUrl(SwaggerConstants.LICENCE_URL)
        .version(SwaggerConstants.VERSION)
        .build();
    }
}
