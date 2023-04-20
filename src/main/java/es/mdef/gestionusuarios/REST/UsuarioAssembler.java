package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.NoAdministrador;
import es.mdef.gestionusuarios.entidades.Usuario;
import es.mdef.gestionusuarios.entidades.Usuario.Rol;

@Component
public class UsuarioAssembler implements RepresentationModelAssembler<Usuario, UsuarioModel>{
	
	@Override
	public UsuarioModel toModel(Usuario entity) {
		UsuarioModel model = new UsuarioModel();
		
		model.setNombre(entity.getNombre());
		model.setNombreUsuario(entity.getNombreUsuario());
		model.setRol(entity.getRol());
		model.add(linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel());

		if (model.getRol() == Rol.Administrator) {
			model.setTelefono(((Administrador) entity).getTelefono());
		} else if (model.getRol() == Rol.noAdministrator) {
			model.setDpto(((NoAdministrador)entity).getDpto());
			model.setTipo(((NoAdministrador)entity).getTipo());
		}
		
		return model;
	}
//	public EntityModel<Usuario> toModel(Usuario entity) {
//		EntityModel<Usuario> model = EntityModel.of(entity);
//		model.add(
//				linkTo(methodOn(UsuarioControll er.class).one(entity.getId())).withSelfRel(),
//		     	linkTo(methodOn(UsuarioController.class).preguntasDeUsuario(entity.getId())).withRel("preguntas"),
//		     	linkTo(methodOn(UsuarioController.class).familiasDeUsuario(entity.getId())).withRel("familias")
//				);
//		return model;
//	}
//	
	
	
//	public EntityModel<Usuario> toModelPost(Usuario entity) {
//		EntityModel<Usuario> model = EntityModel.of(entity);
//		return model;
//	}
	
	public Usuario toEntity(UsuarioPostModel model) {
		Usuario usuario;
		
		switch (model.getRol()) {
		case Administrator:
			Administrador admin = new Administrador();
			admin.setTelefono(model.getTelefono());
			usuario = admin;
			break;
		case noAdministrator: 
			NoAdministrador noAdmin = new NoAdministrador();
			noAdmin.setTipo(model.getTipo());
			noAdmin.setDpto(model.getDpto());
			usuario = noAdmin;
			break;				
		default:
			usuario = new Usuario();
		}
		
		usuario.setNombre(model.getNombre());
		usuario.setNombreUsuario(model.getNombreUsuario());
		usuario.setPassword(model.getPassword());
		return usuario;
	}
	
	
	//sobrecarga del método
	public Usuario toEntity(UsuarioPutModel model) {
		Usuario usuario;
		
		switch (model.getRol()) {
		case Administrator:
			Administrador admin = new Administrador();
			admin.setTelefono(model.getTelefono());
			usuario = admin;
			break;
		case noAdministrator: 
			NoAdministrador noAdmin = new NoAdministrador();
			noAdmin.setTipo(model.getTipo());
			noAdmin.setDpto(model.getDpto());
			usuario = noAdmin;
			break;				
		default:
			usuario = new Usuario();
		}
		
		usuario.setNombre(model.getNombre());
		usuario.setNombreUsuario(model.getNombreUsuario());
		return usuario;
	}
}