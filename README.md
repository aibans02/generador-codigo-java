# Generador de código en java
Generador de código en lenguaje Java a partir de un lenguaje de más alto nivel (creado por los desarrolladores) basado en el lenguaje natural.

## Getting Started
Los comandos que aparecen a continuación se corresponden con una terminal de tipo Bash.

### Prerequisites
Es necesario tener instalado el siguiente entorno para poder ejecutar la App: 

```
-Java JDK 8 o superior
-git
```

### Installing

Escribir en la terminal:

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
```

```
$ java es.unileon.inco.GeneradorCodigo
```
Las nuevas clases se generan dentro de src en el paquete es.unileon.inco, si se quieren testear habría que compilarlas y ejecutarlas como se indicó anteriormente.

## How to use

Tras ejecutar el programa este se quedará a la espera de que introduzcamos una orden:

* **salir**  ->  se producirá el fin de la ejecución del programa.

La sintáxis a utilizar será la siguiente:

**nombreClase/nº_operandos/operacion1(flag_si_se_necesitase)/operacion2**

Las operaciones que por defecto vienen implementadas son Negación, Repetición y Suma.

Los flags admitidos por las operaciones son los siguientes:

* **Suma(-)**  ->  realiza la operación Suma aplicando la operación Negación al segundo operando.
* **Repeticion(0)**  ->  realiza la repetición de la operación 2 operando con el primer operando sobre una variable con valor inicial 0 tantas veces como indica el segundo operando.
* **Repeticion(1)**  ->  realiza la repetición de la operación 2 operando con el primer operando sobre una variable con valor inicial 1 tantas veces como indica el segundo operando.
* **Repeticion(iter)**  ->  realiza la repetición de la operación 2 operando con el número de iteración sobre una variable con valor inicial 1, tantas veces como indique el operando. **Sólo admite un operando**
* **Repeticion(max)**  ->  realiza la repetición de la operación 2 operando con el segundo operando sobre una variable con valor inicial 1 hasta que esta alcance el valor del primer operando. **El resultado obtenido es el nº de iteraciones**
* **Repeticion(tanteo)**  ->  busca el mayor número tal que al operar sobre sí mismo con operación 2 sea menor o igual que el operando. **Sólo admite un operando**

(La operación Repetición siempre debe ir acompañada de un flag)

Si se especifican 3 operandos, el resultado de realizar la primera operación con los dos primeros operandos será utilizado junto al tercer operando para obtener el resultado final.

### Algunos ejemplos que crearían operaciones ya existentes

Cabe mencionar que ciertas combinaciones de operaciones crearían algoritmos que no se corresponden con ninguna operación natural (he aquí la gracia de un generador de código ;) )

**Cuando se citan operaciones que no vienen por defecto estamos suponiendo los nombres que hemos utilizado en los ejemplos**

* **Resta**  ->  resta/2/suma(-)
* **Multiplicación**  ->  multiplicacion/2/repeticion(0)/suma
* **División**  ->  division/2/repeticion(max)/suma
* **Potencia**  ->  potencia/2/repeticion(1)/multiplicacion
* **Factorial**  ->  factorial/1/repeticion(iter)/multiplicacion
* **Porcentaje**  ->  porcentaje/3/multiplicacion/division *(al ejecutar el código generado, el tercer operando sería 100)*
* **Media**  ->  media/3/suma/division *(al ejecutar el código generado, el tercer operando sería 2)*
* **Raíz Cuadrada**  ->  raiz/1/repeticion(tanteo)/multiplicacion


## Otras consideraciones

Es indiferente que se utilicen mayúsculas o minúsculas a la hora de introducir el nombre de las operaciones (**No case-sensitive**)

Este programa sólo trabajo con enteros

## Authors

* **Carlos Fernández San Juan**
* **Armando Ibán Sánchez** 
* **David Pérez Gago**

(Órden alfabético)

## License

This project is licensed under GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details

