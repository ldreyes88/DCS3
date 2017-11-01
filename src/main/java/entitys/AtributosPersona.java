package entitys;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the atributos_personas database table.
 * 
 */
@Entity
@Table(name="atributos_personas")
@NamedQuery(name="AtributosPersona.findAll", query="SELECT a FROM AtributosPersona a")
public class AtributosPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ATRIBUTO_PERSONA")
	private Long idAtributoPersona;

	@Column(name="DESCRIPCION_ATRIBUTO_PERSONA")
	private String descripcionAtributoPersona;

	@Column(name="NOMBRE_ATRIBUTO_PERSONA")
	private String nombreAtributoPersona;

	public AtributosPersona() {
	}

	public Long getIdAtributoPersona() {
		return this.idAtributoPersona;
	}

	public void setIdAtributoPersona(Long idAtributoPersona) {
		this.idAtributoPersona = idAtributoPersona;
	}

	public String getDescripcionAtributoPersona() {
		return this.descripcionAtributoPersona;
	}

	public void setDescripcionAtributoPersona(String descripcionAtributoPersona) {
		this.descripcionAtributoPersona = descripcionAtributoPersona;
	}

	public String getNombreAtributoPersona() {
		return this.nombreAtributoPersona;
	}

	public void setNombreAtributoPersona(String nombreAtributoPersona) {
		this.nombreAtributoPersona = nombreAtributoPersona;
	}

}