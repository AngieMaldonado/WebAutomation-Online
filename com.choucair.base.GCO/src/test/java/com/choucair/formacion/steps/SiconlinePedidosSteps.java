package com.choucair.formacion.steps;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import com.choucair.formacion.pageobjects.HomePage;
import com.choucair.formacion.pageobjects.PedidosPage;

public class SiconlinePedidosSteps {

  HomePage homePage;
  PedidosPage pedidosPage;

  public void loginSiconline(String usu, String pass) {
    homePage.open();
    homePage.ingresarDatos(usu, pass);
    homePage.verificarHome();
  }

  public void paginaPedidos() {
    homePage.ingresarAPedidos();
    homePage.verificarPagPedidos();
  }

  public void ingresarDatosPedidos(
      String cliente,
      String concepto,
      String marca,
      String transportadora,
      String ref,
      String PLU,
      String cantidad,
      String formaPag) {
    pedidosPage.ingresarDatosPedidos(cliente, concepto, marca, transportadora);
    pedidosPage.agregarPLU(ref, PLU, cantidad);
    pedidosPage.agregarFormasPago(formaPag);
  }

  public void guardarPedidoCreado() {
    pedidosPage.guardarPedido();
  }

  public void confirmarPedidoCreado() {
    pedidosPage.confirmarPedido();
  }

  public void verificarPedidoCreado() {
    String labelConfirmacion = "Pedido confirmado exitosamente.";
    String strMensaje = pedidosPage.msjExitoso.getText();
    assertThat(strMensaje, containsString(labelConfirmacion));
    pedidosPage.verificarPedidoCreado();
  }

  public void verificarCampoCliente() {
    pedidosPage.verificarCampoCliente();
  }

  public void verificarPedidoEnBaseDeDatos() {
    pedidosPage.verificarPedidoEnBaseDeDatos();
  }
}
