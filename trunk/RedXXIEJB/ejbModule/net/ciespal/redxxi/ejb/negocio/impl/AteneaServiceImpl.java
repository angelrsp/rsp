package net.ciespal.redxxi.ejb.negocio.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.ciespal.redxxi.ejb.negocio.AteneaService;
import net.ciespal.redxxi.ejb.persistence.dao.FactoryDAO;
import net.ciespal.redxxi.ejb.persistence.entities.AteneaDTO;
import net.ciespal.redxxi.ejb.persistence.entities.CarreraDTO;
import net.ciespal.redxxi.ejb.persistence.entities.CatalogoDTO;
import net.ciespal.redxxi.ejb.persistence.entities.CentroDTO;
import net.ciespal.redxxi.ejb.persistence.entities.ContactoDTO;
import net.ciespal.redxxi.ejb.persistence.entities.ContactoListDTO;
import net.ciespal.redxxi.ejb.persistence.entities.DoctorDTO;
import net.ciespal.redxxi.ejb.persistence.entities.EntidadDTO;
import net.ciespal.redxxi.ejb.persistence.entities.EventoDTO;
import net.ciespal.redxxi.ejb.persistence.entities.FacultadListDTO;
import net.ciespal.redxxi.ejb.persistence.entities.MencionDTO;
import net.ciespal.redxxi.ejb.persistence.entities.ModalidadDTO;
import net.ciespal.redxxi.ejb.persistence.entities.NoticiaDTO;
import net.ciespal.redxxi.ejb.persistence.entities.OrganizacionDTO;
import net.ciespal.redxxi.ejb.persistence.entities.PaisDTO;
import net.ciespal.redxxi.ejb.persistence.entities.ProyectoInvestigacionDTO;
import net.ciespal.redxxi.ejb.persistence.entities.PublicacionDTO;
import net.ciespal.redxxi.ejb.persistence.entities.UniversidadListDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corvustec.commons.util.CorvustecException;

@Stateless
public class AteneaServiceImpl implements AteneaService{

	private static final Logger logger = LoggerFactory.getLogger(AteneaServiceImpl.class);
	
	@EJB
	private FactoryDAO factoryDAO;
	
