package es.mdef.gestionusuarios.REST;



import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


import es.mdef.gestionusuarios.entidades.Departamento.TipoAcuartelamiento;
import es.mdef.gestionusuarios.entidades.Departamento.TipoEmpleo;



@Relation(itemRelation="departamento")
public class DepartamentoModel extends RepresentationModel<CategoriaModel>{
	
	private Long id;
	
	private String nombre;
	private String abreviatura;
	private TipoAcuartelamiento acuartelamiento;
	private String email;
	private int credito;
	private TipoEmpleo responsableEmpleo;
	private String responsableNombre;
	private String telefono;
	
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public TipoAcuartelamiento getAcuartelamiento() {
		return acuartelamiento;
	}

	public int getCredito() {
		return credito;
	}

	public TipoEmpleo getResponsableEmpleo() {
		return responsableEmpleo;
	}

	public String getResponsableNombre() {
		return responsableNombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public void setAcuartelamiento(TipoAcuartelamiento acuartelamiento) {
		this.acuartelamiento = acuartelamiento;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public void setResponsableEmpleo(TipoEmpleo responsableEmpleo) {
		this.responsableEmpleo = responsableEmpleo;
	}

	public void setResponsableNombre(String responsableNombre) {
		this.responsableNombre = responsableNombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "DPTO MODELO [id=" + getId() + ", nombre" + getNombre() + "]";
	}
	
	
}
