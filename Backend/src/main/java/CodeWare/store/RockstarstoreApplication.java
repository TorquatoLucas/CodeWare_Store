package CodeWare.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "CodeWare.store")
public class RockstarstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(RockstarstoreApplication.class, args);
	}

}
