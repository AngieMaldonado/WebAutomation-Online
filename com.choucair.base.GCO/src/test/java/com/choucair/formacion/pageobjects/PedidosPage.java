package com.choucair.formacion.pageobjects;

import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.choucair.formacion.models.Pedido;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.choucair.formacion.queries.Pedidos;
import com.choucair.formacion.utilities.ConnectDB;

import static org.hamcrest.CoreMatchers.*;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://10.66.166.14/#!/login")
public class PedidosPage extends PageObject {

  private String numeroPedido;

  // Campo Cliente
  @FindBy(xpath = "//div[@id='collapseOne']/div/div/div/div/div/span")
  public WebElementFacade spancliente;

  // Campo Cliente
  @FindBy(xpath = "//div[@id='collapseOne']/div/div/div/div/input")
  public WebElementFacade txtcliente;

  // Campo Concepto
  @FindBy(xpath = "//div[2]/div/div/span")
  public WebElementFacade spanconcepto;

  // Campo Concepto
  @FindBy(xpath = "//div[@id='collapseOne']/div/div/div[2]/div/input")
  public WebElementFacade txtconcepto;

  // Campo marca
  @FindBy(xpath = "//div[3]/div/div/span/span[2]")
  public WebElementFacade spanmarca;

  // Campo marca
  @FindBy(xpath = "//div[3]/div/input")
  public WebElementFacade txtmarca;

  // Campo transportadora
  @FindBy(xpath = "//*[@id=\"collapseOne\"]/div/div[3]/div[1]/div[2]/div")
  public WebElementFacade spantransportadora;

  // Campo transportadora
  @FindBy(xpath = "//*[@id=\"collapseOne\"]/div/div[3]/div[1]/div[2]/div/input[1]")
  public WebElementFacade txttransportadora;

  // Boton insertar PLU
  @FindBy(xpath = "(//button[@type='button'])[5]")
  public WebElementFacade btnPlu;

  // Ventana emergente para referencia
  @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/h4")
  public WebElementFacade emergenteRef;

  // Campo de referencia
  @FindBy(xpath = "//span[2]")
  public WebElementFacade spanReferencia;

  // Campo de referencia
  @FindBy(xpath = "//input[@type='search']")
  public WebElementFacade lblReferencia;

  // Campo de botón aceptar PLU
  @FindBy(id = "btnAceptarRefsPedido")
  public WebElementFacade bntAceptarPLU;

  // Unidades PLU
  @FindBy(xpath = "/html/body/div[1]/div/div/div[2]/div[2]/div/table")
  public WebElementFacade tblPLU;

  // Región formas de pago
  @FindBy(
      xpath =
          "/html/body/div[3]/div[1]/div/form/div/ng-transclude/div[3]/div/div/uib-accordion/div/div[1]")
  public WebElementFacade formasPago;

  // Campo formas de pago
  @FindBy(xpath = "//td/div/div/span")
  public WebElementFacade spanFormasPago;

  // Campo formas de pago
  @FindBy(xpath = "//td/div/input")
  public WebElementFacade lblFormasPago;

  // Botón guardar pedido
  @FindBy(xpath = "//li[2]/div/button")
  public WebElementFacade btnGuardar;

  // Botón confirmar pedido
  @FindBy(xpath = "//li/div/button")
  public WebElementFacade btnConfirmar;

  // Región confirmacion de pedido
  @FindBy(xpath = "/html/body/div[1]/div/div/div/div[2]/p")
  public WebElementFacade regionConfirmar;

  // Boton para aceptar la confirmacion del pedido creado
  @FindBy(id = "btnAceptarModal")
  public WebElementFacade btnAceptarConfirm;

  // Región con mensaje de confirmación del pedido creado exitosamente
  @FindBy(xpath = "/html/body/div[1]/div/div/div/div[2]/div/p")
  public WebElementFacade msjExitoso;

  // Botón para aceptar transacción exitosa
  @FindBy(xpath = "//*[@id=\"btnAceptarModal\"]")
  public WebElementFacade btnExitoso;

  // Campo numero del pedido creado
  @FindBy(xpath = "//*[@id='inputPedido']")
  public WebElementFacade lblNumPedido;

  // Región con mensaje de validación campo cliente requerido
  @FindBy(xpath = "/html/body/div[1]/div/div/div/div[2]/div/p")
  public WebElementFacade msjValidacionCliente;

  public void presionarEnter() {
    Actions action = new Actions(getDriver());
    action.sendKeys(Keys.ENTER).perform();
  }

  public void presionarTabulador() {
    Actions action = new Actions(getDriver());
    action.sendKeys(Keys.TAB).perform();
  }

