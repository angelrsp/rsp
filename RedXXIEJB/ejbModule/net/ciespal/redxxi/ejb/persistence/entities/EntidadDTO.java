package net.ciespal.redxxi.ejb.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ate_entidad database table.
 * 
 */
@Entity
@Table(name="ate_entidad")
@NamedQuery(name="EntidadDTO.findAll", query="SELECT e FROM EntidadDTO e")
public class EntidadDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ATE_ENTIDAD_ENTCODIGO_GENERATOR", sequenceName="ATE_ENTIDAD_ENT_CODIGO_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ATE_ENTIDAD_ENTCODIGO_GENERATOR")
	@Column(name="ent_codigo")
	private Integer entCodigo;

	//bi-directional many-to-one association to CarreraDTO
	@ManyToOne
	@JoinColumn(name="ent_carrera")
	private CarreraDTO ateCarrera;

	//bi-directional many-to-one association to DoctorDTO
	@ManyToOne
	@JoinColumn(name="ent_doctor")
	private DoctorDTO ateDoctor;

	//bi-directional many-to-one association to OrganizacionDTO
	@ManyToOne
	@JoinColumn(name="ent_organizacion")
	private OrganizacionDTO ateOrganizacion;

	//bi-directional many-to-one association to ProyectoInvestigacionDTO
	@ManyToOne
	@JoinColumn(name="ent_proyecto_intestigacion")
	private ProyectoInvestigacionDTO ateProyectoInvestigacion;

	//bi-directional many-to-one association to PublicacionDTO
	@ManyToOne
	@JoinColumn(name="ent_publicacion")
	private PublicacionDTO atePublicacion;

	//bi-directional many-to-one association to ContactoDTO
	@ManyToOne
	@JoinColumn(name="ent_contacto")
	private ContactoDTO ateContacto;

	//bi-directional many-to-one association to EventoDTO
	@ManyToOne
	@JoinColumn(name="ent_evento")
	private EventoDTO ateEvento;

	//bi-directional many-to-one association to NoticiaDTO
	@ManyToOne
	@JoinColumn(name="ent_noticias")
	private NoticiaDTO ateNoticia;

	//bi-directional many-to-one association to ObraDTO
	@ManyToOne
	@JoinColumn(name="ent_obra")
	private ObraDTO ateObra;

	public EntidadDTO() {
	}

	public Integer getEntCodigo() {
		return this.entCodigo;
	}

	public void setEntCodigo(Integer entCodigo) {
		this.entCodigo = entCodigo;
	}

	public CarreraDTO getAteCarrera() {
		return this.ateCarrera;
	}

	public void setAteCarrera(CarreraDTO ateCarrera) {
		this.ateCarrera = ateCarrera;
	}

	public DoctorDTO getAteDoctor() {
		return this.ateDoctor;
	}

	public void setAteDoctor(DoctorDTO ateDoctor) {
		this.ateDoctor = ateDoctor;
	}

	public OrganizacionDTO getAteOrganizacion() {
		return this.ateOrganizacion;
	}

	public void setAteOrganizacion(OrganizacionDTO ateOrganizacion) {
		this.ateOrganizacion = ateOrganizacion;
	}

	public ProyectoInvestigacionDTO getAteProyectoInvestigacion() {
		return this.ateProyectoInvestigacion;
	}

	public void setAteProyectoInvestigacion(ProyectoInvestigacionDTO ateProyectoInvestigacion) {
		this.ateProyectoInvestigacion = ateProyectoInvestigacion;
	}

	public PublicacionDTO getAtePublicacion() {
		return this.atePublicacion;
	}

	public void setAtePublicacion(PublicacionDTO atePublicacion) {
		this.atePublicacion = atePublicacion;
	}

	public ContactoDTO getAteContacto() {
		return this.ateContacto;
	}

	public void setAteContacto(ContactoDTO ateContacto) {
		this.ateContacto = ateContacto;
	}

	public EventoDTO getAteEvento() {
		return this.ateEvento;
	}

	public void setAteEvento(EventoDTO ateEvento) {
		this.ateEvento = ateEvento;
	}

	public NoticiaDTO getAteNoticia() {
		return this.ateNoticia;
	}

	public void setAteNoticia(NoticiaDTO ateNoticia) {
		this.ateNoticia = ateNoticia;
	}

	public ObraDTO getAteObra() {
		return this.ateObra;
	}

	public void setAteObra(ObraDTO ateObra) {
		this.ateObra = ateObra;
	}

}