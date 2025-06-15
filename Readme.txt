Nombre del juego: Mini Granja Virtual

Autoría: María Rodríguez García , Antia Pardo Jiménez y Mara Delgado Ciruela

Breve descripción del funcionamiento e instrucciones para ejecutarlo
Descripción:
En Mini Granja Virtual, tu objetivo es cuidar a una vaca alimentándola correctamente para poder recolectar leche. 
Luego podrás intercambiar esa leche por monedas, y usar esas monedas para comprar más alimento. 
¡Gestiona bien tus recursos para mantener a tu vaca feliz y productiva!

Instrucciones:

	Configuración Inicial
	Leche: 0
	Comida: 10
	Monedas: 50

	Paso 1: Alimentar a la Vaca

	- Haz clic en el botón "Alimentar Vaca".
 	- Cada clic consume 1 unidad de comida y aumenta la barra de progreso.
 	- Cuando la barra llegue a 10, aparecerá el mensaje:
 	- "La vaca está lista para ser ordeñada".
 	- Si no hay comida:
  		- Aparecerá "Comida insuficiente".
  		- Deberás ir a la Tienda para conseguir más.



	Paso 2:Recolectar Leche

 	- Haz clic en "Recolectar Leche".
 	- Tu marcador de leche aumentará a 1.
 	- La barra de progreso se reiniciará a 0.


	Paso 3:Vender Leche en la Tienda

 	- Entra a la Tienda (botón "Tienda").
 	- Haz clic en "Cambiar 1 leche por 10 monedas".
 	- Perderás 1 leche.
 	- Ganarás 10 monedas.

	Paso 4:Comprar Comida
 	- En la misma tienda, haz clic en "Cambiar 1 moneda por 10 de alimento".
 	- Perderás 1 moneda.
 	- Ganarás 10 unidades de comida.


	Paso 5:Paso 5: Continuar o Reiniciar

 	- Cierra la ventana de la tienda.
 	- Elige una opción:
	 	- Sigue alimentando a la vaca para acumular más leche.
	 	- Reinicia la partida si deseas empezar desde cero.




Herramientas utilizadas:

-eclipse, MySQL





Justificación del método de persistencia elegido:

Método de persistencia de datos: SQL
Conocimiento previo y experiencia:
Durante el curso hemos trabajado con bases de datos relacionales, realizando prácticas con SQL 
y familiarizándonos con conceptos como tablas, relaciones, claves primarias y foráneas, consultas, 
inserciones, actualizaciones, etc. Esto nos ha proporcionado una base sólida para implementar una solución robusta y eficiente.

Estructura de los datos:
Los datos que maneja nuestra aplicación tienen una estructura bien definida y relaciones claras entre entidades.
SQL es ideal para este tipo de datos gracias a su capacidad para establecer relaciones entre tablas mediante claves externas y garantizar la integridad referencial.

Escalabilidad y mantenimiento:
Las bases de datos relacionales son ampliamente utilizadas en el entorno profesional y cuentan con herramientas de gestión, optimización y respaldo. 
Esto las convierte en una opción más escalable y mantenible a largo plazo.

Seguridad y control:
SQL ofrece mecanismos de control de acceso, validación de datos y restricciones, lo que permite mantener la integridad y seguridad de la información almacenada.
En resumen, elegimos SQL como sistema de persistencia por su adecuación al tipo de datos, nuestra experiencia previa y las ventajas que ofrece en cuanto a organización, 
consultas, integridad y mantenimiento.

