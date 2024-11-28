Feature: Iniciar sesión en el sistema de la clínica
    Como médico
    Quiero iniciar sesión en el sistema de la clínica
    Para poder visualizar las historias clínicas de mis pacientes y crear nuevas evoluciones

  Background: Existe un médico y un recepcionista registrados en el sistema
    Given existen los siguientes usuarios en el sistema:
      | nombre     | apellido       | cuil         | email               | contrasenia | rol           |
      | Juan       | Perez          | 20123456789  | medico1@example.com | password    | MEDICO        |
      | Pablo      | Caceres        | 10123456788  | recep1@example.com  | muySeguro   | RECEPCIONISTA |

  Scenario:
    When el usuario intenta ingresar al sistema con el email "medico1@example.com" y contraseña "password"
    Then el usuario obtiene acceso al sistema con el rol "MEDICO"
    And el cuil del usuario que ha iniciado sesión es 20123456789

  Scenario:
    When el usuario intenta ingresar al sistema con el email "recep1@example.com" y contraseña "muySeguro"
    Then el usuario obtiene acceso al sistema con el rol "RECEPCIONISTA"
    And el cuil del usuario que ha iniciado sesión es 10123456788

  Scenario:
    When el usuario intenta ingresar al sistema con el email "medico1@example.com" y contraseña "contraseñaIncorrecta"
    Then el médico no obtiene acceso al sistema y se indica que las credenciales son incorrectas

  Scenario:
    When el usuario intenta ingresar al sistema con el email "emailincorrecto@example.com" y contraseña "password"
    Then el médico no obtiene acceso al sistema y se indica que las credenciales son incorrectas
