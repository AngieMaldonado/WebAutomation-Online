package com.choucair.formacion.definition;

import static com.choucair.formacion.utilities.SessionVariables.PASSWORD;
import static com.choucair.formacion.utilities.SessionVariables.USER;

import java.util.List;

import com.choucair.formacion.models.Pedido;
import com.choucair.formacion.steps.SiconlinePedidosSteps;
import com.choucair.formacion.utilities.EncryptCredentials;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class SiconlinePedidosDefinition {

  @Steps SiconlinePedidosSteps pedidosSteps;
  private EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

  @Given("^Autenticar en siconline con usuario de prueba$")
  public void autenticar_en_siconline_con_usuario_sicotest_y_contraseña_admin() {
    pedidosSteps.loginSiconline(
        EncryptCredentials.decrypt(variables.getProperty(USER.getValue())),
        EncryptCredentials.decrypt(variables.getProperty(PASSWORD.getValue())));
  }

  @Given("^Ingresar al formulario de pedidos$")
  public void ingresar_al_formulario_de_pedidos() {
    pedidosSteps.paginaPedidos();
  }

  @When("^Diligenciar cada campo del formulario de pedidos$")
  public void diligenciar_cada_campo_del_formulario_de_pedidos(DataTable tabla) {
    String cliente, concepto, marca, transportadora, ref, plu, cantidad, formaPago;
    List<List<String>> datos = tabla.raw();

    cliente = datos.get(0).get(1);
    concepto = datos.get(1).get(1);
    marca = datos.get(2).get(1);
    transportadora = datos.get(3).get(1);
    ref = datos.get(4).get(1);
    plu = datos.get(5).get(1);
    cantidad = datos.get(6).get(1);
    formaPago = datos.get(7).get(1);

    Pedido pedidoACrear = new Pedido();
    //pedidoACrear.setCodTercero(datos.get(0).get(1));
    pedidoACrear.setCodPlu(datos.get(5).get(1));
    pedidoACrear.setCodConcepto(datos.get(1).get(1));
    pedidoACrear.setCodReferencia(datos.get(4).get(1));
    Serenity.setSessionVariable("pedidoInicial").to(pedidoACrear);

    pedidosSteps.ingresarDatosPedidos(
        cliente, concepto, marca, transportadora, ref, plu, cantidad, formaPago);
  }

  @When("^Presionar el botón para guardar pedido$")
  public void presionar_el_botón_para_guardar_pedido() {
    pedidosSteps.guardarPedidoCreado();
  }

  @When("^Presionar botón para verificar la creación del pedido$")
  public void presionar_botón_para_verificar_la_creación_del_pedido() {
    pedidosSteps.confirmarPedidoCreado();
  }

  @Then("^Verificar la creacion de los pedidos en siconline$")
  public void verificar_la_creacion_de_los_pedidos_en_siconline() {
    pedidosSteps.verificarPedidoCreado();
  }

  @Then("^Verificar que el sistema muestre un mensaje de obligatoriedad$")
  public void verificar_que_el_sistema_muestre_un_mensaje_de_obligatoriedad() {
    pedidosSteps.verificarCampoCliente();
  }

  @Then("^Verificar que el pedido sea almacenado en la base de datos$")
  public void VerificarQueElpedidoSeaAlmacenadoEnLaBaseDeatos() {
    pedidosSteps.verificarPedidoEnBaseDeDatos();
  }
}