	/* Atenea*/
	@Override
	public List<AteneaDTO> readAtenea(Object pais) throws CorvustecException	
	{
		List<AteneaDTO> ateneaList=new ArrayList<AteneaDTO>();
		AteneaDTO atenea;
		CatalogoDTO cat;
		if(pais==null)
		{
			atenea=new AteneaDTO();
			atenea.setCodigo(1);
			atenea.setDescripcion("Universidades: ");
			atenea.setCount(factoryDAO.getCentroDAOImpl().getUniversidadCount());
			atenea.setTipo(2);
			ateneaList.add(atenea);
			
			atenea=new AteneaDTO();
			atenea.setCodigo(2);
			atenea.setDescripcion("Facultades: ");
			atenea.setCount(factoryDAO.getCentroDAOImpl().getFacultadCount());
			atenea.setTipo(3);
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(3);
			atenea.setDescripcion("Carreras de Pregrado: ");
			atenea.setCount(factoryDAO.getCarreraDAOImpl().getPregradoCount());
			atenea.setTipo(6);
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(4);
			atenea.setDescripcion("Carreras de Posgrado: ");
			atenea.setCount(factoryDAO.getCarreraDAOImpl().getPosgradoCount());
			atenea.setTipo(7);
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(5);
			atenea.setDescripcion("Revistas Cient�ficas: ");
			atenea.setCount(factoryDAO.getPublicacionDAOImpl().getCountByType(34));
			atenea.setTipo(34);
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(6);
			atenea.setDescripcion("Eventos Cient�ficos: ");
			atenea.setCount(factoryDAO.getEventoDAOImpl().getCount());
			atenea.setTipo(101);
			ateneaList.add(atenea);
			
			atenea=new AteneaDTO();
			atenea.setCodigo(7);
			atenea.setDescripcion("Proyectos de Investigaci�n: ");
			atenea.setCount(factoryDAO.getProyectoInvestigacionDAOImpl().getCount());
			atenea.setTipo(102);
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(8);
			atenea.setDescripcion("Organizaciones Cient�ficas: ");
			atenea.setCount(factoryDAO.getOrganizacioDAOImpl().getCount());
			atenea.setTipo(103);			
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(9);
			atenea.setDescripcion("Doctores en Ciencias: ");
			atenea.setCount(factoryDAO.getDoctorDAOImpl().getCount());
			atenea.setTipo(104);
			ateneaList.add(atenea);
		}
		else
		{
			cat=new CatalogoDTO();
			cat.setCatCodigo(Integer.valueOf(pais.toString()));
			atenea=new AteneaDTO();
			atenea.setCodigo(1);
			atenea.setDescripcion("Universidades: ");
			atenea.setCount(factoryDAO.getCentroDAOImpl().getUniversidadCount(cat.getCatCodigo()));
			atenea.setTipo(2);
			atenea.setPais(Integer.valueOf(pais.toString()));
			ateneaList.add(atenea);
			
			atenea=new AteneaDTO();
			atenea.setCodigo(2);
			atenea.setDescripcion("Facultades: ");
			atenea.setCount(factoryDAO.getCentroDAOImpl().getFacultadCount(cat.getCatCodigo()));
			atenea.setTipo(3);
			atenea.setPais(Integer.valueOf(pais.toString()));
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(3);
			atenea.setDescripcion("Carreras de Pregrado: ");
			atenea.setCount(factoryDAO.getCarreraDAOImpl().getPregradoCount(cat.getCatCodigo()));
			atenea.setTipo(6);
			atenea.setPais(Integer.valueOf(pais.toString()));
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(4);
			atenea.setDescripcion("Carreras de Posgrado: ");
			atenea.setCount(factoryDAO.getCarreraDAOImpl().getPosgradoCount(cat.getCatCodigo()));
			atenea.setTipo(7);
			atenea.setPais(Integer.valueOf(pais.toString()));
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(5);
			atenea.setDescripcion("Revistas Cient�ficas: ");
			atenea.setCount(factoryDAO.getPublicacionDAOImpl().getCountByType(34,cat.getCatCodigo()));
			atenea.setTipo(34);
			atenea.setPais(Integer.valueOf(pais.toString()));
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(6);
			atenea.setDescripcion("Eventos Cient�ficos: ");
			atenea.setCount(factoryDAO.getEventoDAOImpl().getCount(cat.getCatCodigo()));
			atenea.setTipo(101);
			atenea.setPais(Integer.valueOf(pais.toString()));
			ateneaList.add(atenea);
			
			atenea=new AteneaDTO();
			atenea.setCodigo(7);
			atenea.setDescripcion("Proyectos de Investigaci�n: ");
			atenea.setCount(factoryDAO.getProyectoInvestigacionDAOImpl().getCount(cat.getCatCodigo()));
			atenea.setTipo(102);
			atenea.setPais(Integer.valueOf(pais.toString()));
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(8);
			atenea.setDescripcion("Organizaciones Cient�ficas: ");
			atenea.setCount(factoryDAO.getOrganizacioDAOImpl().getCount(cat.getCatCodigo()));
			atenea.setTipo(103);			
			atenea.setPais(Integer.valueOf(pais.toString()));
			ateneaList.add(atenea);

			atenea=new AteneaDTO();
			atenea.setCodigo(9);
			atenea.setDescripcion("Doctores en Ciencias: ");
			atenea.setCount(factoryDAO.getDoctorDAOImpl().getCount(cat.getCatCodigo()));
			atenea.setTipo(104);
			atenea.setPais(Integer.valueOf(pais.toString()));
			ateneaList.add(atenea);
		}
		return ateneaList;
	}
	
