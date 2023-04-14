package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Pregunta;


@Component
public class PreguntaListaAssembler implements RepresentationModelAssembler<Pregunta, PreguntaListaModel>{

	@Override
	public PreguntaListaModel toModel(Pregunta entity) {
		PreguntaListaModel model = new PreguntaListaModel();
		model.setEnunciado(entity.getEnunciado());
		model.add(
				linkTo(methodOn(PreguntaController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public CollectionModel<PreguntaListaModel> toCollection(List<Pregunta> lista) {
		CollectionModel<PreguntaListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
		collection.add(
				linkTo(methodOn(PreguntaController.class).all()).withRel("preguntas")
				);
		return collection;
	}

}
