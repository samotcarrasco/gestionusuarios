package es.mdef.gestionusuarios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionUsuariosApplication {
	public static final Logger log = LoggerFactory.getLogger(GestionUsuariosApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(GestionUsuariosApplication.class, args);
	}

}