	@Override
	public List<PaisDTO> readPais(Object type) throws CorvustecException
	{
		List<PaisDTO> paisList=new ArrayList<PaisDTO>();
		PaisDTO pais;
		CatalogoDTO catalogo;

		catalogo=new CatalogoDTO();
		catalogo.setCatCodigo(13);
		
		if(type==null)
		{
			
			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
				pais=new PaisDTO();
				pais.setCodigo(cat.getCatCodigo());
				pais.setImagenPath(cat.getCatImagenPath());
				pais.setNombre(cat.getCatDescripcion());
				paisList.add(pais);
			}
			
		}
		//Universidad
		else if(type.equals(2))
		{
			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
				pais=new PaisDTO();
				pais.setCodigo(cat.getCatCodigo());
				pais.setImagenPath(cat.getCatImagenPath());
				pais.setNombre(cat.getCatDescripcion());
				pais.setCount(factoryDAO.getCentroDAOImpl().getUniversidadCount(cat.getCatCodigo()));
				pais.setTipo(Integer.valueOf(type.toString()));
				paisList.add(pais);
			}
		}//Facultad
		else if(type.equals(3))
		{
			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
				pais=new PaisDTO();
				pais.setCodigo(cat.getCatCodigo());
				pais.setImagenPath(cat.getCatImagenPath());
				pais.setNombre(cat.getCatDescripcion());
				pais.setCount(factoryDAO.getCentroDAOImpl().getFacultadCount(cat.getCatCodigo()));
				pais.setTipo(Integer.valueOf(type.toString()));
				paisList.add(pais);
			}			
		}//Pregrado
		else if(type.equals(6))
		{
			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
				pais=new PaisDTO();
				pais.setCodigo(cat.getCatCodigo());
				pais.setImagenPath(cat.getCatImagenPath());
				pais.setNombre(cat.getCatDescripcion());
				pais.setCount(factoryDAO.getCarreraDAOImpl().getPregradoCount(cat.getCatCodigo()));
				pais.setTipo(Integer.valueOf(type.toString()));
				paisList.add(pais);
			}			
		}//Posgrado
		else if(type.equals(7))
		{
			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
				pais=new PaisDTO();
				pais.setCodigo(cat.getCatCodigo());
				pais.setImagenPath(cat.getCatImagenPath());
				pais.setNombre(cat.getCatDescripcion());
				pais.setCount(factoryDAO.getCarreraDAOImpl().getPosgradoCount(cat.getCatCodigo()));
				pais.setTipo(Integer.valueOf(type.toString()));
				paisList.add(pais);
			}
		}//Revista
		else if(type.equals(34))
		{
			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
				pais=new PaisDTO();
				pais.setCodigo(cat.getCatCodigo());
				pais.setImagenPath(cat.getCatImagenPath());
				pais.setNombre(cat.getCatDescripcion());
				pais.setCount(factoryDAO.getPublicacionDAOImpl().getCountByType(34,cat.getCatCodigo()));
				pais.setTipo(Integer.valueOf(type.toString()));
				paisList.add(pais);
			}
		}//Eventos
		else if(type.equals(101))
		{
			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
				pais=new PaisDTO();
				pais.setCodigo(cat.getCatCodigo());
				pais.setImagenPath(cat.getCatImagenPath());
				pais.setNombre(cat.getCatDescripcion());
				pais.setCount(factoryDAO.getEventoDAOImpl().getCount(cat.getCatCodigo()));
				pais.setTipo(Integer.valueOf(type.toString()));
				paisList.add(pais);
			}
		}//Proyectos de Investigacion
		else if(type.equals(102))
		{
			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
				pais=new PaisDTO();
				pais.setCodigo(cat.getCatCodigo());
				pais.setImagenPath(cat.getCatImagenPath());
				pais.setNombre(cat.getCatDescripcion());
				pais.setCount(factoryDAO.getProyectoInvestigacionDAOImpl().getCount(cat.getCatCodigo()));
				pais.setTipo(Integer.valueOf(type.toString()));
				paisList.add(pais);
			}
		}//Organizacion
		else if(type.equals(103))
		{
			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
				pais=new PaisDTO();
				pais.setCodigo(cat.getCatCodigo());
				pais.setImagenPath(cat.getCatImagenPath());
				pais.setNombre(cat.getCatDescripcion());
				pais.setCount(factoryDAO.getOrganizacioDAOImpl().getCount(cat.getCatCodigo()));
				pais.setTipo(Integer.valueOf(type.toString()));
				paisList.add(pais);
			}
		}//Doctor
		else if(type.equals(104))
		{
			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
				pais=new PaisDTO();
				pais.setCodigo(cat.getCatCodigo());
				pais.setImagenPath(cat.getCatImagenPath());
				pais.setNombre(cat.getCatDescripcion());
				pais.setCount(factoryDAO.getDoctorDAOImpl().getCount(cat.getCatCodigo()));
				pais.setTipo(Integer.valueOf(type.toString()));
				paisList.add(pais);
			}
		}
		return paisList;
	}
	
	@Override
	public String visor(AteneaDTO atenea) throws CorvustecException
	{
		StringBuilder sb=new StringBuilder();
		if(atenea.getTipo()==0)
		{
		}
		//Universidad
		else if(atenea.getTipo()==2)
		{
			for(CentroDTO centro: factoryDAO.getCentroDAOImpl().getCentro(2))
			{
				sb.append("<table>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append(centro.getCenNombre());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
		}//Facultad
		else if(atenea.getTipo()==3)
		{
			for(CentroDTO centro: factoryDAO.getCentroDAOImpl().getCentro(3))
			{
				sb.append("<table>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append(centro.getCenNombre());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}		
		}//Pregrado
		else if(atenea.getTipo()==6)
		{
			for(CarreraDTO car: factoryDAO.getCarreraDAOImpl().getByType(6)){
				sb.append("<table>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append(car.getCarNombre());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}			
		}//Posgrado
		else if(atenea.getTipo()==7)
		{
			for(CarreraDTO car: factoryDAO.getCarreraDAOImpl().getByType(7)){
				sb.append("<table>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append(car.getCarNombre());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}			
		}//Revista
		else if(atenea.getTipo()==34)
		{
			for(PublicacionDTO pub: factoryDAO.getPublicacionDAOImpl().getByType(34)){
				sb.append("<table>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append(pub.getPubTitulo());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
		}//Eventos
		else if(atenea.getTipo()==101)
		{
			for(EventoDTO eve: factoryDAO.getEventoDAOImpl().getAll()){
				sb.append("<table>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append(eve.getEveNombre());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
		}//Proyectos de Investigacion
		else if(atenea.getTipo()==102)
		{
			for(ProyectoInvestigacionDTO pro: factoryDAO.getProyectoInvestigacionDAOImpl().getAll()){
				sb.append("<table>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append(pro.getPinNombre());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
		}//Organizacion
		else if(atenea.getTipo()==103)
		{
//			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
//				pais=new PaisDTO();
//				pais.setCodigo(cat.getCatCodigo());
//				pais.setImagenPath(cat.getCatImagenPath());
//				pais.setNombre(cat.getCatDescripcion());
//				pais.setCount(factoryDAO.getOrganizacioDAOImpl().getCount(cat.getCatCodigo()));
//				pais.setTipo(Integer.valueOf(type.toString()));
//				paisList.add(pais);
//			}
		}//Doctor
		else if(atenea.getTipo()==104)
		{
//			for(CatalogoDTO cat: factoryDAO.getCatalogoImpl().getAll(catalogo)){
//				pais=new PaisDTO();
//				pais.setCodigo(cat.getCatCodigo());
//				pais.setImagenPath(cat.getCatImagenPath());
//				pais.setNombre(cat.getCatDescripcion());
//				pais.setCount(factoryDAO.getDoctorDAOImpl().getCount(cat.getCatCodigo()));
//				pais.setTipo(Integer.valueOf(type.toString()));
//				paisList.add(pais);
//			}
		}
		return sb.toString();
	}
	
	/* Centro */
	@Override
	public CentroDTO createOrUpdateCentro(CentroDTO centro) throws CorvustecException
	{
		logger.info("createCentro");
		try{
			if(centro.getCenCodigo()!=null)
				return factoryDAO.getCentroDAOImpl().edit(centro);
			else
			{
				return factoryDAO.getCentroDAOImpl().create(centro);
			}
		}
		catch(Exception e)
		{
			logger.info("Error createCentro {}",e.toString());
			throw new CorvustecException("Error al createCentro");
		}
	}
	
	@Override
	public void deleteCentro(CentroDTO centro) throws CorvustecException
	{
		logger.info("deleteCentro");
		try{
			factoryDAO.getCentroDAOImpl().remove(centro);
		}
		catch(Exception e){
			logger.info("Error eliminarCentro {}",e.toString());
			throw new CorvustecException("Error al eliminarCentro");
		}
	}
	
	@Override
	public void updateCentro(CentroDTO centro) throws CorvustecException
	{
		logger.info("updateCentro");
		try{
			factoryDAO.getCentroDAOImpl().edit(centro);
		}
		catch(Exception e){
			logger.info("Error updateCentro {}",e.toString());
			throw new CorvustecException("Error al updateCentro");
		}		
	}
	
	@Override
	public List<CentroDTO> obtenerCentroPadre() throws CorvustecException
	{
		logger.info("obtenerCentroPadre");
		try{
			return factoryDAO.getCentroDAOImpl().findAllPather();
		}
		catch(Exception e)
		{
			logger.info("Error obtenerCentroPadre {}",e.toString());
			throw new CorvustecException("Error al obtenerCentroPadre");
		}
	}

	@Override
	public List<CentroDTO> obtenerCentroPadre(Object ubicacion) throws CorvustecException
	{
		logger.info("obtenerCentroPadre");
		try{
			return factoryDAO.getCentroDAOImpl().findAllPather(ubicacion);
		}
		catch(Exception e)
		{
			logger.info("Error obtenerCentroPadre {}",e.toString());
			throw new CorvustecException("Error al obtenerCentroPadre");
		}
	}

	
	@Override
	public List<CentroDTO> obtenerCentroHijo(CentroDTO centro) throws CorvustecException
	{
		logger.info("obtenerCentroHijo");
		try{
			return factoryDAO.getCentroDAOImpl().findAllChild(centro);
		}
		catch(Exception e)
		{
			logger.info("Error obtenerCentroHijo {}",e.toString());
			throw new CorvustecException("Error al obtenerCentroHijo");
		}
	}

	@Override
	public List<CentroDTO> readCentroByType(Object type) throws CorvustecException
	{
		logger.info("readCentroByType");
		try{
			return factoryDAO.getCentroDAOImpl().findByType(type);
		}
		catch(Exception e)
		{
			logger.info("Error readCentroByType {}",e.toString());
			throw new CorvustecException("Error al readCentroByType");
		}
	}

	@Override
	public List<UniversidadListDTO> readUniversidad() throws CorvustecException
	{
		logger.info("readUniversidad");
		try{
			return factoryDAO.getCentroDAOImpl().getUniversidad();
		}
		catch(Exception e)
		{
			logger.info("Error readUniversidad {}",e.toString());
			throw new CorvustecException("Error al readUniversidad");
		}
	}

	@Override
	public List<FacultadListDTO> readFacultad() throws CorvustecException
	{
		logger.info("readFacultad");
		try{
			return factoryDAO.getCentroDAOImpl().getFacultad();
		}
		catch(Exception e)
		{
			logger.info("Error readFacultad {}",e.toString());
			throw new CorvustecException("Error al readFacultad");
		}
	}

	@Override
	public CentroDTO readCentro(Object id) throws CorvustecException
	{
		logger.info("readCentro");
		try{
			return factoryDAO.getCentroDAOImpl().find(Integer.valueOf(id.toString()));
		}
		catch(Exception e)
		{
			logger.info("Error readCentro {}",e.toString());
			throw new CorvustecException("Error al readCentro");
		}
	}

	/* Carrera */
	@Override
	public CarreraDTO createOrUpdateCarrera(CarreraDTO carrera) throws CorvustecException
	{
		logger.info("createCarrera");
		try{
			if(carrera.getCarCodigo()!=null)
				return factoryDAO.getCarreraDAOImpl().edit(carrera);
			else
			{
				carrera.addAteEntidad(new EntidadDTO());
				return factoryDAO.getCarreraDAOImpl().create(carrera);
			}
		}
		catch(Exception e)
		{
			logger.info("Error createCarrera {}",e.toString());
			throw new CorvustecException("Error al createCarrera");
		}
	}	
	
	@Override
	public List<CarreraDTO> readCarrera(CentroDTO centro,Object tipo) throws CorvustecException
	{
		logger.info("readCarrera");
		try{
			return factoryDAO.getCarreraDAOImpl().getAll(centro,tipo);
		}
		catch(Exception e)
		{
			logger.info("Error readCarrera {}",e.toString());
			throw new CorvustecException("Error al readCarrera");
		}
	}	
	
	@Override
	public List<CarreraDTO> readCarrera(Object type) throws CorvustecException
	{
		logger.info("readCarrera");
		try{
			return factoryDAO.getCarreraDAOImpl().getByType(type);
		}
		catch(Exception e)
		{
			logger.info("Error readCarrera {}",e.toString());
			throw new CorvustecException("Error al readCarrera");
		}
	}	
	
	/* Entidad */
	@Override
	public EntidadDTO createEntidad(EntidadDTO entidad) throws CorvustecException
	{
		logger.info("createEntidad");
		try{
			return factoryDAO.getEntidadDAOImpl().create(entidad);
		}
		catch(Exception e)
		{
			logger.info("Error createEntidad {}",e.toString());
			throw new CorvustecException("Error al createEntidad");
		}		
	}
	
	@Override
	public void updateEntidad(EntidadDTO entidad) throws CorvustecException
	{
		logger.info("updateEntidad");
		try{
			factoryDAO.getEntidadDAOImpl().edit(entidad);
		}
		catch(Exception e){
			logger.info("Error updateEntidad {}",e.toString());
			throw new CorvustecException("Error al updateEntidad");
		}
	}
	

	
	/* Contacto */
	@Override
	public ContactoDTO createOrUpdateContacto(ContactoDTO contactoDTO) throws CorvustecException
	{
		logger.info("createOrUpdateContacto");
		try{
			if(contactoDTO.getConCodigo()!=null)
				return factoryDAO.getContactoDAOImpl().edit(contactoDTO);
			else
				return factoryDAO.getContactoDAOImpl().create(contactoDTO);
		}
		catch(Exception e)
		{
			logger.info("Error createOrUpdateContacto {}",e.toString());
			throw new CorvustecException("Error al createOrUpdateContacto");
		}		
	}

	@Override
	public void deleteContacto(ContactoDTO contactoDTO) throws CorvustecException
	{
		logger.info("deleteContacto");
		try{
			factoryDAO.getContactoDAOImpl().remove(contactoDTO);
		}
		catch(Exception e)
		{
			logger.info("Error createEntidad {}",e.toString());
			throw new CorvustecException("Error al createEntidad");
		}		
	}

	
	@Override
	public List<ContactoListDTO> readContacto(EntidadDTO entidad) throws CorvustecException
	{
		logger.info("readContacto");
		try{
			return factoryDAO.getContactoDAOImpl().getAll(entidad);
		}
		catch(Exception e)
		{
			logger.info("Error readContacto {}",e.toString());
			throw new CorvustecException("Error al readContacto");
		}		
	}

	@Override
	public List<ContactoListDTO> readContacto(CarreraDTO carrera) throws CorvustecException
	{
		logger.info("readContacto");
		try{
			return factoryDAO.getContactoDAOImpl().getAll(carrera);
		}
		catch(Exception e)
		{
			logger.info("Error readContacto {}",e.toString());
			throw new CorvustecException("Error al readContacto");
		}		
	}
	
	@Override
	public List<ContactoListDTO> readContacto(OrganizacionDTO organizacion) throws CorvustecException
	{
		logger.info("readContacto");
		try{
			return factoryDAO.getContactoDAOImpl().getAll(organizacion);
		}
		catch(Exception e)
		{
			logger.info("Error readContacto {}",e.toString());
			throw new CorvustecException("Error al readContacto");
		}		
	}	
	
	@Override
	public List<ContactoListDTO> readContacto(DoctorDTO doctor) throws CorvustecException
	{
		logger.info("readContacto");
		try{
			return factoryDAO.getContactoDAOImpl().getAll(doctor);
		}
		catch(Exception e)
		{
			logger.info("Error readContacto {}",e.toString());
			throw new CorvustecException("Error al readContacto");
		}		
	}	
	/* Mencion */
	@Override
	public MencionDTO createOrUpdateMencion(MencionDTO mencion) throws CorvustecException
	{
		logger.info("createMencion");
		try{
			if(mencion.getMenCodigo()!=null)
				return factoryDAO.getMencionDAOImpl().edit(mencion);
			else
				return factoryDAO.getMencionDAOImpl().create(mencion);
		}
		catch(Exception e)
		{
			logger.info("Error createMencion {}",e.toString());
			throw new CorvustecException("Error al createMencion");
		}		
	}
	
	@Override
	public void deleteMencion(MencionDTO mencion) throws CorvustecException
	{
		logger.info("deleteMencion");
		try{
			factoryDAO.getMencionDAOImpl().remove(mencion);
		}
		catch(Exception e)
		{
			logger.info("Error deleteMencion {}",e.toString());
			throw new CorvustecException("Error al deleteMencion");
		}		
	}
	
	@Override
	public List<MencionDTO> readMencion(CarreraDTO carrera) throws CorvustecException
	{
		logger.info("readContacto");
		try{
			return factoryDAO.getMencionDAOImpl().getAll(carrera);
		}
		catch(Exception e)
		{
			logger.info("Error readContacto {}",e.toString());
			throw new CorvustecException("Error al readContacto");
		}		
	}
	
	/* Proyecto */
	@Override
	public ProyectoInvestigacionDTO createOrUpdateProyectoInvestigacion(ProyectoInvestigacionDTO proyecto) throws CorvustecException
	{
		logger.info("createOrUpdateProyectoInvestigacion");
		try{
			if(proyecto.getPinCodigo()!=null)
				return factoryDAO.getProyectoInvestigacionDAOImpl().edit(proyecto);
			else	
			{
				proyecto.addAteEntidad(new EntidadDTO());
				return factoryDAO.getProyectoInvestigacionDAOImpl().create(proyecto);
			}
		}
		catch(Exception e){
			logger.info("Error createOrUpdateProyectoInvestigacion {}",e.toString());
			throw new CorvustecException("Error al createOrUpdateProyectoInvestigacion");
		}
	}

	@Override
	public void deleteProyectoInvestigacion(ProyectoInvestigacionDTO proyecto) throws CorvustecException
	{
		logger.info("deleteProyectoInvestigacion");
		try{
			factoryDAO.getProyectoInvestigacionDAOImpl().remove2(proyecto);
		}
		catch(Exception e){
			logger.info("Error deleteProyectoInvestigacion {}",e.toString());
			throw new CorvustecException("Error al deleteProyectoInvestigacion");
		}
	}

	
	@Override
	public List<ProyectoInvestigacionDTO> readProyectoInvestigacion(CarreraDTO carrera) throws CorvustecException
	{
		logger.info("obtenerProyectoInvestigacion");
		try{
			return factoryDAO.getProyectoInvestigacionDAOImpl().getAll(carrera);
		}
		catch(Exception e)
		{
			logger.info("Error obtenerProyectoInvestigacion {}",e.toString());
			throw new CorvustecException("Error al obtenerProyectoInvestigacion");
		}
	}
	
	@Override
	public List<ProyectoInvestigacionDTO> readProyectoInvestigacion(OrganizacionDTO org) throws CorvustecException
	{
		logger.info("obtenerProyectoInvestigacion");
		try{
			return factoryDAO.getProyectoInvestigacionDAOImpl().getAll(org);
		}
		catch(Exception e)
		{
			logger.info("Error obtenerProyectoInvestigacion {}",e.toString());
			throw new CorvustecException("Error al obtenerProyectoInvestigacion");
		}
	}
	
	@Override
	public List<ProyectoInvestigacionDTO> readProyectoInvestigacion() throws CorvustecException
	{
		logger.info("readProyectoInvestigacion");
		try{
			return factoryDAO.getProyectoInvestigacionDAOImpl().getAll();
		}
		catch(Exception e)
		{
			logger.info("Error readProyectoInvestigacion {}",e.toString());
			throw new CorvustecException("Error al readProyectoInvestigacion");
		}
	}
	/* Evento */
	@Override
	public EventoDTO createOrUpdateEvento(EventoDTO evento) throws CorvustecException
	{
		logger.info("createProyectoInvestigacion");
		try{
			if(evento.getEveCodigo()!=null)
				return factoryDAO.getEventoDAOImpl().edit(evento);
			else
			{
				evento.addAteEntidad(new EntidadDTO());
				return factoryDAO.getEventoDAOImpl().create(evento);
			}
		}
		catch(Exception e){
			logger.info("Error createProyectoInvestigacion {}",e.toString());
			throw new CorvustecException("Error al createProyectoInvestigacion");
		}
	}
	
	@Override
	public void deleteEvento(EventoDTO evento) throws CorvustecException
	{
		logger.info("createProyectoInvestigacion");
		try{
			factoryDAO.getEventoDAOImpl().remove2(evento);
		}
		catch(Exception e){
			logger.info("Error createProyectoInvestigacion {}",e.toString());
			throw new CorvustecException("Error al createProyectoInvestigacion");
		}
	}
	
	@Override
	public List<EventoDTO> readEvento(CarreraDTO carrera) throws CorvustecException
	{
		logger.info("readEvento");
		try{
			return factoryDAO.getEventoDAOImpl().getAll(carrera);
		}
		catch(Exception e)
		{
			logger.info("Error readEvento {}",e.toString());
			throw new CorvustecException("Error al readEvento");
		}
	}

	@Override
	public List<EventoDTO> readEvento(OrganizacionDTO org) throws CorvustecException
	{
		logger.info("readEvento");
		try{
			return factoryDAO.getEventoDAOImpl().getAll(org);
		}
		catch(Exception e)
		{
			logger.info("Error readEvento {}",e.toString());
			throw new CorvustecException("Error al readEvento");
		}
	}
	@Override
	public List<EventoDTO> readEvento() throws CorvustecException
	{
		logger.info("readEvento");
		try{
			return factoryDAO.getEventoDAOImpl().getAll();
		}
		catch(Exception e)
		{
			logger.info("Error readEvento {}",e.toString());
			throw new CorvustecException("Error al readEvento");
		}
	}
	
	
	/* Publicacion */
	@Override
	public PublicacionDTO createOrUpdatePublicacion(PublicacionDTO publicacion) throws CorvustecException
	{
		logger.info("createOrUpdatePublicacion");
		try{
			if(publicacion.getPubCodigo()!=null)
				return factoryDAO.getPublicacionDAOImpl().edit(publicacion);
			else			
			{
				publicacion.addAteEntidad(new EntidadDTO());
				return factoryDAO.getPublicacionDAOImpl().create(publicacion);
			}
		}
		catch(Exception e){
			logger.info("Error createOrUpdatePublicacion {}",e.toString());
			throw new CorvustecException("Error al createOrUpdatePublicacion");
		}
	}

	
	@Override
	public void deletePublicacion(PublicacionDTO publicacion) throws CorvustecException
	{
		logger.info("deletePublicacion");
		try{
			factoryDAO.getPublicacionDAOImpl().remove2(publicacion);
		}
		catch(Exception e){
			logger.info("Error deletePublicacion {}",e.toString());
			throw new CorvustecException("Error al deletePublicacion");
		}
	}
	
	@Override
	public List<PublicacionDTO> readPublicacion(CarreraDTO carrera) throws CorvustecException
	{
		logger.info("readPublicacion");
		try{
			return factoryDAO.getPublicacionDAOImpl().getAll(carrera);
		}
		catch(Exception e)
		{
			logger.info("Error readPublicacion {}",e.toString());
			throw new CorvustecException("Error al readPublicacion");
		}
	}

	@Override
	public List<PublicacionDTO> readPublicacion(Object ubicacion) throws CorvustecException
	{
		logger.info("readPublicacion");
		try{
			return factoryDAO.getPublicacionDAOImpl().getAll(ubicacion);
		}
		catch(Exception e)
		{
			logger.info("Error readPublicacion {}",e.toString());
			throw new CorvustecException("Error al readPublicacion");
		}
	}

	@Override
	public List<PublicacionDTO> readPublicacion() throws CorvustecException
	{
		logger.info("readPublicacion");
		try{
			return factoryDAO.getPublicacionDAOImpl().getAll();
		}
		catch(Exception e)
		{
			logger.info("Error readPublicacion {}",e.toString());
			throw new CorvustecException("Error al readPublicacion");
		}
	}

	@Override
	public List<PublicacionDTO> readPublicacionByType(Object type) throws CorvustecException
	{
		logger.info("readPublicacion");
		try{
			return factoryDAO.getPublicacionDAOImpl().getByType(type);
		}
		catch(Exception e)
		{
			logger.info("Error readPublicacion {}",e.toString());
			throw new CorvustecException("Error al readPublicacion");
		}
	}

	
	/* Organizacion */
	@Override
	public OrganizacionDTO createOrUpdateOrganizacion(OrganizacionDTO organizacion) throws CorvustecException
	{
		logger.info("createOrganizacion");
		try{
			if(organizacion.getOrgCodigo()!=null)
				return factoryDAO.getOrganizacioDAOImpl().edit(organizacion);
			else
			{
				organizacion.addAteEntidad(new EntidadDTO());
				return factoryDAO.getOrganizacioDAOImpl().create(organizacion);
			}
		}
		catch(Exception e){
			logger.info("Error createOrganizacion {}",e.toString());
			throw new CorvustecException("Error al createOrganizacion");
		}
	}

	@Override
	public List<OrganizacionDTO> readOrganizacion(Object ubicacion) throws CorvustecException
	{
		logger.info("readOrganizacion");
		try{
			return factoryDAO.getOrganizacioDAOImpl().getAll(ubicacion);
		}
		catch(Exception e){
			logger.info("Error readOrganizacion {}",e.toString());
			throw new CorvustecException("Error al readOrganizacion");
		}
	}
	
	@Override
	public List<OrganizacionDTO> readOrganizacion() throws CorvustecException
	{
		logger.info("readOrganizacion");
		try{
			return factoryDAO.getOrganizacioDAOImpl().getAll();
		}
		catch(Exception e){
			logger.info("Error readOrganizacion {}",e.toString());
			throw new CorvustecException("Error al readOrganizacion");
		}
	}
	

	
	/* Doctor */
	@Override
	public DoctorDTO createOrUpdateDoctor(DoctorDTO doctor) throws CorvustecException
	{
		logger.info("createOrUpdateDoctor");
		try{
			if(doctor.getDocCodigo()!=null)
				return factoryDAO.getDoctorDAOImpl().edit(doctor);
			else
			{
				doctor.addAteEntidad(new EntidadDTO());
				return factoryDAO.getDoctorDAOImpl().create(doctor);
			}
		}
		catch(Exception e){
			logger.info("Error createOrUpdateDoctor {}",e.toString());
			throw new CorvustecException("Error al createOrUpdateDoctor");
		}
	}
	
	@Override
	public List<DoctorDTO> readDoctor(Object ubicacion) throws CorvustecException
	{
		logger.info("readDoctor");
		try{
			return factoryDAO.getDoctorDAOImpl().getAll(ubicacion);
		}
		catch(Exception e)
		{
			logger.info("Error readDoctor {}",e.toString());
			throw new CorvustecException("Error al readDoctor");
		}
	}
	
	@Override
	public List<DoctorDTO> readDoctor() throws CorvustecException
	{
		logger.info("readDoctor");
		try{
			return factoryDAO.getDoctorDAOImpl().getAll();
		}
		catch(Exception e){
			logger.info("Error readDoctor {}",e.toString());
			throw new CorvustecException("Error al readDoctor");
		}
	}
	
	/* Noticia */	
	@Override
	public NoticiaDTO createOrUpdateNoticia(NoticiaDTO noticia) throws CorvustecException
	{
		logger.info("createNoticia");
		try{
			noticia.setNotFecha(new Timestamp(new Date().getTime()));
			if(noticia.getNotCodigo()!=null)
				return factoryDAO.getNoticiaDAOImpl().edit(noticia);
			else
				return factoryDAO.getNoticiaDAOImpl().create(noticia);
		}
		catch(Exception e){
			logger.info("Error createNoticia {}",e.toString());
			throw new CorvustecException("Error al createNoticia");
		}
	}

	@Override
	public void deleteNoticia(NoticiaDTO noticia) throws CorvustecException
	{
		logger.info("createNoticia");
		try{
			factoryDAO.getNoticiaDAOImpl().remove2(noticia);
		}
		catch(Exception e){
			logger.info("Error createNoticia {}",e.toString());
			throw new CorvustecException("Error al createNoticia");
		}
	}

	@Override
	public List<NoticiaDTO> readNoticia() throws CorvustecException
	{
		logger.info("readNoticia");
		try{
			return factoryDAO.getNoticiaDAOImpl().getAll();
		}
		catch(Exception e)
		{
			logger.info("Error readNoticia {}",e.toString());
			throw new CorvustecException("Error al readNoticia");
		}
	}

	@Override
	public List<NoticiaDTO> readNoticiaPublic() throws CorvustecException
	{
		logger.info("readNoticia");
		try{
			return factoryDAO.getNoticiaDAOImpl().getAllPublic();
		}
		catch(Exception e)
		{
			logger.info("Error readNoticia {}",e.toString());
			throw new CorvustecException("Error al readNoticia");
		}
	}

	/*Nmodalidad*/
	@Override
	public List<ModalidadDTO> readModalidad(CarreraDTO carrera) throws CorvustecException
	{
		logger.info("readModalidad");
		try{
			return factoryDAO.getModalidadDAOImpl().getAll(carrera);
		}
		catch(Exception e)
		{
			logger.info("Error readModalidad {}",e.toString());
			throw new CorvustecException("Error al readModalidad");
		}
	}
	
}