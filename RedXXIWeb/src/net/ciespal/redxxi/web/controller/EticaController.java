package net.ciespal.redxxi.web.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.ciespal.redxxi.ejb.negocio.EspejoService;
import net.ciespal.redxxi.ejb.persistence.entities.espejo.EticaDTO;
import net.ciespal.redxxi.web.commons.util.JsfUtil;
import net.ciespal.redxxi.web.datamanager.EticaDataManager;

import com.corvustec.commons.util.CorvustecException;

@ViewScoped
@ManagedBean(name = "eticaController")
public class EticaController extends SelectItemController{

	@EJB
	private EspejoService espejoService;
	
	@ManagedProperty(value="#{eticaDataManager}")
	private EticaDataManager eticaDataManager;

	public EticaController() {
	
	}

	public EticaDataManager getEticaDataManager() {
		return eticaDataManager;
	}

	public void setEticaDataManager(EticaDataManager eticaDataManager) {
		this.eticaDataManager = eticaDataManager;
	}
	
	@PostConstruct
	private void init()
	{

	}

	public void obtenerProvinciaChange() {
		try {
			getCatalogoProvincia();
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}		
	}
	
	public void obtenerCiudadChange() {
		try {
			getCatalogoCiudad();
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}		
	}
	
	public void save()
	{
		try {
			eticaDataManager.getEticaDTO().setEtiPais(Integer.valueOf(getPais().toString()));
			eticaDataManager.getEticaDTO().setEtiProvincia(Integer.valueOf(getProvincia().toString()));
			eticaDataManager.getEticaDTO().setEtiCiudad(Integer.valueOf(getCiudad().toString()));
			espejoService.createOrUpdateEtica(eticaDataManager.getEticaDTO());
			read();
			cancel();
			JsfUtil.addInfoMessage("Guardado Exitosamente");
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
		
	}

	public void cancel()
	{
		eticaDataManager.setEticaDTO(new EticaDTO());
	}
	
	public void edit(EticaDTO etica)
	{
		eticaDataManager.setEticaDTO(etica);
	}

	public void delete(EticaDTO etica)
	{
		
	}

	public void ciudadChange()
	{
		read();
	}
	
	private void read()
	{
		try {
			eticaDataManager.setEticaList(espejoService.readEtica(getCiudad()));
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}

	
}