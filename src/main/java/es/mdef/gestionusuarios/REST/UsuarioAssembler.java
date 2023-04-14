package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Administrador;
import es.mdef.gestionusuarios.entidades.NoAdministrador;
import es.mdef.gestionusuarios.entidades.Usuario;

@Component
//public class UsuarioAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{
public class UsuarioAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{
	
	@Override
	public EntityModel<Usuario> toModel(Usuario entity) {
		EntityModel<Usuario> model = EntityModel.of(entity);
		model.add(
				linkTo(methodOn(UsuarioController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public Usuario toEntity(UsuarioModel model) {
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
}