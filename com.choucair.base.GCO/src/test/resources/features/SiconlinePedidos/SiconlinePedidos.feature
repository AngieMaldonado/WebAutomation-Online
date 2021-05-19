@Regresion
Feature: Formulario de pedido
  El usuario debe ingresar al aplicativo y en el formulario de pedidos
  realizar el ingreso y registrar los pedidos en siconline.
  Se debe verificar la obligatoriedad de los campos.|

  @CasoExitoso
  Scenario Outline: Diligenciar de manera correcta la información
  para la creación adecuada de pedidos en siconline

    Given Autenticar en siconline con usuario de prueba
    And Ingresar al formulario de pedidos
    When Diligenciar cada campo del formulario de pedidos
      | Cliente de la factura   | <cliente>        |
      | Concepto                | <concepto>       |
      | Marca de la compra      | <marca>          |
      | Transportadora          | <transportadora> |
      | Referencia seleccionada | <ref>            |
      | PLU                     | <PLU>            |
      | Cantidad PLU            | <cantPLU>        |
      | Forma de pago           | <formaPag>       |
    And Presionar el botón para guardar pedido
    And Presionar botón para verificar la creación del pedido
    Then Verificar la creacion de los pedidos en siconline
    And Verificar que el pedido sea almacenado en la base de datos

    Examples:
      | name  | cliente             | concepto | marca | transportadora | ref      | PLU           | cantPLU | formaPag          |
      | caso1 | monicams@gco.com.co | 100      | 03    | coordinadora   | 87056318 | 8445306703545 | 2       | 2 - VISA DATAFONO |

#  @CasoFallidoClienteObligatorio
#  Scenario Outline: Diligenciar de manera incorrecta la información
#    			para la validación de campos en la creación de pedidos en siconline
#
#    Given Autenticar en siconline con usuario de prueba
#    And Ingresar al formulario de pedidos
#    When Presionar el botón para guardar pedido
#    Then Verificar que el sistema muestre un mensaje de obligatoriedad
#
#    Examples:
#      | name  |
#      | caso1 |
