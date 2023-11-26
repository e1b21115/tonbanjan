package ipeko.tonbanjan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TonbanjanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TonbanjanApplication.class, args);
	}

}
