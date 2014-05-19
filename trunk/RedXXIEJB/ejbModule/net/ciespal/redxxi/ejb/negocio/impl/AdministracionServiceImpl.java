package net.ciespal.redxxi.ejb.negocio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.ciespal.redxxi.ejb.negocio.AdministracionService;
import net.ciespal.redxxi.ejb.persistence.dao.FactoryDAO;
import net.ciespal.redxxi.ejb.persistence.entities.CatalogoDTO;
import net.ciespal.redxxi.ejb.persistence.entities.security.UsuarioDTO;
import net.ciespal.redxxi.ejb.persistence.entities.util.dto.CredencialesDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corvustec.commons.util.ApplicationUtil;
import com.corvustec.commons.util.CorvustecException;


@Stateless
public class AdministracionServiceImpl implements AdministracionService{

	private static final Logger logger = LoggerFactory.getLogger(AdministracionServiceImpl.class);
	
	@EJB
	private FactoryDAO factoryDAO;
	
	@Override
	public List<CatalogoDTO> getCatalogo(CatalogoDTO catalogo) throws CorvustecException
	{
		logger.info("getCatalogo");
		try{
			return factoryDAO.getCatalogoImpl().getAll(catalogo);
		}
		catch(Exception e)
		{
			logger.info("Error al obtener catalogo" +e.toString());
			throw new CorvustecException("Error al obtener catalogo");
		}
	}
	
	@Override
	public CatalogoDTO createOrUpdateCatalogo(CatalogoDTO catalogo) throws CorvustecException
	{
		logger.info("createOrupdateCatalogo");
		try{
			if(catalogo.getCatImagenNombre()!=null)
				ApplicationUtil.deletefile(catalogo.getCatImagenNombre());
			if(catalogo.getCatCodigo()!=null)
				return factoryDAO.getCatalogoImpl().edit(catalogo);
			else
				return factoryDAO.getCatalogoImpl().create(catalogo);
		}
		catch(Exception e)
		{
			logger.info("Error al createOrupdateCatalogo" +e.toString());
			throw new CorvustecException("Error al createOrupdateCatalogo");
		}
	}
		
	@Override
	public CatalogoDTO getCatalogo(Object id) throws CorvustecException
	{
		logger.info("getCatalogo");
		try{
			return factoryDAO.getCatalogoImpl().find(id);
		}
		catch(Exception e)
		{
			logger.info("Error al obtener catalogo" +e.toString());
			throw new CorvustecException("Error al obtener catalogo");
		}
	}
	
	@Override
	public void deleteCatalogo(CatalogoDTO catalogo) throws CorvustecException
	{
		logger.info("deleteCatalogo");
		try{
			if(getCatalogo(catalogo).size()<=0)
				factoryDAO.getCatalogoImpl().remove2(catalogo);
			else
				throw new CorvustecException("No se pudo eliminar existen dependencias");
		}
		catch(Exception e)
		{
			logger.info("Error al deleteCatalogo" +e.toString());
			throw new CorvustecException("Error al deleteCatalogo");
		}
	}
	
	
	/*Usuario*/
	@Override
	public UsuarioDTO userAuthentication(CredencialesDTO credenciales) throws CorvustecException
	{
		logger.info("userAuthentication");
		UsuarioDTO usuario;
		List<UsuarioDTO> userList;
		try{
			usuario=new UsuarioDTO();
			usuario.setUsuLogin(credenciales.getUser());
			usuario.setUsuClave(credenciales.getPassword());
			userList= factoryDAO.getUsuarioDAOImpl().getByAnd(usuario);
			if(!userList.isEmpty())
				return userList.get(0);
			else
				return null;
		}
		catch(Exception e)
		{
			logger.info("Error al userAuthentication" +e.toString());
			throw new CorvustecException("Error al userAuthentication "+e.toString());
		}		
	}
	
}
