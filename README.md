SPRING BOOT- PROYECTO FINAL (HOMEBANKING)

Sistema de simulación de acciones de un homebanking denominado "spring Bank" con implementaciones CRUD y conexiones a base de datos.
Se formó un grupo de 4 desarrolladores, los cuales se dividieron las tareas para poder lograr el correcto funcionamiento del proyecto.

El proyecto se realizó en Java 11/17 con Maven. Se realizó una conexión a la base de datos mediante JDBC y se implementó Spring JPA e Hibernate para el manejo de la persistencia.

CONSIGNA PRINCIPAL A REALIZAR

- CONTRASEÑAS SEGURAS:
  Se deberá implementar un algoritmo de encriptación en el momento de la creación de la contraseña. Al momento de obtener los datos del usuario, no se debe mostrar la contraseña. Se debe adicionar un endpoint de cambio de contraseña que debe recibir la contraseña sin encriptar, la nueva contraseña, encriptar la nueva y reemplazarla por la anterior para almacenar la nueva encriptada en la DB.

  -----------------------------------

Consignas extras realizadas:

- Validaciones Avanzadas:
	Realizar validaciones avanzadas en la entrada de datos para asegurar la integridad y consistencia de la información.

- Función “Cajero Express”:
	Añadir la funcionalidad de depositar y extraer dinero a la cuenta desde un endpoint nuevo. (Se debe acceder por usuario).

Otros:

-Asignación automática de CBU y alias.
-Nuevos endpoints para consultas de transferencias emitidas y recibidas por cuenta.
