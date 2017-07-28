package co.com.ies.adv.backend.cabinas.domain.core.usecases;

import co.com.ies.adv.backend.cabinas.domain.core.exceptions.CabinaException;
import co.com.ies.adv.backend.cabinas.domain.core.repositorys.ICabinaRepository;

public interface ICabinaDomainService {

	void setICabinaRepository(ICabinaRepository cabinaRepository);

	boolean validaCabina(Long userId) throws CabinaException;

}