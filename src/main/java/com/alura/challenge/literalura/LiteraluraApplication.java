package com.alura.challenge.literalura;

import com.alura.challenge.literalura.principal.Principal;
import com.alura.challenge.literalura.repository.AutorRepository;
import com.alura.challenge.literalura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.alura.challenge.literalura.repository")
@ComponentScan(basePackages = {"com.alura.Literalura"})
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibrosRepository librosRepository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(librosRepository, autorRepository);
		principal.muestraElMenu();
	}
}