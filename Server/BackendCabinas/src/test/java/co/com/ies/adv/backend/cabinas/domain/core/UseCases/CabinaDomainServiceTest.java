package co.com.ies.adv.backend.cabinas.domain.core.UseCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

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
	public void testValidaErrorNoExisteCabinaAsociada() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		exceptions.expect(CabinaException.class);
        exceptions.expectMessage(CabinaDomainService.NO_HAY_CABINA_ASOCIADA_AL_USUARIO);
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		
		service.setICabinaRepository(mockRepository);
		
		service.validaCabina(0L);
		
	}
	
	@Test
	public void testValidaErrorCabinaInactiva() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		exceptions.expect(CabinaException.class);
        exceptions.expectMessage(CabinaDomainService.CABINA_SE_ENCUENTRA_INACTIVA);
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		Cabina cabina = new Cabina();
		cabina.setEstado(EstadoCabina.INACTIVO);
		
		mockRepository.setICabinaMock(cabina);
		
		
		service.setICabinaRepository(mockRepository);
		
		service.validaCabina(0L);
		
	}
	
	@Test
	public void testValidaErrorCabinaSinCupo() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		

		exceptions.expect(CabinaException.class);
        exceptions.expectMessage(CabinaDomainService.LA_CABINA_NO_TIENE_CUPO);
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		Cabina cabina = new Cabina();
		cabina.setEstado(EstadoCabina.ACTIVO);
		
		mockRepository.setICabinaMock(cabina);
		
		
		service.setICabinaRepository(mockRepository);
		
		service.validaCabina(0L);
		assertTrue(true);
		
	}
	
	@Test
	public void testValidaCabinaOk() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		Cabina cabina = new Cabina();
		cabina.setEstado(EstadoCabina.ACTIVO);
		cabina.setCupo(BigDecimal.ONE);
		
		mockRepository.setICabinaMock(cabina);
		
		
		service.setICabinaRepository(mockRepository);
		
		service.validaCabina(0L);
		
	}
	
	
	@Test
	public void testLoginNoCabina() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return null;
			}
		};
		Cabina cabina = new Cabina();
		cabina.setEstado(EstadoCabina.ACTIVO);
		
		//mockRepository.setICabinaMock(cabina);
				
		service.setICabinaRepository(mockRepository);
				
		service.loginCabina(null);
		assertTrue(true);
				
	}
	
	@Test
	public void testLoginErrorNoExisteCabinaAsociada() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		exceptions.expect(CabinaException.class);
        exceptions.expectMessage(CabinaDomainService.NO_HAY_CABINA_ASOCIADA_AL_USUARIO);
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		
		service.setICabinaRepository(mockRepository);
		
		service.loginCabina("CUALQUIERA");
		assertTrue(false);
	}
	
	@Test
	public void testLoginErrorCabinaInactiva() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		exceptions.expect(CabinaException.class);
        exceptions.expectMessage(CabinaDomainService.CABINA_SE_ENCUENTRA_INACTIVA);
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		Cabina cabina = new Cabina();
		cabina.setEstado(EstadoCabina.INACTIVO);
		
		mockRepository.setICabinaMock(cabina);
		
		
		service.setICabinaRepository(mockRepository);
		
		service.loginCabina("CUALQUIERA");
		assertTrue(false);
		
	}
	
	@Test
	public void testLoginErrorCabinaSinCupo() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		exceptions.expect(CabinaException.class);
        exceptions.expectMessage(CabinaDomainService.LA_CABINA_NO_TIENE_CUPO);
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		Cabina cabina = new Cabina();
		cabina.setEstado(EstadoCabina.ACTIVO);
		
		mockRepository.setICabinaMock(cabina);
		
		
		service.setICabinaRepository(mockRepository);
		
		service.loginCabina("CUALQUIERA");
		assertTrue(false);
		
	}
	
	@Test
	public void testLoginCabinaOK() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		Cabina cabina = new Cabina();
		cabina.setEstado(EstadoCabina.ACTIVO);
		cabina.setCupo(BigDecimal.ONE);
		mockRepository.setICabinaMock(cabina);
		
		
		service.setICabinaRepository(mockRepository);
		
		service.loginCabina("CUALQUIERA");
		assertTrue(true);
		
	}
	
	@Test
	public void testLoginCabinaBloqueadaOK() throws CabinaException{
		MockRepository mockRepository = new MockRepository();
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		Cabina cabina = new Cabina();
		cabina.setEstado(EstadoCabina.BLOQUEADA);
		cabina.setCupo(BigDecimal.ONE);
		mockRepository.setICabinaMock(cabina);
		
		
		service.setICabinaRepository(mockRepository);
		
		service.loginCabina("CUALQUIERA");
		
		ICabina iCabina = mockRepository.findOneByUserId(null);
		
		String esperado = EstadoCabina.ACTIVO.name();
		
		String resultado = iCabina.getEstado().name();
		
		assertEquals(esperado, resultado);
		
	}
	
	public static class MockRepository implements ICabinaRepository{

		ICabina cabinaMock;
		
		public void setICabinaMock(ICabina cabina){
			this.cabinaMock = cabina;
		}
		
		@Override
		public ICabina findOneByUserId(Long userId) {
			
			return cabinaMock;
		}

		@Override
		public ICabina save(ICabina cabina) {
			return cabina;
		}
		
		
		
	}
	

}
