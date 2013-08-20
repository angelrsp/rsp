package ec.edu.uce.erpmunicipal.contabilidad.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import ec.edu.uce.erpmunicipal.contabilidad.bsl.JournalService;
import ec.edu.uce.erpmunicipal.contabilidad.orm.ConClase;
import ec.edu.uce.erpmunicipal.contabilidad.orm.ConCuenta;
import ec.edu.uce.erpmunicipal.contabilidad.orm.ConMovimiento;
import ec.edu.uce.erpmunicipal.contabilidad.orm.ConMovimientoDetalle;
import ec.edu.uce.erpmunicipal.contabilidad.orm.ConTipoMovimiento;
import ec.edu.uce.erpmunicipal.sistema.bsl.CrudService;
import ec.edu.uce.erpmunicipal.util.orm.SessionObject;

@ManagedBean(name = "journalPage")
@ViewScoped
public class JournalPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB(name = "crudService/local")
	private CrudService crudService;
	@EJB(name = "journalService/local")
	private JournalService journalService;
	
	@SuppressWarnings("unused")
	private List<ConTipoMovimiento> typeMove;
	@SuppressWarnings("unused")
	private List<ConClase> clas;

	private List<ConCuenta> cuentas;

	private List<ConMovimientoDetalle> detalles;

	private ConMovimientoDetalle detalle;
	
	private ConMovimiento movimiento;

	private String search;
	private String searchCode;
	private String descripcionCuenta;

	private String debe;
	private String haber;
	private Double cuadre;
	
	private int claseCode;
	private int tipoMovimientoCode;
	
	private Date fecha;
	
	public JournalPage() {
		clas = new ArrayList<ConClase>();
		typeMove = new ArrayList<ConTipoMovimiento>();
		cuentas = new ArrayList<ConCuenta>();
		cuadre = 0.0;
		detalles = new ArrayList<ConMovimientoDetalle>();
		detalle = new ConMovimientoDetalle();
		movimiento=new ConMovimiento();
		Calendar cal=Calendar.getInstance();
		fecha=cal.getTime();
	}

	/**
	 * @return the typeMove
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ConTipoMovimiento> getTypeMove() {
		return typeMove = (List<ConTipoMovimiento>) (List) crudService
				.find("ConTipoMovimiento");
	}

	/**
	 * @param typeMove
	 *            the typeMove to set
	 */
	public void setTypeMove(List<ConTipoMovimiento> typeMove) {
		this.typeMove = typeMove;
	}

	/**
	 * @return the clas
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ConClase> getClas() {
		return clas = (List<ConClase>) (List) crudService.find("ConClase");
	}

	/**
	 * @param clas
	 *            the clas to set
	 */
	public void setClas(List<ConClase> clas) {
		this.clas = clas;
	}

	public List<ConCuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<ConCuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSearchCode() {
		return searchCode;
	}

	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}

	public String getDescripcionCuenta() {
		return descripcionCuenta;
	}

	public void setDescripcionCuenta(String descripcionCuenta) {
		this.descripcionCuenta = descripcionCuenta;
	}

	/**
	 * @return the debe
	 */
	public String getDebe() {
		return debe;
	}

	/**
	 * @param debe
	 *            the debe to set
	 */
	public void setDebe(String debe) {
		this.debe = debe;
	}

	/**
	 * @return the haber
	 */
	public String getHaber() {
		return haber;
	}

	/**
	 * @param haber
	 *            the haber to set
	 */
	public void setHaber(String haber) {
		this.haber = haber;
	}

	/**
	 * @return the detalle
	 */
	public List<ConMovimientoDetalle> getDetalles() {
		return detalles;
	}

	/**
	 * @param detalle
	 *            the detalle to set
	 */
	public void setDetalle(List<ConMovimientoDetalle> detalles) {
		this.detalles = detalles;
	}

	/**
	 * @return the detalle
	 */
	public ConMovimientoDetalle getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle
	 *            the detalle to set
	 */
	public void setDetalle(ConMovimientoDetalle detalle) {
		this.detalle = detalle;
	}

	public ConMovimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(ConMovimiento movimiento) {
		this.movimiento = movimiento;
	}

	public int getClaseCode() {
		return claseCode;
	}

	public void setClaseCode(int claseCode) {
		this.claseCode = claseCode;
	}

	public int getTipoMovimientoCode() {
		return tipoMovimientoCode;
	}

	public void setTipoMovimientoCode(int tipoMovimientoCode) {
		this.tipoMovimientoCode = tipoMovimientoCode;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void searchCuenta() {
		this.cuentas = journalService.getAccoutingService().dynamicSearch(search);
	}

	public void searchCuentaCode() {
		this.descripcionCuenta = ((ConCuenta) journalService.getAccoutingService()
				.search(searchCode)).getCueDescripcion();
	}

	public void onRowSelect(SelectEvent event) {
		ConCuenta obj = (ConCuenta) event.getObject();
		this.searchCode = obj.getCueNumero();
		this.descripcionCuenta = obj.getCueDescripcion();
		this.cuentas = new ArrayList<ConCuenta>();
		this.search = "";
	}

	public void addClick() {
		if (debe.equals("") && haber.equals("")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Ingrese valor de debe"));

		} else {
			ConMovimientoDetalle det = new ConMovimientoDetalle();
			if (!debe.equals("")) {
				Double deb = Double.valueOf(debe.replace(',', '.'));
				cuadre = cuadre + deb;
				haber = cuadre.toString();
				debe = "";
				det.setConCuenta(new ConCuenta(null, null, descripcionCuenta,
						null, searchCode, null, null, null, null,null));
				det.setMdeDebe(BigDecimal.valueOf(deb));
				detalles.add(det);
				searchCode = "";
				descripcionCuenta = "";
			} else if (!haber.equals("")) {
				Double hab = Double.valueOf(haber.replace(',', '.'));
				cuadre = cuadre - hab;
				
				haber = cuadre.toString();
				
					
				debe = "";
				det.setConCuenta(new ConCuenta(null, null, descripcionCuenta,
						null, searchCode, null, null, null, null,null));
				det.setMdeHaber(BigDecimal.valueOf(hab));
				detalles.add(det);
				searchCode = "";
				descripcionCuenta = "";
			}
		}
	}
	
	public void onRowDeleting(ConMovimientoDetalle de) {
		detalles.remove(de);
	}

	public void create()
	{
		SessionObject sessionObject=new SessionObject();
		if(detalles.size()<=0)
		{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Ingrese detalles"));
			return;
		}
		journalService.create(sessionObject, claseCode, tipoMovimientoCode, movimiento, detalles);
	}
	
	public void clear()
	{
		detalles=new ArrayList<ConMovimientoDetalle>();
	}
}
