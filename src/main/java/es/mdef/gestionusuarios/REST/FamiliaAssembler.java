package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.FamiliaImpl;
import es.mdef.gestionusuarios.entidades.FamiliaImpl;
import es.mdef.gestionusuarios.entidades.Pregunta;
import es.mdef.gestionusuarios.entidades.Usuario;

@Component
public class FamiliaAssembler implements RepresentationModelAssembler<FamiliaImpl, FamiliaModel>{
	
//	@Override
//	public EntityModel<Familia> toModel(Familia entity) {
//		EntityModel<Familia> model = EntityModel.of(entity);
//		model.add(
//				linkTo(methodOn(FamiliaController.class).one(entity.getId())).withSelfRel(),
//		     	linkTo(methodOn(FamiliaController.class).preguntasDeFamilia(entity.getId())).withRel("preguntas"),
//		     	linkTo(methodOn(FamiliaController.class).usuariosDeFamilia(entity.getId())).withRel("usuarios")
//				);
//		return model;
//	}
//	
	
	@Override
	public FamiliaModel  toModel(FamiliaImpl entity) {
		FamiliaModel model = new FamiliaModel();
		
		model.setEnunciado(entity.getEnunciado());
		int tamanio = entity.getPreguntas() != null ? entity.getPreguntas().size() : 0;
		model.setTamanio(tamanio);
		
		model.add(
				linkTo(methodOn(FamiliaController.class).one(entity.getId())).withSelfRel(),
		     	linkTo(methodOn(FamiliaController.class).preguntasDeFamilia(entity.getId())).withRel("preguntas"),
		     	linkTo(methodOn(FamiliaController.class).usuariosDeFamilia(entity.getId())).withRel("usuarios")
				);
		return model;
	}
	
	
	
	public FamiliaImpl toEntity(FamiliaPostModel model) {
		FamiliaImpl familia = new FamiliaImpl();
		familia.setEnunciado(model.getEnunciado());
		//preguntar al profesor
		//familia.setTamanio(Familia.getTamanio());
		return familia;
	}
}
