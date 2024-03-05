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

	@GetMapping("api/auth/signup")
	public String apiAuthSignup(){
		return "auth/signup";
	}

	@GetMapping("api/auth/signin")
	public String apiAuthSignin(){
		return "auth/signin";
	}

	@GetMapping("api/auth/refresh")
	public String apiAuthRefresh(){
		return "auth/refresh";
	}

	@GetMapping("api/auth/logout")
	public String apiAuthLogout(){
		return "auth/Logout";
	}
}
