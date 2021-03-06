package net.ciespal.redxxi.ejb.persistence.entities.security;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seg_usuario database table.
 * 
 */
@Entity
@Table(name="seg_usuario")
@NamedQuery(name="UsuarioDTO.findAll", query="SELECT u FROM UsuarioDTO u")
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEG_USUARIO_USUCODIGO_GENERATOR", sequenceName="SEG_USUARIO_USU_CODIGO_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEG_USUARIO_USUCODIGO_GENERATOR")
	@Column(name="usu_codigo")
	private Integer usuCodigo;

	@Column(name="usu_apellidos")
	private String usuApellidos;

	@Column(name="usu_clave")
	private String usuClave;

	@Column(name="usu_login")
	private String usuLogin;

	@Column(name="usu_nombres")
	private String usuNombres;

	@Column(name="usu_tipo")
	private Integer usuTipo;

	@Column(name="usu_pais")
	private Integer usuPais;

	@Column(name="usu_provincia")
	private Integer usuProvincia;

	@Column(name="usu_ciudad")
	private Integer usuCiudad;
	
	//bi-directional many-to-one association to EmpresaDTO
	@ManyToOne
	@JoinColumn(name="usu_empresa")
	private EmpresaDTO segEmpresa;

	//bi-directional many-to-one association to UsuarioPerfilDTO
	@OneToMany(mappedBy="segUsuario",fetch=FetchType.EAGER)
	private List<UsuarioPerfilDTO> segUsuarioPerfils;

	//bi-directional many-to-one association to ComentarioDTO
	@OneToMany(mappedBy="segUsuario")
	private List<ComentarioDTO> segComentarios;

	
	public UsuarioDTO() {
	}

	public Integer getUsuCodigo() {
		return this.usuCodigo;
	}

	public void setUsuCodigo(Integer usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	public String getUsuApellidos() {
		return this.usuApellidos;
	}

	public void setUsuApellidos(String usuApellidos) {
		this.usuApellidos = usuApellidos;
	}

	public String getUsuClave() {
		return this.usuClave;
	}

	public void setUsuClave(String usuClave) {
		this.usuClave = usuClave;
	}

	public String getUsuLogin() {
		return this.usuLogin;
	}

	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	public String getUsuNombres() {
		return this.usuNombres;
	}

	public void setUsuNombres(String usuNombres) {
		this.usuNombres = usuNombres;
	}

	public Integer getUsuTipo() {
		return usuTipo;
	}

	public Integer getUsuPais() {
		return usuPais;
	}

	public void setUsuPais(Integer usuPais) {
		this.usuPais = usuPais;
	}

	public Integer getUsuProvincia() {
		return usuProvincia;
	}

	public void setUsuProvincia(Integer usuProvincia) {
		this.usuProvincia = usuProvincia;
	}

	public Integer getUsuCiudad() {
		return usuCiudad;
	}

	public void setUsuCiudad(Integer usuCiudad) {
		this.usuCiudad = usuCiudad;
	}

	public void setUsuTipo(Integer usuTipo) {
		this.usuTipo = usuTipo;
	}

	public EmpresaDTO getSegEmpresa() {
		return this.segEmpresa;
	}

	public void setSegEmpresa(EmpresaDTO segEmpresa) {
		this.segEmpresa = segEmpresa;
	}

	public List<UsuarioPerfilDTO> getSegUsuarioPerfils() {
		return this.segUsuarioPerfils;
	}

	public void setSegUsuarioPerfils(List<UsuarioPerfilDTO> segUsuarioPerfils) {
		this.segUsuarioPerfils = segUsuarioPerfils;
	}

	public List<ComentarioDTO> getSegComentarios() {
		return segComentarios;
	}

	public void setSegComentarios(List<ComentarioDTO> segComentarios) {
		this.segComentarios = segComentarios;
	}

	public UsuarioPerfilDTO addSegUsuarioPerfil(UsuarioPerfilDTO segUsuarioPerfil) {
		getSegUsuarioPerfils().add(segUsuarioPerfil);
		segUsuarioPerfil.setSegUsuario(this);

		return segUsuarioPerfil;
	}

	public UsuarioPerfilDTO removeSegUsuarioPerfil(UsuarioPerfilDTO segUsuarioPerfil) {
		getSegUsuarioPerfils().remove(segUsuarioPerfil);
		segUsuarioPerfil.setSegUsuario(null);

		return segUsuarioPerfil;
	}

}