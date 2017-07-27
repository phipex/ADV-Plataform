package co.com.ies.adv.backend.cabinas.domain.core.UseCases;

import java.math.BigDecimal;

import co.com.ies.adv.backend.cabinas.domain.core.entities.ICabina;
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.EstadoCabina;
import co.com.ies.adv.backend.cabinas.domain.core.exceptions.CabinaException;
import co.com.ies.adv.backend.cabinas.domain.core.repositorys.ICabinaRepository;

public class CabinaDomainService implements ICabinaDomainService {

	private ICabinaRepository cabinaRepository;

	/* (non-Javadoc)
	 * @see co.com.ies.adv.backend.cabinas.domain.core.UseCases.ICabinaDomainService#setICabinaRepository(co.com.ies.adv.backend.cabinas.domain.core.repositorys.ICabinaRepository)
	 */
	@Override
	public void setICabinaRepository(ICabinaRepository cabinaRepository){
		this.cabinaRepository = cabinaRepository;
	}
	
	/* (non-Javadoc)
	 * @see co.com.ies.adv.backend.cabinas.domain.core.UseCases.ICabinaDomainService#validaCanbina(java.lang.Long)
	 */
	@Override
	public boolean validaCabina(Long userId) throws CabinaException{
		
		
		ICabina cabina = cabinaRepository.findOneByUserId(userId);
		
		System.out.println(cabina);
		
		if(cabina == null){
			throw new CabinaException("No hay cabina asociada al usuario");
		}
		
		BigDecimal cupo = cabina.getCupo();
		EstadoCabina estado = cabina.getEstado();
		
		boolean noEsInactivo = !EstadoCabina.INACTIVO.equals(estado);
		boolean hayCupo = BigDecimal.ZERO.compareTo(cupo) < 0;
		
		System.out.println("cupo:"+cupo+",compareto"+BigDecimal.ZERO.compareTo(cupo));
		
		
		if (!noEsInactivo) {
			throw new CabinaException("Cabina se encuentra inactiva");
		}
		
		if (!hayCupo) {
			throw new CabinaException("La cabina no tiene cupo");
		}
		
				
		return true;
	}
	
}
