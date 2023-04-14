package es.mdef.gestionusuarios.REST;

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

import es.mdef.gestionusuarios.GestionUsuariosApplication;
import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.NoAdministrador;
import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.entidades.Usuario.Rol;
import es.mdef.gestionusuarios.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	private final UsuarioRepositorio repositorio;
	private final UsuarioAssembler assembler;
	private final UsuarioListaAssembler listaAssembler;
	private final Logger log;
	
	UsuarioController(UsuarioRepositorio repositorio, UsuarioAssembler assembler, 
			UsuarioListaAssembler listaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		log = GestionUsuariosApplication.log;
	}
	
	@GetMapping("{id}")
	public EntityModel<Usuario> one(@PathVariable int id) {
		Usuario usuario = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "usuario"));
		log.info("Recuperado " + usuario);
		return assembler.toModel(usuario);
	}
	
	@PutMapping("{id}")
	public EntityModel<Usuario> edit(@PathVariable int id, @RequestBody UsuarioModel model) {
		
		Usuario Usuario = repositorio.findById(id).map(usu -> {
			usu.setNombre(model.getNombre());
			usu.setNombreUsuario(model.getNombreUsuario());
			usu.setPassword(model.getNombre());

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
		log.info("Actualizado " + Usuario);
		return assembler.toModel(Usuario);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable int id) {
		log.info("Borrado Usuario " + id);
		repositorio.deleteById(id);
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
	@PostMapping
	public EntityModel<Usuario> add(@RequestBody UsuarioModel model) {
		Usuario Usuario = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + Usuario);
		return assembler.toModel(Usuario);
	}
	
}