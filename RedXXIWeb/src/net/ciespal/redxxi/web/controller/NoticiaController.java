package net.ciespal.redxxi.web.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.corvustec.commons.util.CorvustecException;

import net.ciespal.redxxi.ejb.negocio.AteneaService;
import net.ciespal.redxxi.ejb.persistence.entities.NoticiaDTO;
import net.ciespal.redxxi.web.commons.util.JsfUtil;
import net.ciespal.redxxi.web.datamanager.NoticiaDataManager;

@ViewScoped
@ManagedBean(name = "noticiaController")
public class NoticiaController extends SelectItemController{

	@EJB
	private AteneaService ateneaService;
	
	@ManagedProperty(value="#{noticiaDataManager}")
	private NoticiaDataManager noticiaDataManager;

	
	@PostConstruct
	private void init()
	{
		readNoticia();
		readNoticiaPublic();
	}

	public NoticiaDataManager getNoticiaDataManager() {
		return noticiaDataManager;
	}

	public void setNoticiaDataManager(NoticiaDataManager noticiaDataManager) {
		this.noticiaDataManager = noticiaDataManager;
	}
	
	
	public void save()
	{
		try {
			noticiaDataManager.getNoticia().setNotPais(Integer.valueOf(noticiaDataManager.getPais().toString()));
			ateneaService.createOrUpdateNoticia(noticiaDataManager.getNoticia());
			readNoticia();
			noticiaDataManager.setNoticia(new NoticiaDTO());
			JsfUtil.addInfoMessage("Guardado Exitosamente");
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}
	
	private void readNoticia()
	{
		try {
			noticiaDataManager.setNoticiaList(ateneaService.readNoticia());
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}
	
	private void readNoticiaPublic()
	{
		try {
			noticiaDataManager.setNoticiaPublicList(ateneaService.readNoticiaPublic(3));
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}
	
	public void edit(NoticiaDTO noti)
	{
		noticiaDataManager.setNoticia(noti);
		noticiaDataManager.setPais(noti.getNotPais());
	}
	
	public void delete(NoticiaDTO noti)
	{
		try {
			ateneaService.deleteNoticia(noti);
			readNoticia(); 
			JsfUtil.addInfoMessage("Eliminado Exitosamente");
		} catch (CorvustecException e) {
			JsfUtil.addErrorMessage(e.toString());
		}
	}
	
	public void cancel()
	{
		noticiaDataManager.setNoticia(new NoticiaDTO());
		noticiaDataManager.setPais(null);
	}
}
