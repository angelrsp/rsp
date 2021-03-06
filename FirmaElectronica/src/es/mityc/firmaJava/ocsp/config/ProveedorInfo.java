/**
 * Copyright 2013 Ministerio de Industria, Energía y Turismo
 *
 * Este fichero es parte de "Componentes de Firma XAdES 1.1.7".
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 o –en cuanto sean aprobadas por la Comisión Europea– versiones posteriores de la EUPL (la Licencia);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 *
 * Puede obtenerse una copia de la Licencia en:
 *
 * http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones que establece la Licencia.
 */
package es.mityc.firmaJava.ocsp.config;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.asn1.ASN1OctetString;

/**
 * Contiene datos sobre servidores OCSPs
 * 
 */

public final class ProveedorInfo implements Cloneable, ConstantesProveedores 
{
    private static Log logger = LogFactory.getLog(ProveedorInfo.class);
	private String nombre = null;
	private String descripcion = null;
	
	// Si lo de clonar no se puede siempre se puede usar un
	// HashTable q tmb va sincronizado (aunq va mejor esto otro)
	//private ConcurrentHashMap<String, String> caHash = null;
	private Hashtable<String, String> caHash = null;
	private Vector<ServidorOcsp> servidores = null;
	
	
	protected Object clone() throws CloneNotSupportedException {
		ProveedorInfo copy = (ProveedorInfo) super.clone();
		copy.nombre = nombre;
		copy.descripcion = descripcion;
		copy.caHash = new Hashtable<String, String> ();
		copy.caHash = getCAList();
		copy.servidores = new Vector<ServidorOcsp>();
		copy.servidores = getServidores();
		return copy;
	}
	
	/**
	 * Constructor de la clase. 
	 */
	public ProveedorInfo() {
		nombre = EMPTY_STRING;
		descripcion = EMPTY_STRING;
		caHash = new Hashtable<String, String>();
		servidores = new Vector<ServidorOcsp>();

	}
	/**
	 * Obtiene el nombre del proveedor
	 */
	public String getNombre() {
		return nombre;
	}
	
	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la descripcion del proveedor.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	protected void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Obtiene la lista de servidores OCSP con los que se puede validar el certificado indicado
	 */
	public Vector<ServidorOcsp> getServidores() {

		Vector<ServidorOcsp> copy = null;
		int total2 = servidores.size();
		
		try {
			copy = new Vector<ServidorOcsp>();
			for (int i=0;i<total2;i++)
				copy.add((ServidorOcsp)servidores.get(i).clone());
		} catch (CloneNotSupportedException e) {		
			logger.error(e.getMessage());
		}
		return copy;
	}
	
	/**
	 * Obtiene el primer Servidor ocsp de la lista de servidores con los que se puede validar el certificado indicado
	 */
	public ServidorOcsp getServidor() {

		Iterator<ServidorOcsp> lista =  getServidores().iterator();
		ServidorOcsp servidorOcsp = null;
		if (lista.hasNext()) {
			servidorOcsp = lista.next();
		}
		return servidorOcsp;
	}

	protected Hashtable<String, String> getCAList() {
		return (Hashtable<String, String>)caHash.clone();
	}

	/**
	 * Indica el certificado indicado puede ser validado por este OCSP.
	 * @param cert
	 * @return true si puede ser validado, false en otro caso.
	 */
	public boolean puedeValidar(Object certObj) {
		X509Certificate cert = null;
		
		if (certObj == null) {
			logger.error (CERTIFICATE_TYPE_EXCEPTION);
			return false;
		}
		try {
			if (certObj instanceof String) {
				cert = UtilidadesX509.getCertificate((String)certObj);					
			} else if (certObj instanceof byte[]) {
				cert = UtilidadesX509.getCertificate((byte[])certObj);
			} else if (certObj instanceof X509Certificate) {
				cert = (X509Certificate)certObj;
			} else {
				logger.error (CERTIFICATE_TYPE_EXCEPTION);
				return false;
			}
		} catch (CertificateException e) {
			logger.error (e.getMessage());
			return false;
		}	
		
		String nameHash = EMPTY_STRING;
		String pkHash = EMPTY_STRING;
		try {

			ASN1OctetString issuerNameHash = UtilidadesX509.getIssuerNameHash(cert);
			ASN1OctetString issuerKeyHash = UtilidadesX509.getIssuerKeyHash(cert);

			nameHash = issuerNameHash.toString().replace(ALMOHADILLA, EMPTY_STRING);
			pkHash = issuerKeyHash.toString().replace(ALMOHADILLA, EMPTY_STRING);

			return puedeValidar (nameHash,pkHash);
		} catch (IOException ex) {
			logger.error(ex.getMessage());
			return false;
		}
	}
	
	/**
	 * (Version desarrollo. Def sera protected..) Indica el certificado indicado puede ser validado por este OCSP.
	 * @param nameHash
	 * @param pkHash
	 * @return true si puede ser validado, false en otro caso.
	 */
	protected boolean puedeValidar(String nameHash, String pkHash) {
		if (caHash.containsKey(nameHash))
			return (((String)caHash.get(nameHash)).equals(pkHash));
		return false;
	}
	
	protected void addServidor (ServidorOcsp server) {
		this.servidores.add(server);
	}
	
	protected void addCA(String nameHash, String pkHash) {
		if (UtilidadesX509.isEmpty(nameHash)) return;
		
		if (false == caHash.containsKey(nameHash))
			caHash.put(nameHash, pkHash);
	}
}
