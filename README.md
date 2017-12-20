# Generador de código en Java
Generador de código en lenguaje Java a partir de un lenguaje de más alto nivel (creado por los desarrolladores) basado en el lenguaje natural.


## Primeros pasos
Los comandos que aparecen a continuación se corresponden con una terminal de tipo Bash.


### Requisitos
Para seguir las instrucciones siguientes y garantizar un correcto comportamiento son necesarios estos componentes:
* Java JDK 8 o superior
* git


### Preparación

Clonamos el repositorio:

```
$ git clone https://github.com/dpereg06/generador-codigo-java.git
```

Accedemos a la carpeta del repositorio:

```
$ cd generador-codigo-java
```

Creamos una carpeta para los binarios:

```
$ mkdir bin
```

Compilamos:

```
$ javac -d bin src/es/unileon/inco/*.java
```

Finalmente ejecutamos el programa:

```
$ cd bin
$ java es.unileon.inco.GeneradorCodigo
```
Las nuevas clases se generan dentro de src en el paquete es.unileon.inco, si se quieren testear habría que compilarlas y ejecutarlas como se indicó anteriormente.


## Utilización

Tras ejecutar el programa este se quedará a la espera de que introduzcamos una orden:

* **salir**  ->  se producirá el fin de la ejecución del programa.

La sintaxis a utilizar será la siguiente:

**nombreClase/nº_operandos/operacion1(flag_si_se_necesitase)/operacion2**

El parámetro *operacion2* puede no ser necesario.

Las operaciones que por defecto vienen implementadas son Negación, Repetición y Suma.

Los flags admitidos por las operaciones son los siguientes:

* **Suma(-)**  ->  realiza la operación Suma aplicando la operación Negación al segundo operando.
* **Repeticion(0)**  ->  realiza la repetición de la operación 2 operando con el primer operando sobre una variable con valor inicial 0 tantas veces como indica el segundo operando.
* **Repeticion(1)**  ->  realiza la repetición de la operación 2 operando con el primer operando sobre una variable con valor inicial 1 tantas veces como indica el segundo operando.
* **Repeticion(iter)**  ->  realiza la repetición de la operación 2 operando con el número de iteración sobre una variable con valor inicial 1, tantas veces como indique el operando. **Sólo admite un operando**
* **Repeticion(max)**  ->  realiza la repetición de la operación 2 operando con el segundo operando sobre una variable con valor inicial 1 hasta que esta alcance el valor del primer operando. **El resultado obtenido es el nº de iteraciones**
* **Repeticion(tanteo)**  ->  busca el mayor número tal que al operar sobre sí mismo con operación 2 sea menor o igual que el operando. **Sólo admite un operando**

(La operación Repetición siempre debe ir acompañada de un flag)

Si se especifican 3 operandos, el resultado de realizar la primera operación con los dos primeros operandos será utilizado junto al tercer operando para obtener el resultado final aplicando la segunda operación.


### Algunos ejemplos para crear operaciones matemáticas ya existentes

Cabe mencionar que ciertas combinaciones de operaciones crearían algoritmos que no se corresponden con ninguna operación natural (ahí radica también el potencial de un generador de código :wink:)

**Cuando se citan operaciones que no vienen por defecto estamos suponiendo los nombres que hemos utilizado en los ejemplos**

* **Resta**  ->  resta/2/suma(-)
* **Multiplicación**  ->  multiplicacion/2/repeticion(0)/suma
* **División**  ->  division/2/repeticion(max)/suma
* **Potencia**  ->  potencia/2/repeticion(1)/multiplicacion
* **Factorial**  ->  factorial/1/repeticion(iter)/multiplicacion
* **Porcentaje**  ->  porcentaje/3/multiplicacion/division *(al ejecutar el código generado, el tercer operando sería 100)*
* **Media**  ->  media/3/suma/division *(al ejecutar el código generado, el tercer operando sería 2)*
* **Raíz Cuadrada**  ->  raiz/1/repeticion(tanteo)/multiplicacion


## Consideraciones

* Es indiferente que se utilicen mayúsculas o minúsculas a la hora de introducir el nombre de las operaciones (**not case-sensitive**)

* Este programa sólo trabaja con números enteros

* El código se generará en la carpeta src dentro del paquete es.unileon.inco


## Funcionamiento interno

La generación del código para nuevas operaciones a partir de las ya existentes funciona de la siguiente manera:
Se interpreta la entrada del usuario, identificando el número de operandos, las operaciones a combinar y los flags. En función de todo ello, se va construyendo el código de la clase Java con la lectura de los operandos por teclado y las invocaciones pertinentes a las clases de las operaciones creadas anteriormente, guardando resultados parciales en caso de ser necesario. Finalmente, una vez completado el código, se genera el fichero de la clase Java para la nueva operación creada.


## Autores

Han colaborado, por orden alfabético del primer apellido:

* **Carlos Fernández San Juan**
* **Armando Ibán Sánchez** 
* **David Pérez Gago**


## Licencia/License

This project is licensed under GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details
