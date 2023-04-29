package es.mdef.gestionusuarios.entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name="DEPARTAMENTOS")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})

public class Departamento {
	
	public static enum TipoAcuartelamiento {
		ACING
	}
	public enum TipoEmpleo { 
		Soldado, 
		Cabo, 
		Suboficial, 
		Oficial
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "dptoOferta")
	private List<Material> materialesOfertados;	
	
	@OneToMany(mappedBy = "dptoAdquisicion")
	private List<Material> materialesAdquiridos;
	
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

	public void setId(Long id) {
		this.id = id;
	}

	public List<Material> getMaterialesOfertados() {
		return materialesOfertados;
	}

	public void setMaterialesOfertados(List<Material> materialesOfertados) {
		this.materialesOfertados = materialesOfertados;
	}

	public List<Material> getMaterialesAdquiridos() {
		return materialesAdquiridos;
	}

	public void setMaterialesAdquiridos(List<Material> materialesAdquiridos) {
		this.materialesAdquiridos = materialesAdquiridos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public TipoAcuartelamiento getAcuartelamiento() {
		return acuartelamiento;
	}

	public void setAcuartelamiento(TipoAcuartelamiento acuartelamiento) {
		this.acuartelamiento = acuartelamiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	public TipoEmpleo getResponsableEmpleo() {
		return responsableEmpleo;
	}

	public void setResponsableEmpleo(TipoEmpleo responsableEmpleo) {
		this.responsableEmpleo = responsableEmpleo;
	}

	public String getResponsableNombre() {
		return responsableNombre;
	}

	public void setResponsableNombre(String responsableNombre) {
		this.responsableNombre = responsableNombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public void aumentarCredito(int cantidad) {
	    credito += cantidad;
	}
	

	@Override
	public String toString() {
		return "Departamento [id=" + getId() + ", nombre" + getAbreviatura() + "]";
	}
	
}