package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Familia;
import es.mdef.gestionusuarios.entidades.FamiliaImpl;
import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Usuario;

@Component
public class FamiliaAssembler implements RepresentationModelAssembler<FamiliaImpl, EntityModel<FamiliaImpl>>{
	
	
	@Override
	public EntityModel<FamiliaImpl> toModel(FamiliaImpl entity) {
		EntityModel<FamiliaImpl> model = EntityModel.of(entity);
		model.add(
				linkTo(methodOn(FamiliaController.class).one(entity.getId())).withSelfRel(),
		     	linkTo(methodOn(FamiliaController.class).preguntasDeFamilia(entity.getId())).withRel("preguntas"),
		     	linkTo(methodOn(FamiliaController.class).usuariosDeFamilia(entity.getId())).withRel("usuarios")
				);
		return model;
	}
	
	public Familia toEntity(FamiliaModel model) {
		FamiliaImpl familia = new FamiliaImpl();
		familia.setEnunciado(model.getEnunciado());
		return familia;
	}
}
