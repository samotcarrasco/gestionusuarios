package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Categoria;



@Component
public class CategoriaAssembler implements RepresentationModelAssembler<Categoria, CategoriaModel>{

	
	@Override
	public CategoriaModel toModel(Categoria entity) {
		CategoriaModel model = new CategoriaModel();
		model.setCategoria(entity.getCategoria());
		model.setDescripcion(entity.getDescripcion());
		model.setGrupo(entity.getGrupo());
		model.setId(entity.getId());
		model.setMinMilis(entity.getMinMilis());
		model.setMaxMilis(entity.getMaxMilis());
		
		//devolvemos el numero de materiales que tiene la categoria
		int numMateriales = entity.getMateriales() != null ? entity.getMateriales().size() : 0;

		model.setNumMateriales(numMateriales);
				
		model.add(
				linkTo(methodOn(CategoriaController.class).one(entity.getId())).withSelfRel(),
		     	linkTo(methodOn(CategoriaController.class).materialesDeCategoria(entity.getId())).withRel("materiales")
				);
		return model;
	}
	
	
	
	
	public Categoria toEntity(CategoriaPostModel model) {
		Categoria categoria = new Categoria();
		categoria.setCategoria(model.getCategoria());
		categoria.setDescripcion(model.getDescripcion());
		categoria.setGrupo(model.getGrupo());
		categoria.setMinMilis(model.getMinMilis());
		categoria.setMaxMilis(model.getMaxMilis());
		
		return categoria;
	}
}
