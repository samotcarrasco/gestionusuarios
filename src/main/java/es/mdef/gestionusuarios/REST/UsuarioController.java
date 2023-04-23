package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import es.mdef.gestionusuarios.validation.RegisterNotFoundException;
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

	
	//probamos este metodo
	@GetMapping("/buscarusuario")
	public UsuarioModel getByUsername(@RequestParam(value = "username") String username) {
	    Usuario usuario = repositorio.findByUsername(username)
	            .orElseThrow(() -> new RegisterNotFoundException(username, "usuario"));
	    log.info("Recuperado " + usuario);
	    return assembler.toModel(usuario);
	}
	
//	@GetMapping("{id}")
//	public EntityModel<Usuario>  one(@PathVariable Long id) {
//		Usuario usuario = repositorio.findById(id)
//				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
//		log.info("Recuperado " + usuario);
//		return assembler.toModel2(usuario);
//	}
	
	@GetMapping
	public CollectionModel<UsuarioListaModel> all() {
		return listaAssembler.toCollection(repositorio.findAll());
	}

	
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
	public UsuarioModel edit(@Valid @PathVariable Long id,  @RequestBody UsuarioPutModel model) {
	    Usuario usuario = repositorio.findById(id).map(usu -> {
	    	log.info("PUT MODEL" + model);
	    	
	    	//el nombre y username nunca van a ser nullos, porque lo controlamos en el post.
	    	//si no tienen valor en el modeloPut, lo recuperamos del reposituorio.
	    	String nombre = (model.getNombre() != null) ? model.getNombre() : usu.getNombre();
	    	String username = (model.getNombre() != null) ? model.getNombre() : usu.getNombre();
	        
	        //solamente actualizamos los datos necesarios de cada rol cuando corresponda
	        if (model.getRol() == Rol.Administrator) {
	          	Administrador admin = new Administrador();
	        	
	          	if (model.getTelefono() != null) {
	        		//solo actualizamos la bbdd en caso de que el tlf
	        		//en caso contrario, se devolverá un error controlado
	        		repositorio.actualizarUsuarioAdmin(nombre, username, model.getTelefono(), id);
	        	}
	        	
	        	//si es nulo fallará la validación, ,tampoco se habrá hecho el update en bbdd
	        	admin.setTelefono(model.getTelefono());
				
	        	//si en el modelo son nulos (es decir, no se quieren actualilar), devolvemos los del repositorio
	        	if (model.getNombre() != null){ admin.setNombre(model.getNombre()); } 
				    else admin.setNombre(usu.getNombre());
				if (model.getUsername() != null){ admin.setUsername(model.getUsername()); } 
				    else admin.setUsername(usu.getUsername());
				
		        admin.setPassword(usu.getPassword());
		        usu = admin;
	        }else if (model.getRol() == Rol.noAdministrator) {
	        	NoAdministrador noAdmin = new NoAdministrador();
	        	
	        	if (model.getDpto() != null && model.getTipo() != null) {
	        		//solo actualizamos la bbdd en caso de que ambos sean no nulos
	        		//en caso contrario, se devolverá un error controlado
	        		repositorio.actualizarUsuarioNoAdmin(nombre, username, model.getDpto().ordinal(), model.getTipo().ordinal(), id);
	        	}
	        	//si son nulos fallará la validación,tampoco se habrá hecho el update en bbdd
	        	noAdmin.setDpto(model.getDpto());
        		noAdmin.setTipo(model.getTipo());
        		
        		
        		if (model.getNombre() != null) { noAdmin.setNombre(model.getNombre()); }  
				    else noAdmin.setNombre(usu.getNombre());
				if (model.getUsername() != null) { noAdmin.setUsername(model.getUsername()); }
					else noAdmin.setUsername(usu.getUsername());
				
		        noAdmin.setPassword(usu.getPassword());
		        usu = noAdmin;
	        } else //si no se indica el rol, simplemente actualizamos nombre y username
	        {
	        	if (model.getNombre() != null) usu.setNombre(model.getNombre());
		        if (model.getUsername() != null) usu.setUsername(model.getUsername());
	        }
	        
	        usu.setId(id);
            return repositorio.save(usu);
        })
        .orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"));	    
	    log.info("Actualizado ---->>>> " + usuario);
	    

	    //finalmente, llamamos al metodo toModel para devolver el modelo
	    return assembler.toModel(usuario);
	}
	
	@PutMapping("{id}/password")
	public void editPassword(@Valid @PathVariable Long id, @RequestBody String password) {
		log.info("Nueva password " + password);
		
		Usuario usuario = repositorio.findById(id).map(usu -> {
			usu.setPassword(new BCryptPasswordEncoder().encode(password));
	      	return repositorio.save(usu);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"));
		log.info("Actualizada constraseña " + usuario);
	}
	
	//emplementamos el método con otro endpoint usando el método PATCH
	@PatchMapping("{id}/cambiarContrasena")
	public UsuarioModel edit(@Valid @PathVariable Long id, @RequestBody String newPassword) {
		Usuario usuario = repositorio.findById(id).map(usu -> {
			usu.setPassword(new BCryptPasswordEncoder().encode(newPassword));
			return repositorio.save(usu);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		log.info("Actualizado " + usuario);
		return assembler.toModel(usuario);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
	
		Usuario usuario = repositorio.findById(id).map(usu -> {
			repositorio.deleteById(id);	
			return usu;
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
	    
	    log.info("Borrado Usuario " + id);
	}
	
	

	
}