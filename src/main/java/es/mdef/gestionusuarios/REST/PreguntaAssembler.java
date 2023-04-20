package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Pregunta;

@Component
public class PreguntaAssembler implements RepresentationModelAssembler<Pregunta, PreguntaModel>{
	
	@Override
	public PreguntaModel toModel(Pregunta entity) {
		PreguntaModel model = new PreguntaModel();
		
		model.setEnunciado(entity.getEnunciado());
		model.setNombreDeUsuario(entity.getUsuario().getNombre());
		
		model.add(linkTo(methodOn(PreguntaController.class).one(entity.getId())).withSelfRel());
	    model.add(linkTo(methodOn(UsuarioController.class).one(entity.getUsuario().getId())).withRel("usuario"));
	    //model.add(linkTo(methodOn(PreguntaController.class).oneUsuario(entity.getUsuario().getId())).withRel("usuario"));
	    model.add(linkTo(methodOn(FamiliaController.class).one(entity.getUsuario().getId())).withRel("familia"));
	   return model;
	}
	
	
//	public EntityModel<Pregunta> toModel(Pregunta entity) {
//		EntityModel<Pregunta> model = EntityModel.of(entity);
//		  model.add(linkTo(methodOn(PreguntaController.class).one(entity.getId())).withSelfRel());
//	      model.add(linkTo(methodOn(UsuarioController.class).one(entity.getUsuario().getId())).withRel("usuario"));
//	      //model.add(linkTo(methodOn(PreguntaController.class).oneUsuario(entity.getUsuario().getId())).withRel("usuario"));
//	      model.add(linkTo(methodOn(FamiliaController.class).one(entity.getUsuario().getId())).withRel("familia"));
//	   return model;
//	}
//	
	
	public Pregunta toEntity(PreguntaModel model) {
		Pregunta pregunta = new Pregunta();
		pregunta.setEnunciado(model.getEnunciado());
//		pregunta.setUsuario(model.getUsuario());
//		pregunta.setFamilia(model.getFamilia());
		return pregunta;
	}
	

	
	public Pregunta toEntity(PreguntaPostModel model) {
		Pregunta pregunta = new Pregunta();
		pregunta.setEnunciado(model.getEnunciado());
		pregunta.setUsuario(model.getUsuario());
		pregunta.setFamilia(model.getFamilia());
		return pregunta;
	}
}
