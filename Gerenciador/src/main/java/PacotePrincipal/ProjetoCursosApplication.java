package PacotePrincipal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication(scanBasePackages={"service" , "dao" , "projeto.curso.com.br.web.controller","domain", "PacotePrincipal"})
@EntityScan(basePackages = "domain")
public class ProjetoCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoCursosApplication.class, args);
	}

}
 