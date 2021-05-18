#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Opcion de reportes
  El usuario debe ingresar al aplicativo y seleccionar el menu de reportes 
  seleccionar el reporte que desea, diligenciar los parametros de busqueda 
  y finalmente generar los reportes y exportar aquellos que tengan la opcion

  @CasoExitoso
  Scenario Outline: Generar y exportar
    el reporte de movimientos de manera exitosa

    Given Autenticar en siconline con usuario <usuario> y contrasena <contrasenia>
    And Ingresar al menú de reportes
    And Seleccionar el reporte de consulta movimientos
    When Ingresar Los parametros de busqueda
      | Fecha inicial | <fechaIni> |
      | Fecha final   | <fechaFin> |
    And presionar el boton exportar excel
    Then verificar que se exporte la información de los movimientos de manera correcta

    Examples: 
      | name  | usuario    | contrasenia | fechaIni     | fechaFin     |
      | caso1 | "sicotest" | "admin2017" | "2019-05-08" | "2019-05-08" |
