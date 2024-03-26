package online.courseal.courseal_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Configuration
public class CoursealBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursealBackendApplication.class, args);
	}

	/*
	@GetMapping("/api/courseal-info")
	public String apiInfo() {
		return "TODO: actual api info";
	}
	 */

	@GetMapping("api/user/register")
	public String apiUserRegister(){
		return "auth/signup";
	}

	@GetMapping("api/user/change-name")
	public String apiUserChangeName(){
		return "user/change-name";
	}

	@GetMapping("api/auth/login")
	public String apiAuthLogin(){
		return "auth/login";
	}

	@GetMapping("api/auth/refresh")
	public String apiAuthRefresh(){
		return "auth/refresh";
	}

	@GetMapping("api/auth/logout")
	public String apiAuthLogout(){
		return "auth/Logout";
	}

	@GetMapping("api/course-management/create-course")
	public String apiCourseManagementCreateCourse(){
		return "course-management/create-course";
	}
}
