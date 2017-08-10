package co.com.ies.adv.backend.cabinas.web.rest;

import co.com.ies.adv.backend.cabinas.BackendCabinasApp;

import co.com.ies.adv.backend.cabinas.domain.Cabina;
import co.com.ies.adv.backend.cabinas.repository.CabinaRepository;
import co.com.ies.adv.backend.cabinas.service.CabinaService;
import co.com.ies.adv.backend.cabinas.service.dto.CabinaDTO;
import co.com.ies.adv.backend.cabinas.service.mapper.CabinaMapper;
import co.com.ies.adv.backend.cabinas.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.com.ies.adv.backend.cabinas.domain.core.entities.ICabina;
import co.com.ies.adv.backend.cabinas.domain.core.enumeration.EstadoCabina;
/**
 * Test class for the CabinaResource REST controller.
 *
 * @see CabinaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendCabinasApp.class)
public class CabinaResourceIntTest {

    private static final String DEFAULT_IDEM = "AAAAAAAAAA";
    private static final String UPDATED_IDEM = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSABLE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_MUNICIPIO = "AAAAAAAAAA";
    private static final String UPDATED_MUNICIPIO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CUPO = new BigDecimal(1);
    private static final BigDecimal UPDATED_CUPO = new BigDecimal(2);

    private static final Long DEFAULT_CONSEGUTIVO = 1L;
    private static final Long UPDATED_CONSEGUTIVO = 2L;

    private static final Double DEFAULT_LONGITUD = 1D;
    private static final Double UPDATED_LONGITUD = 2D;

    private static final Double DEFAULT_LATITUD = 1D;
    private static final Double UPDATED_LATITUD = 2D;

    private static final EstadoCabina DEFAULT_ESTADO = EstadoCabina.ACTIVO;
    private static final EstadoCabina UPDATED_ESTADO = EstadoCabina.INACTIVO;

    private static final String DEFAULT_OBSERVACIONES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACIONES = "BBBBBBBBBB";

    @Autowired
    private CabinaRepository cabinaRepository;

    @Autowired
    private CabinaMapper cabinaMapper;

    @Autowired
    private CabinaService cabinaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCabinaMockMvc;

    private Cabina cabina;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CabinaResource cabinaResource = new CabinaResource(cabinaService);
        this.restCabinaMockMvc = MockMvcBuilders.standaloneSetup(cabinaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cabina createEntity(EntityManager em) {
        Cabina cabina = new Cabina()
            .idem(DEFAULT_IDEM)
            .responsable(DEFAULT_RESPONSABLE)
            .direccion(DEFAULT_DIRECCION)
            .departamento(DEFAULT_DEPARTAMENTO)
            .municipio(DEFAULT_MUNICIPIO)
            .cupo(DEFAULT_CUPO)
            .consegutivo(DEFAULT_CONSEGUTIVO)
            .longitud(DEFAULT_LONGITUD)
            .latitud(DEFAULT_LATITUD)
            .estado(DEFAULT_ESTADO)
            .observaciones(DEFAULT_OBSERVACIONES);
        return cabina;
    }

    @Before
    public void initTest() {
        cabina = createEntity(em);
    }

    @Test
    @Transactional
    public void createCabina() throws Exception {
        int databaseSizeBeforeCreate = cabinaRepository.findAll().size();

        // Create the Cabina
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);
        restCabinaMockMvc.perform(post("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isCreated());

        // Validate the Cabina in the database
        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeCreate + 1);
        ICabina testCabina = cabinaList.get(cabinaList.size() - 1);
        assertThat(testCabina.getIdem()).isEqualTo(DEFAULT_IDEM);
        assertThat(testCabina.getResponsable()).isEqualTo(DEFAULT_RESPONSABLE);
        assertThat(testCabina.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testCabina.getDepartamento()).isEqualTo(DEFAULT_DEPARTAMENTO);
        assertThat(testCabina.getMunicipio()).isEqualTo(DEFAULT_MUNICIPIO);
        assertThat(testCabina.getCupo()).isEqualTo(DEFAULT_CUPO);
        assertThat(testCabina.getConsegutivo()).isEqualTo(DEFAULT_CONSEGUTIVO);
        assertThat(testCabina.getLongitud()).isEqualTo(DEFAULT_LONGITUD);
        assertThat(testCabina.getLatitud()).isEqualTo(DEFAULT_LATITUD);
        assertThat(testCabina.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testCabina.getObservaciones()).isEqualTo(DEFAULT_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void createCabinaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cabinaRepository.findAll().size();

        // Create the Cabina with an existing ID
        cabina.setId(1L);
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCabinaMockMvc.perform(post("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIdemIsRequired() throws Exception {
        int databaseSizeBeforeTest = cabinaRepository.findAll().size();
        // set the field null
        cabina.setIdem(null);

        // Create the Cabina, which fails.
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);

        restCabinaMockMvc.perform(post("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isBadRequest());

        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponsableIsRequired() throws Exception {
        int databaseSizeBeforeTest = cabinaRepository.findAll().size();
        // set the field null
        cabina.setResponsable(null);

        // Create the Cabina, which fails.
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);

        restCabinaMockMvc.perform(post("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isBadRequest());

        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDireccionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cabinaRepository.findAll().size();
        // set the field null
        cabina.setDireccion(null);

        // Create the Cabina, which fails.
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);

        restCabinaMockMvc.perform(post("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isBadRequest());

        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDepartamentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cabinaRepository.findAll().size();
        // set the field null
        cabina.setDepartamento(null);

        // Create the Cabina, which fails.
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);

        restCabinaMockMvc.perform(post("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isBadRequest());

        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMunicipioIsRequired() throws Exception {
        int databaseSizeBeforeTest = cabinaRepository.findAll().size();
        // set the field null
        cabina.setMunicipio(null);

        // Create the Cabina, which fails.
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);

        restCabinaMockMvc.perform(post("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isBadRequest());

        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCupoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cabinaRepository.findAll().size();
        // set the field null
        cabina.setCupo(null);

        // Create the Cabina, which fails.
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);

        restCabinaMockMvc.perform(post("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isBadRequest());

        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsegutivoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cabinaRepository.findAll().size();
        // set the field null
        cabina.setConsegutivo(null);

        // Create the Cabina, which fails.
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);

        restCabinaMockMvc.perform(post("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isBadRequest());

        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cabinaRepository.findAll().size();
        // set the field null
        cabina.setEstado(null);

        // Create the Cabina, which fails.
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);

        restCabinaMockMvc.perform(post("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isBadRequest());

        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCabinas() throws Exception {
        // Initialize the database
        cabinaRepository.saveAndFlush(cabina);

        // Get all the cabinaList
        restCabinaMockMvc.perform(get("/api/cabinas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cabina.getId().intValue())))
            .andExpect(jsonPath("$.[*].idem").value(hasItem(DEFAULT_IDEM.toString())))
            .andExpect(jsonPath("$.[*].responsable").value(hasItem(DEFAULT_RESPONSABLE.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].departamento").value(hasItem(DEFAULT_DEPARTAMENTO.toString())))
            .andExpect(jsonPath("$.[*].municipio").value(hasItem(DEFAULT_MUNICIPIO.toString())))
            .andExpect(jsonPath("$.[*].cupo").value(hasItem(DEFAULT_CUPO.intValue())))
            .andExpect(jsonPath("$.[*].consegutivo").value(hasItem(DEFAULT_CONSEGUTIVO.intValue())))
            .andExpect(jsonPath("$.[*].longitud").value(hasItem(DEFAULT_LONGITUD.doubleValue())))
            .andExpect(jsonPath("$.[*].latitud").value(hasItem(DEFAULT_LATITUD.doubleValue())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].observaciones").value(hasItem(DEFAULT_OBSERVACIONES.toString())));
    }

    @Test
    @Transactional
    public void getCabina() throws Exception {
        // Initialize the database
        cabinaRepository.saveAndFlush(cabina);

        // Get the cabina
        restCabinaMockMvc.perform(get("/api/cabinas/{id}", cabina.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cabina.getId().intValue()))
            .andExpect(jsonPath("$.idem").value(DEFAULT_IDEM.toString()))
            .andExpect(jsonPath("$.responsable").value(DEFAULT_RESPONSABLE.toString()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.departamento").value(DEFAULT_DEPARTAMENTO.toString()))
            .andExpect(jsonPath("$.municipio").value(DEFAULT_MUNICIPIO.toString()))
            .andExpect(jsonPath("$.cupo").value(DEFAULT_CUPO.intValue()))
            .andExpect(jsonPath("$.consegutivo").value(DEFAULT_CONSEGUTIVO.intValue()))
            .andExpect(jsonPath("$.longitud").value(DEFAULT_LONGITUD.doubleValue()))
            .andExpect(jsonPath("$.latitud").value(DEFAULT_LATITUD.doubleValue()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()))
            .andExpect(jsonPath("$.observaciones").value(DEFAULT_OBSERVACIONES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCabina() throws Exception {
        // Get the cabina
        restCabinaMockMvc.perform(get("/api/cabinas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCabina() throws Exception {
        // Initialize the database
        cabinaRepository.saveAndFlush(cabina);
        int databaseSizeBeforeUpdate = cabinaRepository.findAll().size();

        // Update the cabina
        Cabina updatedCabina = cabinaRepository.findOne(cabina.getId());
        updatedCabina
            .idem(UPDATED_IDEM)
            .responsable(UPDATED_RESPONSABLE)
            .direccion(UPDATED_DIRECCION)
            .departamento(UPDATED_DEPARTAMENTO)
            .municipio(UPDATED_MUNICIPIO)
            .cupo(UPDATED_CUPO)
            .consegutivo(UPDATED_CONSEGUTIVO)
            .longitud(UPDATED_LONGITUD)
            .latitud(UPDATED_LATITUD)
            .estado(UPDATED_ESTADO)
            .observaciones(UPDATED_OBSERVACIONES);
        CabinaDTO cabinaDTO = cabinaMapper.toDto(updatedCabina);

        restCabinaMockMvc.perform(put("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isOk());

        // Validate the Cabina in the database
        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeUpdate);
        ICabina testCabina = cabinaList.get(cabinaList.size() - 1);
        assertThat(testCabina.getIdem()).isEqualTo(UPDATED_IDEM);
        assertThat(testCabina.getResponsable()).isEqualTo(UPDATED_RESPONSABLE);
        assertThat(testCabina.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testCabina.getDepartamento()).isEqualTo(UPDATED_DEPARTAMENTO);
        assertThat(testCabina.getMunicipio()).isEqualTo(UPDATED_MUNICIPIO);
        assertThat(testCabina.getCupo()).isEqualTo(UPDATED_CUPO);
        assertThat(testCabina.getConsegutivo()).isEqualTo(UPDATED_CONSEGUTIVO);
        assertThat(testCabina.getLongitud()).isEqualTo(UPDATED_LONGITUD);
        assertThat(testCabina.getLatitud()).isEqualTo(UPDATED_LATITUD);
        assertThat(testCabina.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testCabina.getObservaciones()).isEqualTo(UPDATED_OBSERVACIONES);
    }

    @Test
    @Transactional
    public void updateNonExistingCabina() throws Exception {
        int databaseSizeBeforeUpdate = cabinaRepository.findAll().size();

        // Create the Cabina
        CabinaDTO cabinaDTO = cabinaMapper.toDto(cabina);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCabinaMockMvc.perform(put("/api/cabinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cabinaDTO)))
            .andExpect(status().isCreated());

        // Validate the Cabina in the database
        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCabina() throws Exception {
        // Initialize the database
        cabinaRepository.saveAndFlush(cabina);
        int databaseSizeBeforeDelete = cabinaRepository.findAll().size();

        // Get the cabina
        restCabinaMockMvc.perform(delete("/api/cabinas/{id}", cabina.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cabina> cabinaList = cabinaRepository.findAll();
        assertThat(cabinaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cabina.class);
        Cabina cabina1 = new Cabina();
        cabina1.setId(1L);
        Cabina cabina2 = new Cabina();
        cabina2.setId(cabina1.getId());
        assertThat(cabina1).isEqualTo(cabina2);
        cabina2.setId(2L);
        assertThat(cabina1).isNotEqualTo(cabina2);
        cabina1.setId(null);
        assertThat(cabina1).isNotEqualTo(cabina2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CabinaDTO.class);
        CabinaDTO cabinaDTO1 = new CabinaDTO();
        cabinaDTO1.setId(1L);
        CabinaDTO cabinaDTO2 = new CabinaDTO();
        assertThat(cabinaDTO1).isNotEqualTo(cabinaDTO2);
        cabinaDTO2.setId(cabinaDTO1.getId());
        assertThat(cabinaDTO1).isEqualTo(cabinaDTO2);
        cabinaDTO2.setId(2L);
        assertThat(cabinaDTO1).isNotEqualTo(cabinaDTO2);
        cabinaDTO1.setId(null);
        assertThat(cabinaDTO1).isNotEqualTo(cabinaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cabinaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cabinaMapper.fromId(null)).isNull();
    }
}