  public void ingresarDatosPedidos(
      String cliente, String concepto, String marca, String transportadora) {
    spancliente.click();
    txtcliente.sendKeys(cliente);
    waitFor(5).seconds();
    presionarEnter();
    spanconcepto.click();
    txtconcepto.sendKeys(concepto);
    waitFor(2).seconds();
    presionarEnter();
    spanmarca.click();
    txtmarca.sendKeys(marca);
    waitFor(2).seconds();
    presionarEnter();
    spantransportadora.click();
    txttransportadora.sendKeys(transportadora);
    presionarEnter();
  }

  public void verificarEmergenteRef() {
    String labelReferenciasPed = "Referencias del pedido";
    String strMensaje = emergenteRef.getText();
    waitFor(2).seconds();
    assertThat(strMensaje, containsString(labelReferenciasPed));
  }

  public void agregarPLU(String ref, String PLU, String cantidad) {
    btnPlu.click();
    verificarEmergenteRef();
    spanReferencia.click();
    lblReferencia.sendKeys(ref);
    waitFor(5).seconds();
    presionarEnter();
    cantidadxPLU(PLU, cantidad);
    waitFor(3).seconds();
    bntAceptarPLU.click();
    waitFor(3).seconds();
  }

  public void cantidadxPLU(String PLU, String cantidad) {
    waitFor(3).seconds();
    int registros = getDriver().findElements(By.xpath("//table/tbody/tr")).size();
    for (int i = 1; i < registros; i++) {
      String strPLU = "//table/tbody/tr[" + i + "]/td[2]";

      if (getDriver().findElement(By.xpath(strPLU)).getText().equals(PLU)) {
        String strColumnaCant = "//table/tbody/tr[" + i + "]/td[5]/input";
        getDriver().findElement(By.xpath(strColumnaCant)).sendKeys(cantidad);
        waitFor(3).seconds();
        break;
      }
    }
  }

  /*	public void agregarFormasPago(String formPago)
  {
  	waitFor(3).seconds();
  	formasPago.click();
  	spanFormasPago.click();
  	waitFor(5).seconds();

  	List<WebElement> ddFormPago = getDriver().findElements(By.xpath("//li[@id='ui-select-choices-4']/div"));
         for (int i = 0; i < ddFormPago.size(); i++)
         {	WebElement element =ddFormPago.get(i);
             String elementText = element.getText();


             if (elementText.equals(formPago)) {
                	waitFor(5).seconds();
                 element.click();
                 break;
             }
         }

  }*/

  public void agregarFormasPago(String formaPago) {
    waitFor(3).seconds();
    formasPago.click();
    spanFormasPago.click();
    waitFor(5).seconds();
    getDriver().findElement(By.xpath("//div[contains(text(),'" + formaPago + "')]")).click();
  }

  public void guardarPedido() {
    btnGuardar.click();
    waitFor(10).seconds();
  }

  public void confirmarPedido() {
    btnConfirmar.click();
    waitFor(10).seconds();
    btnAceptarConfirm.click();
    waitFor(5).seconds();
  }

  public void verificarPedidoCreado() {
    btnExitoso.click();
    waitFor(3).seconds();
    almacenarNumPedido();
  }

  public void almacenarNumPedido() {
    numeroPedido = getDriver().findElement(By.id("inputPedido")).getAttribute("value");
    System.out.println(numeroPedido);
  }

  public void verificarPedidoEnBaseDeDatos() {
    /*
     * _Se realiza la consulta de pedido almacenado en la base de datos
     */
    String sqlRespuesta = "";
    try (Connection connection = ConnectDB.getInstance().getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(Pedidos.qryConsultarPedido(numeroPedido)); ) {
      Pedido pedido = new Pedido();
      if (rs.next()) {
        pedido.setNumPedido(rs.getString("num_pedido"));
        pedido.setCodEmpresa(rs.getString("cod_empresa"));
        pedido.setCodConcepto(rs.getString("cod_concepto"));
        // pedido.setCodTercero(rs.getString("cod_tercero"));
        pedido.setCodReferencia(rs.getString("cod_referencia").replaceAll("\\s", ""));
        pedido.setCodPlu(rs.getString("cod_plu"));
      } else {
        Assert.fail("No se encontraron registros en la base de datos");
      }
      Pedido pedidoInicial = Serenity.sessionVariableCalled("pedidoInicial");
      pedidoInicial.setNumPedido(numeroPedido);
      pedidoInicial.setCodEmpresa("841");
      assertThat(pedido.toString(), containsString(pedidoInicial.toString()));
    } catch (SQLException e) {
      Assert.fail(e.getMessage());
    }
  }

  public void verificarCampoCliente() {
    String labelConfirmacion = "El cliente es obligatorio.";
    String strMensaje = msjValidacionCliente.getText();
    assertThat(strMensaje, containsString(labelConfirmacion));
  }
}
