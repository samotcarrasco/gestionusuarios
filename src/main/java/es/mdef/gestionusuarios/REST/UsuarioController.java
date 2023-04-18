package es.mdef.gestionusuarios.REST;

import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.h2.util.json.JSONObject;
import org.h2.util.json.JSONValue;
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

import es.mdef.gestionusuarios.GestionUsuariosApplication;
import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.NoAdministrador;
import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.entidades.Usuario.Rol;
import es.mdef.gestionusuarios.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioRepositorio repositorio;
	private final UsuarioAssembler assembler;
	private final UsuarioListaAssembler listaAssembler;
	private final PreguntaListaAssembler prListaAssembler;
	private final Logger log;
	
	UsuarioController(UsuarioRepositorio repositorio, UsuarioAssembler assembler, 
			UsuarioListaAssembler listaAssembler, PreguntaListaAssembler prListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.prListaAssembler = prListaAssembler;
		log = GestionUsuariosApplication.log;
	}
	
	@GetMapping("{id}")
	public EntityModel<Usuario> one(@PathVariable Long id) {
		Usuario usuario = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		log.info("Recuperado " + usuario);
		return assembler.toModel(usuario);
	}
	
	@GetMapping("{id}/preguntas")
	public CollectionModel<PreguntaListaModel> preguntasDeUsuario(@PathVariable Long id) {
		Usuario usuario = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
	    return prListaAssembler.toCollection(usuario.getPreguntas());
	}
	
	@PostMapping
	public EntityModel<Usuario> add(@RequestBody UsuarioModel model) {
		Usuario Usuario = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + Usuario);
		return assembler.toModel(Usuario);
	}
	
	@PutMapping("{id}")
	public EntityModel<Usuario> edit(@PathVariable Long id, @RequestBody UsuarioModel model) {
		
		Usuario usuario = repositorio.findById(id).map(usu -> {
			usu.setNombre(model.getNombre());
			usu.setNombreUsuario(model.getNombreUsuario());
			usu.setPassword(model.getPassword());

			if (usu.getRol() == Rol.Administrator) {
				Administrador admin = (Administrador) usu;
				admin.setTelefono(model.getTelefono());
			} else if (usu.getRol() == Rol.noAdministrator) {
				NoAdministrador noAdmin = (NoAdministrador) usu;
				noAdmin.setDpto(model.getDpto());
				noAdmin.setTipo(model.getTipo());
			}

			//usu.setRol(model.getRol());
			return repositorio.save(usu);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"));
		log.info("Actualizado " + usuario);
		return assembler.toModel(usuario);
	}
	
	@PutMapping("{id}/password")
	public void editPassword(@PathVariable Long id, @RequestBody  String password) {
		log.info("Nueva password " + password);
		// Analiza el objeto JSON
		// Analiza el objeto JSON
//		JSONParser parser = new JSONParser(password);
//		JSONObject jsonObject = (JSONObject) parser.parse();
//		JSONValue passwordPeticion = jsonObject.getFirst(password);
//		
//		log.info("passwordPeticion " + passwordPeticion);
//		
//		
		
		Usuario usuario = repositorio.findById(id).map(usu -> {
			usu.setPassword(password);
			return repositorio.save(usu);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "Usuario"));
		log.info("Actualizada constraseña " + usuario);
		//return assembler.toModel(usuario);
		//return EntityModel.ok().build();

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