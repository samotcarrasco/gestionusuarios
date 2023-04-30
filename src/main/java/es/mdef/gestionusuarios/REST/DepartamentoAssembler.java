package es.mdef.gestionusuarios.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.gestionusuarios.entidades.Departamento;



@Component
public class DepartamentoAssembler implements RepresentationModelAssembler<Departamento, DepartamentoModel>{

	
	@Override
	public DepartamentoModel toModel(Departamento entity) {
		DepartamentoModel model = new DepartamentoModel();
		model.setId(entity.getId());
		model.setAbreviatura(entity.getAbreviatura());
		model.setNombre(entity.getNombre());
		model.setAcuartelamiento(entity.getAcuartelamiento());
		model.setCredito(entity.getCredito());
		model.setEmail(entity.getEmail());
		model.setTelefono(entity.getTelefono());
		model.setResponsableNombre(entity.getResponsableNombre());
		model.setResponsableEmpleo(entity.getResponsableEmpleo());
		
				
		model.add(linkTo(methodOn(DepartamentoController.class).one(entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(DepartamentoController.class).materialesOfertados(entity.getId())).withRel("materialesOfertados"));
		model.add(linkTo(methodOn(DepartamentoController.class).materialesAdquiridos(entity.getId())).withRel("materialesAdquiridos"));
		return model;
	}
	
	
	
	
	public Departamento toEntity(DepartamentoPostModel model) {
		Departamento departamento = new Departamento();
		departamento.setAbreviatura(model.getAbreviatura());
		departamento.setAcuartelamiento(model.getAcuartelamiento());
		departamento.setNombre(model.getNombre());
		//inicialemnte se asignan 1000 milis a los departamentos
		departamento.setCredito(1000);
		departamento.setEmail(model.getEmail());
		departamento.setTelefono(model.getTelefono());
		departamento.setResponsableNombre(model.getResponsableNombre());
		departamento.setResponsableEmpleo(model.getResponsableEmpleo());
		
		//las entidades ocn las que esta relacionada
		return departamento;
	}
}
