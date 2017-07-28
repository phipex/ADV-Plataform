package co.com.ies.adv.backend.cabinas.domain.core.usecases;

import java.math.BigDecimal;

import co.com.ies.adv.backend.cabinas.domain.core.entities.ICabina;
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.EstadoCabina;
import co.com.ies.adv.backend.cabinas.domain.core.exceptions.CabinaException;
import co.com.ies.adv.backend.cabinas.domain.core.repositorys.ICabinaRepository;

/**
 * Logica de las cabinas
 * @author Andres Felipe Montoya
 *
 */
public class CabinaDomainService implements ICabinaDomainService {

	public static final String LA_CABINA_NO_TIENE_CUPO = "La cabina no tiene cupo";
	public static final String CABINA_SE_ENCUENTRA_INACTIVA = "Cabina se encuentra inactiva";
	public static final String NO_HAY_CABINA_ASOCIADA_AL_USUARIO = "No hay cabina asociada al usuario";
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
		
		//System.out.println(cabina);
		
		if(cabina == null){
			throw new CabinaException(NO_HAY_CABINA_ASOCIADA_AL_USUARIO);
		}
		
		EstadoCabina estado = cabina.getEstado();
		
		boolean noEsInactivo = !EstadoCabina.INACTIVO.equals(estado);
		
		if (!noEsInactivo) {
			throw new CabinaException(CABINA_SE_ENCUENTRA_INACTIVA);
		}
		
		BigDecimal cupo = cabina.getCupo();
		
		boolean hayCupo = cupo != null && BigDecimal.ZERO.compareTo(cupo) < 0;
		
		//System.out.println("cupo:"+cupo+",compareto"+BigDecimal.ZERO.compareTo(cupo));
		
		if (!hayCupo) {
			throw new CabinaException(LA_CABINA_NO_TIENE_CUPO);
		}
		
				
		return true;
	}
	
}