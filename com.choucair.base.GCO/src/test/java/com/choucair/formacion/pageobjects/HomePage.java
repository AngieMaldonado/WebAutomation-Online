package com.choucair.formacion.pageobjects;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://10.66.166.14/#!/login")

public class HomePage extends PageObject{

	//Campo usuario 
	@FindBy(id="inputUsuario")
	public WebElementFacade txtUsuario;
	
	//Campo constraseña
	@FindBy(xpath="(.//*[normalize-space(text()) and normalize-space(.)='Contraseña'])[1]/following::input[1]")
	public WebElementFacade txtPassword;
	
	//Botón ingresar 
	@FindBy(id="loginbutton")
	public WebElementFacade btnIngresar;
	
	//Label ingreso online 
	@FindBy(linkText="Picking Online")
	public WebElementFacade lblHomePpal;
	
	//Menú pedidos 
	@FindBy(linkText="Pedidos")
	public WebElementFacade menuPedidos;
	
	//Opción pedidos 
	@FindBy(xpath="//*[@id=\"menuPedidos\"]/ul/li[1]/a")
	public WebElementFacade opcPedidos;

	//Label pedidos 
	@FindBy(xpath="//div[@id='accordion']/div/div/div/div/h3/a")
	public WebElementFacade pagPedidos;
	
	//Menú Reportes
	@FindBy(linkText="Reportes")
	public WebElementFacade menuReportes;
	
	//Opción que genera el reporte de movientos 
	@FindBy(xpath="/html/body/div[2]/nav/div/div[2]/ul[1]/li[4]/ul/li[11]/a")
	public WebElementFacade opcConsultaMovimientos;
	
	//Label formulario para generar reporte de consulta de movimientos  
	@FindBy(xpath="/html/body/div[3]/div[1]/div/form/div/div[1]/h3/a")
	public WebElementFacade pagConsultaMovimientos;
	
	
	/**
	 * @param usu
	 * @param pass
	 */
	public void ingresarDatos(String usu,String pass)
	{
		txtUsuario.sendKeys(usu);
		txtPassword.sendKeys(pass);
		btnIngresar.click();
	}
	
	/**
	 * 
	 */
	public void verificarHome()
	{
		String labelHome="Picking Online";
		String strMensaje=lblHomePpal.getText();
		assertThat(strMensaje,containsString(labelHome));
	}
	
	
	public void ingresarAPedidos()
	{
		waitFor(2).seconds();
		menuPedidos.click();
		waitFor(2).seconds();
		opcPedidos.click();
	}
	
	/**
	 * 
	 */
	public void verificarPagPedidos()
	{
		
		String labelPedidos="Pedidos";
		String strMensaje=pagPedidos.getText();
		waitFor(2).seconds();
		assertThat(strMensaje,containsString(labelPedidos));
	}
	
	public void ingresarAReporteMovimientos()
	{
		waitFor(2).seconds();
		menuPedidos.click();
		waitFor(2).seconds();
		opcPedidos.click();
	}
	
	public void ingresarAConsultaMovimientos()
	{
		waitFor(2).seconds();
		menuPedidos.click();
		waitFor(2).seconds();
		opcConsultaMovimientos.click();
	}
	
	public void verificarPagConsultaMovimientos()
	{
		
		String labelConsMov="Consulta Movimientos";
		String strMensaje=pagConsultaMovimientos.getText();
		waitFor(2).seconds();
		assertThat(strMensaje,containsString(labelConsMov));
	}
	
	
}
