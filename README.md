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

Explain how to run the automated tests for this system


## Authors

* **David Pérez Gago** 
* **Armando Ibán Sánchez** 
* **Carlos Fernández San Juan** 

## License

This project is licensed under GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details

