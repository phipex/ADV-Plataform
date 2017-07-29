package co.com.ies.adv.backend.cabinas.domain.core.repositorys;

import co.com.ies.adv.backend.cabinas.domain.core.entities.ICabina;

public interface ICabinaRepository {

	ICabina findOneByUserId(Long userId);
	
	ICabina save(ICabina cabina);
	
}
