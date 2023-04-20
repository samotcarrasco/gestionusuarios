package es.mdef.gestionusuarios.REST;

import java.util.List;
import java.util.stream.Collectors;

//import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;
import org.slf4j.Logger;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import es.mdef.gestionusuarios.GestionUsuariosApplication;
import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.FamiliaImpl;
import es.mdef.gestionusuarios.entidades.NoAdministrador;
import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.entidades.Usuario.Rol;
import es.mdef.gestionusuarios.repositorios.UsuarioRepositorio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioRepositorio repositorio;
	private final UsuarioAssembler assembler;
	private final UsuarioListaAssembler listaAssembler;
	private final PreguntaListaAssembler prListaAssembler;
	private final FamiliaListaAssembler famListaAssembler;
	private final Logger log;
	
	UsuarioController(UsuarioRepositorio repositorio, UsuarioAssembler assembler, 
			UsuarioListaAssembler listaAssembler, PreguntaListaAssembler prListaAssembler,
			FamiliaListaAssembler famListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.prListaAssembler = prListaAssembler;
		this.famListaAssembler = famListaAssembler;
		log = GestionUsuariosApplication.log;
	}
	
	@GetMapping("{id}")
	public UsuarioModel one(@PathVariable Long id) {
		Usuario usuario = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		log.info("Recuperado " + usuario);
		return assembler.toModel(usuario);
	}
//	
//	@GetMapping("{id}")
//	public EntityModel<Usuario>  one(@PathVariable Long id) {
//		Usuario usuario = repositorio.findById(id)
//				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
//		log.info("Recuperado " + usuario);
//		return assembler.toModel(usuario);
//	}
//	
	
	@GetMapping("{id}/preguntas")
	public CollectionModel<PreguntaListaModel> preguntasDeUsuario(@PathVariable Long id) {
		Usuario usuario = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
	    return prListaAssembler.toCollection(usuario.getPreguntas());
	}
	
	
	
	@GetMapping("{id}/familias")
	public CollectionModel<FamiliaListaModel> familiasDeUsuario(@PathVariable Long id) {
		   Usuario usuario = repositorio.findById(id)
		            .orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));

		    List<FamiliaImpl> familias = usuario.getPreguntas().stream()
		            .map(Pregunta::getFamilia)
		            .distinct()
		            .collect(Collectors.toList());

		    return famListaAssembler.toCollection(familias);	    
	}
	
	
	@PostMapping
	public UsuarioModel add(@Valid @RequestBody UsuarioPostModel model) {
		Usuario usuario = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + usuario);
		return assembler.toModel(usuario);
		//return assembler.toModelPost(usuario);
	}
	
	@PutMapping("{id}")
	public UsuarioModel edit(@PathVariable Long id, @RequestBody UsuarioPutModel model) {
Usuario usuario = repositorio.findById(id).map(usu -> {
			
			Usuario us = null;
			
			if (model.getRol() == Rol.Administrator) {
				Administrador admin = new Administrador();
				admin.setTelefono(model.getTelefono());
				us = admin;
			} else if (model.getRol() == Rol.noAdministrator) {
				NoAdministrador noAdmin = new NoAdministrador();
				noAdmin.setDpto(model.getDpto());
				noAdmin.setTipo(model.getTipo());
				us = noAdmin;
			}
			
			us.setId(id);
			us.setNombre(model.getNombre());
			us.setUserName(model.getUserName());
			us.setRol(model.getRol());
//			usu.setAccountNonExpired(model.isAccountNonExpired());
//			usu.setAccountNonLocked(model.isAccountNonLocked());
//			usu.setCredentialsNonExpired(empleado.isCredentialsNonExpired());
//			usu.setEnabled(modelmodel.isEnabled());
			usu.setAccountNonExpired(true);
			usu.setAccountNonLocked(true);
			usu.setCredentialsNonExpired(true);
			usu.setEnabled(true);

			

			
			//usu.setRol(model.getRol());
			return repositorio.save(us);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"));
		log.info("Actualizado " + usuario);
		return assembler.toModel(usuario);
	//	return null;
		
//		Usuario usuario = repositorio.findById(id).map(usu -> {
//			
//			Usuario us = null;
//			
//			if (model.getRol() == Rol.Administrator) {
//				Administrador admin = new Administrador();
//				admin.setTelefono(model.getTelefono());
//				us = admin;
//			} else if (model.getRol() == Rol.noAdministrator) {
//				NoAdministrador noAdmin = new NoAdministrador();
//				noAdmin.setDpto(model.getDpto());
//				noAdmin.setTipo(model.getTipo());
//				us = noAdmin;
//			}
//			
//			us.setNombre(model.getNombre());
//			us.setNombreUsuario(model.getNombreUsuario());
//			us.setRol(model.getRol());
//			
//			
//			//usu.setRol(model.getRol());
//			return repositorio.save(us);
//		})
//		.orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"));
//		
//		return assembler.toModel(usuario);

	}
	
	@PutMapping("{id}/password")
	public void editPassword(@PathVariable Long id, @RequestBody String password) {
		log.info("Nueva password " + password);

		
		Usuario usuario = repositorio.findById(id).map(usu -> {
			usu.setPassword(new BCryptPasswordEncoder().encode(password));
	      	return repositorio.save(usu);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"));
		log.info("Actualizada constraseña " + usuario);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
	    log.info("Borrado Usuario " + id);
//	    if (repositorio.existsById(id)) {
	        repositorio.deleteById(id);
//	    } else {
//	        throw new RegisterNotFoundException(id, "usuario");
//	    }
	}
	
	
	@GetMapping
	public CollectionModel<UsuarioListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
//	@GetMapping("porEstado")
//	public CollectionModel<UsuarioListaModel> usuariosPorEstado(@RequestParam UsuarioEstado estado) {
//		return listaAssembler.toCollection(
//				repositorio.findUsuarioByEstado(estado)
//				);
//	}
//	

	
}