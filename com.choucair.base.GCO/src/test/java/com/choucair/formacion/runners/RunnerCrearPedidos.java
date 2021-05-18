package com.choucair.formacion.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		glue = { "com.choucair.formacion.definition" },
		features ="src/test/resources/features/SiconlinePedidos/SiconlinePedidos.feature")
public class RunnerCrearPedidos {

}
