package ec.edu.uce.besg.ejb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cue_control database table.
 * 
 */
@Entity
@Table(name="cue_control")
@NamedQuery(name="ControlDTO.findAll", query="SELECT c FROM ControlDTO c")
public class ControlDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CUE_CONTROL_CONCODIGO_GENERATOR", sequenceName="CUE_CONTROL_CON_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUE_CONTROL_CONCODIGO_GENERATOR")
	@Column(name="con_codigo")
	private Integer conCodigo;

	@Column(name="con_descripcion")
	private String conDescripcion;

	//bi-directional many-to-one association to RespuestaDTO
	@OneToMany(mappedBy="cueControl")
	private List<PreguntaDTO> cuePreguntas;

	public ControlDTO() {
	}

	public Integer getConCodigo() {
		return this.conCodigo;
	}

	public void setConCodigo(Integer conCodigo) {
		this.conCodigo = conCodigo;
	}

	public String getConDescripcion() {
		return this.conDescripcion;
	}

	public void setConDescripcion(String conDescripcion) {
		this.conDescripcion = conDescripcion;
	}

	public List<PreguntaDTO> getCuePreguntas() {
		return cuePreguntas;
	}

	public void setCuePreguntas(List<PreguntaDTO> cuePreguntas) {
		this.cuePreguntas = cuePreguntas;
	}


}