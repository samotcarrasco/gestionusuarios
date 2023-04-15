package es.mdef.gestionusuarios.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {	    
        //List<Usuario> findUsuarioByNombre(String nombre);
}
