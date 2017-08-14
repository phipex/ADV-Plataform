package co.com.ies.adv.backend.cabinas.domain.core.usecases;

import java.math.BigDecimal;

import co.com.ies.adv.backend.cabinas.domain.core.entities.ICabina;
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.ErroresCabina;
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.EstadoCabina;
import co.com.ies.adv.backend.cabinas.domain.core.exceptions.CabinaException;
import co.com.ies.adv.backend.cabinas.domain.core.repositorys.ICabinaRepository;

/**
 * Logica de las cabinas
 * @author Andres Felipe Montoya
 *
 */
public abstract class CabinaDomainService implements ICabinaDomainService {

	/*public static final String ERROR_AL_INGRESAR_LA_CABINA_AL_SISTEMA = "Error al ingresar la cabina al sistema";
	public static final String LA_CABINA_NO_TIENE_CUPO = "La cabina no tiene cupo";
	public static final String CABINA_SE_ENCUENTRA_INACTIVA = "Cabina se encuentra inactiva";
	public static final String NO_HAY_CABINA_ASOCIADA_AL_USUARIO = "No hay cabina asociada al usuario";
*/	
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
	public ICabina validaCabina(Long userId) throws CabinaException{
		
		
		ICabina cabina = cabinaRepository.findOneByUserId(userId);
		
		if(cabina == null){
			throw new CabinaException(ErroresCabina.NO_HAY_CABINA_ASOCIADA_AL_USUARIO);
		}
		
		EstadoCabina estado = cabina.getEstado();
		
		boolean noEsInactivo = !EstadoCabina.INACTIVO.equals(estado);
		
		if (!noEsInactivo) {
			throw new CabinaException(ErroresCabina.CABINA_SE_ENCUENTRA_INACTIVA);
		}
		
		BigDecimal cupo = cabina.getCupo();
		
		boolean hayCupo = cupo != null && BigDecimal.ZERO.compareTo(cupo) < 0;
		
		if (!hayCupo) {
			throw new CabinaException(ErroresCabina.LA_CABINA_NO_TIENE_CUPO);
		}
		
				
		return cabina;
	}
	
	public abstract Long getUserCabinaId(String login);
	
	public void loginCabina(String login) throws CabinaException{
		
		try {
			Long userId = getUserCabinaId(login);
			
			if(userId == null){
				return;//no hay una cabina asociada al usuario
			}
			
			ICabina cabinaValida = validaCabina(userId);
			
			if (cabinaValida != null && EstadoCabina.BLOQUEADA.equals(cabinaValida.getEstado())  ) {
				cabinaValida.setEstado(EstadoCabina.ACTIVO);
				cabinaRepository.save(cabinaValida);
			}
		} catch (CabinaException e) {
			throw new CabinaException(e.getEnumError());
		} catch (Exception e) {
			throw new CabinaException(ErroresCabina.ERROR_AL_INGRESAR_LA_CABINA_AL_SISTEMA);
		}
		
		
	}
	
	
}
