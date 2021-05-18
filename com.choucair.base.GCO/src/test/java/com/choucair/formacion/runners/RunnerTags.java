package com.choucair.formacion.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
//@CucumberOptions (features = "src/test/resources/features/", tags = "@SmokeTest")
@CucumberOptions (features = {"src/test/resources/features/SiconlinePedidos/SiconlinePedidos.feature"}) 
				//	tags = {"@CasoExitoso,@CasoFallidoClienteObligatorio"})

//@CucumberOptions (features = {"src/test/resources/features/SiconlineReportes/SiconlineReportes.feature"}, 
//tags = {"@CasoExitoso"})
//@CucumberOptions ("src/test/resources/features/SiconlinePedidos/SiconlinePedidos.feature")
public class RunnerTags {

}
