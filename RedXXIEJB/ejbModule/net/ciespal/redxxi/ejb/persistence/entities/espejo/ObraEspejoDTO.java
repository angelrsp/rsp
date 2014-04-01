package net.ciespal.redxxi.ejb.persistence.entities.espejo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the esp_obra database table.
 * 
 */
@Entity
@Table(name="esp_obra")
@NamedQuery(name="ObraEspejoDTO.findAll", query="SELECT o FROM ObraDTO o")
public class ObraEspejoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESP_OBRA_OBRCODIGO_GENERATOR", sequenceName="ESP_OBRA_OBR_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESP_OBRA_OBRCODIGO_GENERATOR")
	@Column(name="obr_codigo")
	private Integer obrCodigo;

	@Column(name="obr_archivo")
	private byte[] obrArchivo;

	@Column(name="obr_texto")
	private String obrTexto;

	@Column(name="obr_titulo")
	private String obrTitulo;

	@Column(name="obr_tipo")
	private Integer obrTipo;
	
	//bi-directional many-to-one association to EntidadDTO
	@ManyToOne
	@JoinColumn(name="obr_entidad")
	private EntidadEspejoDTO espEntidad;

	public ObraEspejoDTO() {
	}

	public Integer getObrCodigo() {
		return this.obrCodigo;
	}

	public void setObrCodigo(Integer obrCodigo) {
		this.obrCodigo = obrCodigo;
	}

	public byte[] getObrArchivo() {
		return this.obrArchivo;
	}

	public void setObrArchivo(byte[] obrArchivo) {
		this.obrArchivo = obrArchivo;
	}

	public String getObrTexto() {
		return this.obrTexto;
	}

	public void setObrTexto(String obrTexto) {
		this.obrTexto = obrTexto;
	}

	public String getObrTitulo() {
		return this.obrTitulo;
	}

	public void setObrTitulo(String obrTitulo) {
		this.obrTitulo = obrTitulo;
	}

	public Integer getObrTipo() {
		return obrTipo;
	}

	public void setObrTipo(Integer obrTipo) {
		this.obrTipo = obrTipo;
	}

	public EntidadEspejoDTO getEspEntidad() {
		return this.espEntidad;
	}

	public void setEspEntidad(EntidadEspejoDTO espEntidad) {
		this.espEntidad = espEntidad;
	}

}