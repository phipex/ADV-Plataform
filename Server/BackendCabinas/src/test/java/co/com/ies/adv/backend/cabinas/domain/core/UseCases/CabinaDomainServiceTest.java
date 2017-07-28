package co.com.ies.adv.backend.cabinas.domain.core.UseCases;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import co.com.ies.adv.backend.cabinas.domain.Cabina;
import co.com.ies.adv.backend.cabinas.domain.core.entities.ICabina;
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.EstadoCabina;
import co.com.ies.adv.backend.cabinas.domain.core.exceptions.CabinaException;
import co.com.ies.adv.backend.cabinas.domain.core.repositorys.ICabinaRepository;
import co.com.ies.adv.backend.cabinas.domain.core.usecases.CabinaDomainService;

public class CabinaDomainServiceTest {

	@Rule
    public ExpectedException exceptions = ExpectedException.none();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testValidaNoExisteCabinaAsociada() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		exceptions.expect(CabinaException.class);
        exceptions.expectMessage(CabinaDomainService.NO_HAY_CABINA_ASOCIADA_AL_USUARIO);
		
		CabinaDomainService service = new CabinaDomainService();
		
		service.setICabinaRepository(mockRepository);
		
		service.validaCabina(0L);
		
	}
	
	@Test
	public void testValidaCabinaInactiva() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		exceptions.expect(CabinaException.class);
        exceptions.expectMessage(CabinaDomainService.CABINA_SE_ENCUENTRA_INACTIVA);
		
		CabinaDomainService service = new CabinaDomainService();
		Cabina cabina = new Cabina();
		cabina.setEstado(EstadoCabina.INACTIVO);
		
		mockRepository.setICabinaMock(cabina);
		
		
		service.setICabinaRepository(mockRepository);
		
		service.validaCabina(0L);
		
	}
	
	@Test
	public void testValidaCabinaSinCupo() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		exceptions.expect(CabinaException.class);
        exceptions.expectMessage(CabinaDomainService.LA_CABINA_NO_TIENE_CUPO);
		
		CabinaDomainService service = new CabinaDomainService();
		Cabina cabina = new Cabina();
		cabina.setEstado(EstadoCabina.ACTIVO);
		
		mockRepository.setICabinaMock(cabina);
		
		
		service.setICabinaRepository(mockRepository);
		
		service.validaCabina(0L);
		
	}
	
	public static class MockRepository implements ICabinaRepository{

		ICabina cabinaMock;
		
		public void setICabinaMock(Cabina cabina){
			this.cabinaMock = cabina;
		}
		
		@Override
		public ICabina findOneByUserId(Long userId) {
			
			return cabinaMock;
		}
		
		
		
	}
	

}
