package net.ciespal.redxxi.web.controller.home;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import net.ciespal.redxxi.ejb.negocio.ArgosService;
import net.ciespal.redxxi.ejb.negocio.AteneaService;
import net.ciespal.redxxi.ejb.negocio.EspejoService;
import net.ciespal.redxxi.ejb.persistence.entities.AteneaDTO;
import net.ciespal.redxxi.ejb.persistence.entities.PaisDTO;
import net.ciespal.redxxi.web.commons.util.JsfUtil;
import net.ciespal.redxxi.web.datamanager.home.ReportPublicDataManager;

import com.corvustec.commons.util.CorvustecException;

@ViewScoped
@ManagedBean(name = "reportPublicController")
public class ReportPublicController implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@EJB
	private AteneaService ateneaService;

	@EJB
	private ArgosService argosService;

	@EJB
	private EspejoService espejoService;

	
	@ManagedProperty(value="#{reportPublicDataManager}")
	private ReportPublicDataManager reportPublicDataManager;
	
	public ReportPublicController() {
		
	}

	@PostConstruct
	private void init()
	{
		readAtenea();
		readPais();
		readAteneaCount();
		readArgosCount();
		readEspejoCount();
	}
	
	public ReportPublicDataManager getReportPublicDataManager() {
		return reportPublicDataManager;
	}

	public void setReportPublicDataManager(
			ReportPublicDataManager reportPublicDataManager) {
		this.reportPublicDataManager = reportPublicDataManager;
	}
	
	private void readAtenea()
	{
		try {
			reportPublicDataManager.setAteneaList(ateneaService.readAtenea(null));
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}

	private void readPais()
	{
		try {
			reportPublicDataManager.setPaisList(ateneaService.readPais(null));
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}

	public void selectAtenea(AteneaDTO atenea)
	{
		try {
			reportPublicDataManager.setPaisList(ateneaService.readPais(atenea.getTipo()));
			reportPublicDataManager.setAtenea(atenea);
			//reportPublicDataManager.setVisor(ateneaService.visor(atenea));
			//JsfUtil.redirect("/"+JsfUtil.getExternalContext().getContextName()+"/public/home/ateneaVisor.xhtml");
			
			if(atenea.getTipo()==2)
				reportPublicDataManager.setTipoConsulta("Universidades");
			else if(atenea.getTipo()==6)
				reportPublicDataManager.setTipoConsulta("Carreras de Pregrado");
			else if(atenea.getTipo()==7)
				reportPublicDataManager.setTipoConsulta("Carreras de Posgrado");
			else if(atenea.getTipo()==103)
				reportPublicDataManager.setTipoConsulta("Organizaciones Científicas");
			else if(atenea.getTipo()==104)
				reportPublicDataManager.setTipoConsulta("Doctores en Ciencias");
			else if(atenea.getTipo()==105)
				reportPublicDataManager.setTipoConsulta("Articulos, Monografías, Tesis, Libros");
			
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}
	
	public void selectPais(PaisDTO pais)
	{
		AteneaDTO atenea;
		try {
			reportPublicDataManager.setPais(pais);
			reportPublicDataManager.setAteneaList(ateneaService.readAtenea(pais.getCodigo()));
			//reportPublicDataManager.setVisor(ateneaService.infoPais(pais));
			if(reportPublicDataManager.getAtenea().getTipo()!=0)
				pais.setTipo(reportPublicDataManager.getAtenea().getTipo());
			else
				pais.setTipo(2);

			atenea=new AteneaDTO();
			atenea.setPais(pais.getCodigo());
			atenea.setTipo(pais.getTipo());
			
			reportPublicDataManager.setAteneaVisorList(ateneaService.visorList(atenea));
			reportPublicDataManager.getAtenea().setTipo(0);
			JsfUtil.redirect("/"+JsfUtil.getExternalContext().getContextName()+"/public/home/ateneaVisor.xhtml");
		} catch (IOException e) {
			JsfUtil.addErrorMessage(e.toString());
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}

	
	private void readAteneaCount()
	{
		try {
			reportPublicDataManager.setAteneaCount(ateneaService.rearAteneaCount());
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}
	
	private void readArgosCount()
	{
		try {
			reportPublicDataManager.setArgosCount(argosService.countArgos());
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}

	private void readEspejoCount()
	{
		try {
			reportPublicDataManager.setEspejoCount(espejoService.readEspejoCount());
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}

}
