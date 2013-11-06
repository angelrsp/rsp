/**
 * 
 */
package com.corvustec.facee.files.xml.guiaRemision;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.corvustec.facee.files.xml.commons.InfoTributariaDTO;

/**
 * @author wilmerPC
 *
 */
@XmlRootElement(name="guiaRemision")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "infoTributaria","infoGuiaRemision","destinatarios" })
public class GuiaRemisionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlAttribute
	private String version;
	
	@XmlAttribute
	private String id;
	
	@XmlElement(required = true)
	private InfoTributariaDTO infoTributaria;
	
	@XmlElement(required = true)
	private InfoGuiaRemisionDTO infoGuiaRemision;
	
	@XmlElement(required = true)
	private DestinatariosDTO destinatarios;
	
	public InfoGuiaRemisionDTO getInfoGuiaRemision() {
		return infoGuiaRemision;
	}

	public void setInfoGuiaRemision(InfoGuiaRemisionDTO infoGuiaRemision) {
		this.infoGuiaRemision = infoGuiaRemision;
	}

	public DestinatariosDTO getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(DestinatariosDTO destinatarios) {
		this.destinatarios = destinatarios;
	}

	public GuiaRemisionDTO () {}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the infoTributaria
	 */
	public InfoTributariaDTO getInfoTributaria() {
		return infoTributaria;
	}

	/**
	 * @param infoTributaria the infoTributaria to set
	 */
	public void setInfoTributaria(InfoTributariaDTO infoTributaria) {
		this.infoTributaria = infoTributaria;
	}
	

}
