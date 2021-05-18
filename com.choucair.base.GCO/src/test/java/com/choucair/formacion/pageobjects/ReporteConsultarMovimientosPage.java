package com.choucair.formacion.pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://10.66.166.14/#!/login")

public class ReporteConsultarMovimientosPage extends PageObject {
	
	//Campo fecha inicial 
	@FindBy(id="divFecIniConsultaFac")
	public WebElementFacade fechaInicial;
	
	//Campo fecha final
	@FindBy(id="divFecFinConsultaFac")
	public WebElementFacade  fechaFinal;

}
