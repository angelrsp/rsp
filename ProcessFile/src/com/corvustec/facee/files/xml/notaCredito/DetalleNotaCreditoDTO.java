package com.corvustec.facee.files.xml.notaCredito;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.corvustec.facee.files.xml.factura.ImpuestosDTO;




@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "codigoInterno", "codigoAdicional","descripcion",
		"cantidad", "precioUnitario", "descuento",
		"precioTotalSinImpuesto", "impuestos"})


public class DetalleNotaCreditoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(required = true)
	private String codigoInterno;
	
	private String codigoAdicional;
	
	@XmlElement(required = true)
	private String descripcion;
	
	@XmlElement(required = true)
	private BigDecimal cantidad;
	
	@XmlElement(required = true)
	private BigDecimal precioUnitario;
	
	@XmlElement(required = true)
	private BigDecimal descuento;
	
	@XmlElement(required = true)
	private BigDecimal precioTotalSinImpuesto;
	
//	private List<DetalleAdicionalDTO> detallesAdicionales;
	
	@XmlElement(required = true)
	private ImpuestosDTO impuestos;
	
	public DetalleNotaCreditoDTO () {}

	/**
	 * @return the codigoInterno
	 */
	public String getCodigoInterno() {
		return codigoInterno;
	}


	/**
	 * @param codigoPrincipal the codigoInterno to set
	 */
	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}


	/**
	 * @return the codigoInterno
	 */
	public String getCodigoAdicional() {
		return codigoAdicional;
	}


	/**
	 * @param codigoAdicional the codigoAdicional to set
	 */
	public void setCodigoAdicinal(String codigoAdicional) {
		this.codigoAdicional = codigoAdicional;
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return the cantidad
	 */
	public BigDecimal getCantidad() {
		return cantidad;
	}


	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}


	/**
	 * @return the precioUnitario
	 */
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}


	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	/**
	 * @return the descuento
	 */
	public BigDecimal getDescuento() {
		return descuento;
	}


	/**
	 * @param descuento the descuento to set
	 */
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}


	/**
	 * @return the precioTotalSinImpuesto
	 */
	public BigDecimal getPrecioTotalSinImpuesto() {
		return precioTotalSinImpuesto;
	}


	/**
	 * @param precioTotalSinImpuesto the precioTotalSinImpuesto to set
	 */
	public void setPrecioTotalSinImpuesto(BigDecimal precioTotalSinImpuesto) {
		this.precioTotalSinImpuesto = precioTotalSinImpuesto;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */



	/**
	 * @return the impuestos
	 */
	public ImpuestosDTO getImpuestos() {
		
		if (impuestos == null) {
			impuestos = new ImpuestosDTO();
		}
		return impuestos;
	}


	@Override
	public String toString() {
		return "DetalleNotaCreditoDTO [codigoInterno=" + codigoInterno
				+ ", codigoAdicional=" + codigoAdicional + ", descripcion="
				+ descripcion + ", cantidad=" + cantidad + ", precioUnitario="
				+ precioUnitario + ", descuento=" + descuento
				+ ", precioTotalSinImpuesto=" + precioTotalSinImpuesto
				+ ", impuestos=" + impuestos + "]";
	}

	/**
	 * @param impuestos the impuestos to set
	 */
	public void setImpuestos(ImpuestosDTO impuestos) {
		this.impuestos = impuestos;
	}

//	/**
//	 * @return the detallesAdicionales
//	 */
//	public List<DetalleAdicionalDTO> getDetallesAdicionales() {
//		return detallesAdicionales;
//	}
//
//
//	/**
//	 * @param detallesAdicionales the detallesAdicionales to set
//	 */
//	public void setDetallesAdicionales(List<DetalleAdicionalDTO> detallesAdicionales) {
//		this.detallesAdicionales = detallesAdicionales;
//	}
	
	
	
}
	
	


