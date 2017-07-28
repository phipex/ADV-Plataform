package co.com.ies.adv.backend.cabinas.cucumber.stepdefs;

import co.com.ies.adv.backend.cabinas.BackendCabinasApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = BackendCabinasApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
