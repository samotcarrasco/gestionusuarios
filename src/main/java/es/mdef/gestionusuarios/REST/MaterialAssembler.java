package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Categoria;
import es.mdef.gestionusuarios.entidades.Inventariable;
import es.mdef.gestionusuarios.entidades.Material;
import es.mdef.gestionusuarios.entidades.NoInventariable;
import es.mdef.gestionusuarios.entidades.Material.TipoMaterial;



@Component
public class MaterialAssembler implements RepresentationModelAssembler<Material, MaterialModel>{

	
	@Override
	public MaterialModel toModel(Material entity) {
		MaterialModel model = new MaterialModel();
		
		model.setId(entity.getId());
		model.setEstado(entity.getEstado());
		model.setNombre(entity.getNombre());
		model.setDescripcion(entity.getDescripcion());
		model.setCantidad(entity.getCantidad());
		model.setMilis(entity.getMilis());
		model.setDimensiones(entity.getDimensiones());
		model.setPeso(entity.getPeso());
		model.setImagen(entity.getImagen());
				
		//por compodidad para el front, estos tres los devolvemos como String
		model.setCategoriaN(entity.getCategoria().getCategoria());
		model.setDptoOfertaN(entity.getDeptoOferta().getAbreviatura());
		
		String nombreUnidadOferta = entity.getDptoAdquisicion() != null ? entity.getDptoAdquisicion().getAbreviatura() : "-";
		model.setDptoAdquisicionN(nombreUnidadOferta);
		
		
		if (entity.getTipoMaterial() == TipoMaterial.Inventariable) {
			model.setNoc(((Inventariable) entity).getNoc());
			model.setNumeroSerie(((Inventariable) entity).getNumeroSerie());
			model.setTipoMaterial(TipoMaterial.Inventariable);
			model.setBonificacion(0);
		} else if (entity.getTipoMaterial() == TipoMaterial.noInventariable) {
			model.setBonificacion(((NoInventariable) entity).getBonificacion());
			model.setTipoMaterial(TipoMaterial.noInventariable);
			model.setNoc(null);
			model.setNumeroSerie(null);
		}
	
		model.add(linkTo(methodOn(CategoriaController.class).one(entity.getCategoria().getId())).withRel("categoria"));
		model.add(linkTo(methodOn(DepartamentoController.class).one(entity.getDeptoOferta().getId())).withRel("dptoOferta"));
		if (entity.getDptoAdquisicion() != null) {
			model.add(linkTo(methodOn(DepartamentoController.class).one(entity.getDptoAdquisicion().getId())).withRel("dptoAdquisicion"));
		}
		
		return model;
	}
	
	
	
	
	public Material toEntity(MaterialPostModel model) {
		Material material = new Material();
		
		
		switch (model.getTipoMaterial()) {
		case Inventariable:
			Inventariable inv = new Inventariable();
			inv.setNoc(model.getNoc());
			inv.setNumeroSerie(model.getNumeroSerie());
			material = inv;
			break;
		case noInventariable: 
			NoInventariable noinv = new NoInventariable();
			noinv.setBonificacion(model.getBonificacion());
			material = noinv;
			break;				
		
		}
		
		material.setNombre(model.getNombre());
		material.setDescripcion(model.getDescripcion());
		material.setCantidad(model.getCantidad());
		material.setDimensiones(model.getDimensiones());
		material.setFechaAdquisicion(model.getFechaAdquisicion());
		material.setFechaOferta(model.getFechaOferta());
		material.setCantidad(model.getCantidad());
		material.setMilis(model.getMilis());
		material.setEstado(model.getEstado());
		
		//las entidades con las que esta relacionada
		material.setDeptoOferta(model.getDptoOferta());
		material.setCategoria(model.getCategoria());
		material.setDptoAdquisicion(model.getDptoAdquisicion());
		
		return material;
	}
}
