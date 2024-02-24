package online.courseal.courseal_backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CoursealBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursealBackendApplication.class, args);
	}

	@GetMapping("/api/courseal-info")
	public String apiInfo() {
		return "TODO: actual api info";
	}
}
