package es.mdef.gestionusuarios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GestionUsuariosApplication {
	public static final Logger log = LoggerFactory.getLogger(GestionUsuariosApplication.class);
	
	public static void main(String[] args) {
		System.err.println(new BCryptPasswordEncoder().encode("password"));
		SpringApplication.run(GestionUsuariosApplication.class, args);
	}

}
