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
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.ErroresCabina;
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
	public void testValidaErrorNoExisteCabinaAsociada(){
		MockRepository mockRepository = new MockRepository();
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		
		service.setICabinaRepository(mockRepository);
		
		try {
			service.validaCabina(0L);
			assert(false);
		} catch (CabinaException e) {
			final String enumErrorResult = e.getEnumError().code();
			final String enumErrorExpect = ErroresCabina.NO_HAY_CABINA_ASOCIADA_AL_USUARIO.code();
			
			assertEquals(enumErrorExpect, enumErrorResult);
			
		}
		
		
	}
	
	@Test
	public void testValidaErrorCabinaInactiva() {
		MockRepository mockRepository = new MockRepository();
		
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
		
		try {
			service.validaCabina(0L);
			assert(false);
		} catch (CabinaException e) {
			final String enumErrorResult = e.getEnumError().code();
			final String enumErrorExpect = ErroresCabina.CABINA_SE_ENCUENTRA_INACTIVA.code();
			
			assertEquals(enumErrorExpect, enumErrorResult);
			
		}
		
	}
	
	@Test
	public void testValidaErrorCabinaSinCupo() throws CabinaException{
		MockRepository mockRepository = new MockRepository();

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
		
		try {
			service.validaCabina(0L);
			assert(false);
		} catch (CabinaException e) {
			final String enumErrorResult = e.getEnumError().code();
			final String enumErrorExpect = ErroresCabina.LA_CABINA_NO_TIENE_CUPO.code();
			
			assertEquals(enumErrorExpect, enumErrorResult);
			
		}
		
	}
	
	@Test
	public void testValidaCabinaOk(){
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
		
		try {
			service.validaCabina(0L);
			assert(true);
		} catch (CabinaException e) {
			e.printStackTrace();
		}
		
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
	public void testLoginErrorNoExisteCabinaAsociada(){
		MockRepository mockRepository = new MockRepository();
		
		CabinaDomainService service = new CabinaDomainService() {
			
			@Override
			public Long getUserCabinaId(String login) {
				return 1l;
			}
		};
		
		service.setICabinaRepository(mockRepository);
		
		
		
		try {
			service.loginCabina("CUALQUIERA");
			assert(false);
		} catch (CabinaException e) {
			System.out.println("CabinaDomainServiceTest.java::testLoginErrorNoExisteCabinaAsociada::e"+e.getMessage());
			final String enumErrorResult = e.getEnumError().code();
			final String enumErrorExpect = ErroresCabina.NO_HAY_CABINA_ASOCIADA_AL_USUARIO.code();
			
			assertEquals(enumErrorExpect, enumErrorResult);
			
		}
		
	}
	
	@Test
	public void testLoginErrorCabinaInactiva(){
		MockRepository mockRepository = new MockRepository();
		
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
		
		try {
			service.loginCabina("CUALQUIERA");
			assert(false);
		} catch (CabinaException e) {
			final String enumErrorResult = e.getEnumError().code();
			final String enumErrorExpect = ErroresCabina.CABINA_SE_ENCUENTRA_INACTIVA.code();
			
			assertEquals(enumErrorExpect, enumErrorResult);
			
		}
		
	}
	
	@Test
	public void testLoginErrorCabinaSinCupo(){
		MockRepository mockRepository = new MockRepository();
		
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
		
		try {
			service.loginCabina("CUALQUIERA");
			assert(false);
		} catch (CabinaException e) {
			final String enumErrorResult = e.getEnumError().code();
			final String enumErrorExpect = ErroresCabina.LA_CABINA_NO_TIENE_CUPO.code();
			
			assertEquals(enumErrorExpect, enumErrorResult);
			
		}
		
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
