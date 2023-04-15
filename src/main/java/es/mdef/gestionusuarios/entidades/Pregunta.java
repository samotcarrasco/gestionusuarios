package es.mdef.gestionusuarios.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="PREGUNTAS")
public class Pregunta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String enunciado;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuarioId")
	private Usuario usuario;	

//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="familiaID")
//	private Familia familia;


	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
//	public Familia getFamilia() {
//		return familia;
//	}
//	public void setFamilia(Familia familia) {
//		this.familia = familia;
//	}
	
	
	@Override
	public String toString() {
		return "Pregunta [id=" + id + ", enunciado=" + enunciado + ", usuario=" + usuario+ "]";
	}
	
	
	
}