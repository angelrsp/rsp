package ec.edu.uce.besg.ejb.persistence.dao;

import java.util.List;

import ec.edu.uce.besg.ejb.entity.HabilidadDTO;
import ec.edu.uce.besg.ejb.entity.HabilidadListDTO;

public interface HabilidadDAO extends AbstractFacade<HabilidadDTO> {

	List<HabilidadListDTO> getByAnd(HabilidadListDTO objeto)
			throws SecurityException;
	

}
