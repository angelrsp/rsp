package net.ciespal.redxxi.ejb.negocio;

import java.util.List;

import javax.ejb.Local;

import net.ciespal.redxxi.ejb.persistence.entities.CatalogoDTO;
import net.ciespal.redxxi.ejb.persistence.entities.security.AccesoVieDTO;
import net.ciespal.redxxi.ejb.persistence.entities.security.ComponenteDTO;
import net.ciespal.redxxi.ejb.persistence.entities.security.ComponenteMenuDTO;
import net.ciespal.redxxi.ejb.persistence.entities.security.MenuDTO;
import net.ciespal.redxxi.ejb.persistence.entities.security.PerfilDTO;
import net.ciespal.redxxi.ejb.persistence.entities.security.UsuarioDTO;
import net.ciespal.redxxi.ejb.persistence.entities.util.dto.CredencialesDTO;
import net.ciespal.redxxi.ejb.persistence.entities.vo.AccesoVO;

import com.corvustec.commons.util.CorvustecException;

@Local
public interface AdministracionService {

	List<CatalogoDTO> getCatalogo(CatalogoDTO catalogo)
			throws CorvustecException;

	CatalogoDTO getCatalogo(Object id) throws CorvustecException;

	CatalogoDTO createOrUpdateCatalogo(CatalogoDTO catalogo)
			throws CorvustecException;

	void deleteCatalogo(CatalogoDTO catalogo) throws CorvustecException;

	UsuarioDTO userAuthentication(CredencialesDTO credenciales)
			throws CorvustecException;

	UsuarioDTO createOrUpdateUsuario(UsuarioDTO usuarioDTO)
			throws CorvustecException;

	List<UsuarioDTO> readUser(UsuarioDTO usuarioDTO) throws CorvustecException;

	List<UsuarioDTO> readAllUser() throws CorvustecException;

	List<PerfilDTO> perfilReadAll() throws CorvustecException;

	PerfilDTO perfilCreateOrUpdate(PerfilDTO perfilDTO)
			throws CorvustecException;

	List<MenuDTO> menuReadAll() throws CorvustecException;

	MenuDTO menuCreateOrUpdate(MenuDTO menuDTO) throws CorvustecException;

	List<MenuDTO> menuRootRead() throws CorvustecException;

	List<ComponenteDTO> componenteReadAll() throws CorvustecException;

	ComponenteDTO componenteCreateOrUpdate(ComponenteDTO componenteDTO)
			throws CorvustecException;

	List<ComponenteMenuDTO> componenteMenuReadAll() throws CorvustecException;

	ComponenteMenuDTO componenteMenuCreateOrUpdate(
			ComponenteMenuDTO componenteDTO) throws CorvustecException;

	List<AccesoVieDTO> accesoVieRead(AccesoVieDTO acceso)
			throws CorvustecException;

	void accesoCreateOrUpdate(AccesoVO acceso) throws CorvustecException;

	List<AccesoVieDTO> accesoVieReadPerfilIsNull(AccesoVieDTO acceso)
			throws CorvustecException;

	void accesoDelete(AccesoVO acceso) throws CorvustecException;

}
