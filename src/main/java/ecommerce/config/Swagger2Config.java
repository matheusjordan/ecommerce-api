package ecommerce.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import io.swagger.models.Contact;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Bean
	public Docket api() {
		Docket swaggerDoc = new Docket(DocumentationType.SWAGGER_2);
		
		//Selecting my base package to doc
		Predicate<RequestHandler> requestHandler = RequestHandlerSelectors.basePackage("ecommerce");
		
		//Selecting my base package to doc
		Predicate<String> basePath = PathSelectors.any();
		
		swaggerDoc.select().apis(requestHandler).paths(basePath).build().apiInfo(apiEndpointsInfo());
		
		return swaggerDoc;
	}
	
	private ApiInfo apiEndpointsInfo() {
		
		//Contact object to  use in ApiInfo.contact()
		Contact contact = new Contact();
		contact.name("Matheus Jordan").email("matheusj1599@outlook.com").url("github.com/matheusjordan");
		
		ApiInfo info = new ApiInfoBuilder()
				.title("Ecommerce Spring Boot API.")
				.description("Study API develop for CTIS employeement.")
				.license("Mjordan Corp licensed")
				.version("1.0")
				.build();
		
		return info;
	}

}
