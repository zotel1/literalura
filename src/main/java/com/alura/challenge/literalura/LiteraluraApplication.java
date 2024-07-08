package com.alura.challenge.literalura;

import com.alura.challenge.literalura.principal.Principal;
import com.alura.challenge.literalura.repository.AutorRepository;
import com.alura.challenge.literalura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.alura.challenge.literalura")
public class LiteraluraApplication implements CommandLineRunner {

	private final LibrosRepository librosRepository;
	private final AutorRepository autorRepository;

	public LiteraluraApplication(LibrosRepository librosRepository, AutorRepository autorRepository) {
		this.librosRepository = librosRepository;
		this.autorRepository = autorRepository;
	}


	@Autowired
	private LibrosRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(librosRepository, autorRepository);
		principal.muestraElMenu();
	}
}
