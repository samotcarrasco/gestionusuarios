package es.mdef.gestionusuarios.REST;



import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


import es.mdef.gestionusuarios.entidades.Departamento.TipoAcuartelamiento;
import es.mdef.gestionusuarios.entidades.Departamento.TipoEmpleo;



@Relation(collectionRelation = "departamentos")
public class DepartamentoListaModel extends RepresentationModel<CategoriaModel>{
	
	private Long id;
	private String abreviatura;
	private String email;
	private int credito;
	
	public Long getId() {
		return id;
	}

	public String getAbreviatura() {
		return abreviatura;
	}


	public int getCredito() {
		return credito;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}


	public void setCredito(int credito) {
		this.credito = credito;
	}


	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "DPTO MODELO [id=" + getId() + ", nombre" + getAbreviatura() + "]";
	}
	
	
}
