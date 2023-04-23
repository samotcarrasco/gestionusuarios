package es.mdef.gestionusuarios.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.entidades.Usuario.Rol;
import jakarta.transaction.Transactional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUsername(String username);
		
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.usuarios SET "
					+ "nombre = :nombre, "
					+ "username = :username, "
					+ "rol_usuario = 'A',"
					+ "telefono = :telefono, " 
					+ "dpto = null, "
					+ "tipo = null "
					+ "WHERE id = :id", nativeQuery = true)
	void actualizarUsuarioAdmin(
	    @Param("nombre") String nombre,
	    @Param("username") String username,
	    @Param("telefono") String telefono,
	    @Param("id") Long id
	);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE public.usuarios SET "
					+ "nombre = :nombre, "
					+ "username = :username, "
					+ "rol_usuario = 'N', "
					+ "telefono = null, "
					+ "dpto = :dpto, "
					+ "tipo = :tipo "
					+ "WHERE id = :id", nativeQuery = true)
	void actualizarUsuarioNoAdmin(
	@Param("nombre") String nombre,
    @Param("username") String userrname,
    @Param("dpto") int dpto,
    @Param("tipo") int tipo,
    @Param("id") Long id
);
	

}	
