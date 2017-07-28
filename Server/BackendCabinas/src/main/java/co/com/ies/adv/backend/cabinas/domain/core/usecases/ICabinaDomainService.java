package co.com.ies.adv.backend.cabinas.domain.core.usecases;

import co.com.ies.adv.backend.cabinas.domain.core.entities.ICabina;
import co.com.ies.adv.backend.cabinas.domain.core.exceptions.CabinaException;
import co.com.ies.adv.backend.cabinas.domain.core.repositorys.ICabinaRepository;

/**
 * Contiene los casos de uso referentes a las cabinas
 * @author Andres Felipe Montoya
 *
 */
public interface ICabinaDomainService {

	/**
	 * agrega el repositorio de las cabinas
	 * @param cabinaRepository
	 */
	void setICabinaRepository(ICabinaRepository cabinaRepository);

	/**
	 * verifica que sea una cabina valida
	 * @param userId usuario que esta asociado a la cabina
	 * @return una cabina valida asociada al id del usuario
	 * @throws CabinaException
	 */
	ICabina validaCabina(Long userId) throws CabinaException;
	
	/**
	 * Retorna el id siempre y cuando sea un usuario asociado a una cabina
	 * @param login
	 * @return
	 * @throws NullPointerException en caso que el usuario no sea una cabina
	 */
	public Long getUserCabinaId(String login) throws NullPointerException;
	
	
	public void loginCabina(String login) throws CabinaException;

}