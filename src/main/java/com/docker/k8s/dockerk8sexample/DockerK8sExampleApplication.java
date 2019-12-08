package com.docker.k8s.dockerk8sexample;

import static springfox.documentation.builders.PathSelectors.regex;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { HypermediaAutoConfiguration.class })
@EnableSwagger2
public class DockerK8sExampleApplication {

	@Value("${apiVersion}")
	private String apiVersion;
	
	public static void main(String[] args) {
		SpringApplication.run(DockerK8sExampleApplication.class, args);
	}
	
	/**
	 * Bean for Swagger
	 * 
	 * @return
	 */
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Calculator").apiInfo(apiInfo()).select()
				.paths(regex("/v1.*")).build().directModelSubstitute(XMLGregorianCalendar.class, MixIn.class);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Fibonacci series calculator REST APIs").description("Fibonacci series calculator REST APIs")
				.termsOfServiceUrl("http://....").contact("Mahesh Kumar").license("MK Licensed")
				.licenseUrl("https://myurl.com").version(apiVersion).build();
	}
	
	@FunctionalInterface
	public static interface MixIn {
		@JsonIgnore
		public void setYear(int year);
	}

	
}
