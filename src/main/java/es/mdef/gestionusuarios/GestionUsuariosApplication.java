package es.mdef.gestionusuarios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import es.mde.acing.utils.Material;


@SpringBootApplication
public class GestionUsuariosApplication {
	public static final Logger log = LoggerFactory.getLogger(GestionUsuariosApplication.class);
	
	public static void main(String[] args) {
		System.err.println(new BCryptPasswordEncoder().encode("password"));
		SpringApplication.run(GestionUsuariosApplication.class, args);
		
		//probamos librería externa alojada en github
		Material mat = new Material(1,"ordenador");
	    System.out.println(mat.toString());
	}
	
}
