package ec.edu.uce.silsae.ejb.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ec.edu.uce.silsae.commons.dto.util.CredencialesDTO;
import ec.edu.uce.silsae.commons.util.SilsaeException;
import ec.edu.uce.silsae.ejb.persistence.dao.UsuarioDAO;
import ec.edu.uce.silsae.ejb.persistence.entities.EmpresaDTO;
import ec.edu.uce.silsae.ejb.persistence.entities.UsuarioDTO;

@Stateless
public class UsuarioDAOImpl extends AbstractFacadeImpl<UsuarioDTO> implements UsuarioDAO{
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioDAOImpl.class);
	
	public UsuarioDAOImpl () {}
	
	public UsuarioDAOImpl (EntityManager entityManager) {
		super(entityManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public UsuarioDTO buscarUsuarioLogin(CredencialesDTO credencialesDTO)
			throws SilsaeException {
		
		UsuarioDTO usuarioLogin = null;
		
		try {
//			u inner join u.bemCandidatos c where c.canIdentificacion =? and u.usuPassword =?
			Query query = entityManager.createQuery("select u from UsuarioDTO u where u.usuLogin =? and u.usuPassword =?");
			query.setParameter(1, credencialesDTO.getUsername());
			query.setParameter(2, credencialesDTO.getPassword());
			List<UsuarioDTO> usuariosEncontrados = query.getResultList();
			
			if (usuariosEncontrados != null && usuariosEncontrados.size()==1){
				usuarioLogin = usuariosEncontrados.get(0);
				Query query2=entityManager.createQuery("select u from EmpresaDTO u where u.bemUsuario.usuCodigo=?");
				query2.setParameter(1, usuarioLogin.getUsuCodigo());
				List<EmpresaDTO> emp=query2.getResultList();
				if(!emp.isEmpty())
				{
					usuarioLogin.setBemEmpresas(emp);
				}
				
			}
			
		} catch (Exception e) {
			log.info("Error al autenticar el usuario {}", e.toString());
			throw new SilsaeException("Error al autenticar el usuario" + e);
		}
		return usuarioLogin;
	}

}