package hr.algebra.juristiq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "hr.algebra.juristiq.models")
public class JuristiQApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuristiQApplication.class, args);
	}

}
