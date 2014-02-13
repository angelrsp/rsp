package net.ciespal.redxxi.ejb.negocio;

import java.util.List;

import javax.ejb.Local;

import net.ciespal.redxxi.ejb.persistence.entities.CarreraDTO;
import net.ciespal.redxxi.ejb.persistence.entities.CentroDTO;
import net.ciespal.redxxi.ejb.persistence.entities.ContactoDTO;
import net.ciespal.redxxi.ejb.persistence.entities.ContactoListDTO;
import net.ciespal.redxxi.ejb.persistence.entities.EntidadDTO;
import net.ciespal.redxxi.ejb.persistence.entities.EventoDTO;
import net.ciespal.redxxi.ejb.persistence.entities.MencionDTO;
import net.ciespal.redxxi.ejb.persistence.entities.OrganizacionDTO;
import net.ciespal.redxxi.ejb.persistence.entities.ProyectoInvestigacionDTO;
import net.ciespal.redxxi.ejb.persistence.entities.PublicacionDTO;

import com.corvustec.commons.util.CorvustecException;

@Local
public interface AteneaService {

	CentroDTO createCentro(CentroDTO centro) throws CorvustecException;

	List<CentroDTO> obtenerCentroPadre() throws CorvustecException;

	List<CentroDTO> obtenerCentroHijo(CentroDTO centro)
			throws CorvustecException;

	CarreraDTO createCarrera(CarreraDTO carrera) throws CorvustecException;

	EntidadDTO createEntidad(EntidadDTO entidad) throws CorvustecException;

	List<ContactoListDTO> readContacto(EntidadDTO entidad)
			throws CorvustecException;

	List<MencionDTO> readMencion(CarreraDTO carrera) throws CorvustecException;

	List<ProyectoInvestigacionDTO> readProyectoInvestigacion(
			CarreraDTO carrera) throws CorvustecException;

	void updateEntidad(EntidadDTO entidad) throws CorvustecException;

	EventoDTO createEvento(EventoDTO evento) throws CorvustecException;

	List<EventoDTO> readEvento(CarreraDTO carrera) throws CorvustecException;

	PublicacionDTO createPublicacion(PublicacionDTO publicacion)
			throws CorvustecException;

	List<PublicacionDTO> readPublicacion(CarreraDTO carrera)
			throws CorvustecException;

	void deleteCentro(CentroDTO centro) throws CorvustecException;

	void updateCentro(CentroDTO centro) throws CorvustecException;

	List<CarreraDTO> readCarrera(CentroDTO centro, Object tipo)
			throws CorvustecException;

	List<ContactoListDTO> readContacto(CarreraDTO carrera)
			throws CorvustecException;

	OrganizacionDTO createOrganizacion(OrganizacionDTO organizacion)
			throws CorvustecException;

	List<OrganizacionDTO> readOrganizacion(Object ubicacion)
			throws CorvustecException;

	List<ContactoListDTO> readContacto(OrganizacionDTO organizacion)
			throws CorvustecException;

	ContactoDTO createOrUpdateContacto(ContactoDTO contactoDTO)
			throws CorvustecException;

	void deleteContacto(ContactoDTO contactoDTO) throws CorvustecException;

	MencionDTO createOrUpdateMencion(MencionDTO mencion)
			throws CorvustecException;

	void deleteMencion(MencionDTO mencion) throws CorvustecException;

	ProyectoInvestigacionDTO createOrUpdateProyectoInvestigacion(
			ProyectoInvestigacionDTO proyecto) throws CorvustecException;

	void deleteProyectoInvestigacion(ProyectoInvestigacionDTO proyecto)
			throws CorvustecException;

}
