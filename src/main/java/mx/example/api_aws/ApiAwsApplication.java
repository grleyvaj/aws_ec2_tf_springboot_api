package mx.example.api_aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"mx.example.api_aws.application.bean",
				"mx.example.api_aws.application.controller",
		}
)
public class ApiAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAwsApplication.class, args);
	}

}
