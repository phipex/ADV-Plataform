package co.com.ies.adv.backend.cabinas.domain.core.UseCases;

import co.com.ies.adv.backend.cabinas.domain.core.exceptions.CabinaException;
import co.com.ies.adv.backend.cabinas.domain.core.repositorys.ICabinaRepository;

public interface ICabinaDomainService {

	void setICabinaRepository(ICabinaRepository cabinaRepository);

	boolean validaCanbina(Long userId) throws CabinaException;

}