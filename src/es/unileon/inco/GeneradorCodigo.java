package es.unileon.inco;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * Programa que, a través de un lenguaje de alto nivel, genera código java de
 * operaciones definidas por el usuario. Se utilizan unas operaciones básicas
 * como inicio
 * 
 * @author Carlos Fernández San Juan
 * @author Armando Ibán Sánchez
 * @author David Pérez Gago
 */

public class GeneradorCodigo {

	/**
	 * Método main que llama al lector de comandos
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		leerComando();

	}

	/**
	 * Este método lee la entrada por teclado del usuario. Procesa su contenido,
	 * escribe en el buffer donde se encuentra el código y llama al creador del
	 * fichero
	 */

	public static void leerComando() {
		String entradaUsuario = "";
		String[] split;
		StringBuilder codigo;

		Scanner sc = new Scanner(System.in);

		entradaUsuario = sc.nextLine().trim().toLowerCase();

		while (!entradaUsuario.equals("salir")) {
			try {

				split = entradaUsuario.split("/");

				split[0] = formatoClase(split[0]);
				split[2] = formatoClase(split[2]);

				codigo = crearCodigoComun(split);

				if (split.length == 4) {
					split[3] = formatoClase(split[3]);
					if (split[2].contains("Repeticion")) {
						String flag = split[2].substring(split[2].indexOf("(") + 1, split[2].indexOf(")"));

						switch (flag) {
						case "0":
							codigo.append("\t\tMethod metodo = " + split[3]
									+ ".class.getMethod(\"resolver\", int.class, int.class);\n");
							codigo.append("\t\treturn Repeticion.resolver(op1,op2,0,metodo);\n\t}\n\n}");
							break;
						case "1":
							codigo.append("\t\tMethod metodo = " + split[3]
									+ ".class.getMethod(\"resolver\", int.class, int.class);\n");
							codigo.append("\t\treturn Repeticion.resolver(op1,op2,1,metodo);\n\t}\n\n}");
							break;
						case "iter":
							codigo.append("\t\tMethod metodo = " + split[3]
									+ ".class.getMethod(\"resolver\", int.class, int.class);\n");
							codigo.append("\t\treturn RepeticionIter.resolver(op1,metodo);\n\t}\n\n}");
							break;
						case "max":
							codigo.append("\t\tMethod metodo = " + split[3]
									+ ".class.getMethod(\"resolver\", int.class, int.class);\n");
							codigo.append("\t\treturn RepeticionMax.resolver(op1,op2,metodo);\n\t}\n\n}");
							break;
						case "tanteo":
							codigo.append("\t\tMethod metodo = " + split[3]
									+ ".class.getMethod(\"resolver\", int.class, int.class);\n");
							codigo.append("\t\treturn RepeticionTanteo.resolver(op1,metodo);\n\t}\n\n}");
							break;
						default:
							throw new IllegalArgumentException("El flag introducido no es válido\n");

						}

					} else {
						codigo.append("\t\tMethod metodo1 = " + split[2]
								+ ".class.getMethod(\"resolver\", int.class, int.class);\n");
						codigo.append("\t\tMethod metodo2 = " + split[3]
								+ ".class.getMethod(\"resolver\", int.class, int.class);\n");
						codigo.append("\t\tint resulParcial = " + split[2] + ".resolver(op1,op2);\n");
						codigo.append("\t\treturn " + split[3] + ".resolver(resulParcial,op3);\n\t}\n\n}");
					}
				}

				else if (split.length == 3) {
					if (split[2].equals("Suma(-)")) {
						codigo.append("return Suma.resolver(op1,Negacion.resolver(op2));\n}\n\n}");

					} else
						throw new Exception();

				} else
					throw new Exception();

				crearFichero(split, codigo);

			} catch (Exception e) {
				throw new IllegalArgumentException(
						"La sintaxis debe ser la siguiente -> <Nombre>/<NºOperandos>/<Operacion1>/<Operacion2(opcional)>\nConsúltese la documentación para más detalles sobre la sintaxis y los flags");
			}

			entradaUsuario = sc.nextLine().trim().toLowerCase();
		}

	}

	/**
	 * Método que sirve para dar formato a la entrada del usuario
	 * 
	 * @param arg
	 * @return String
	 */

	public static String formatoClase(String arg) {

		return arg.substring(0, 1).toUpperCase() + arg.substring(1);
	}

	/**
	 * Método que crea el fichero de la clase .java a la operación
	 * correspondiente
	 * 
	 * @param args
	 * @param codigo
	 */

	public static void crearFichero(String[] args, StringBuilder codigo) {
		String rutaBin = GeneradorCodigo.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		File fichero = new File(
				rutaBin.substring(0, rutaBin.indexOf("/bin")) + "/src/es/unileon/inco/" + args[0] + ".java");

		try {
			Files.write(fichero.toPath(), codigo.toString().getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que escribe el el buffer del código la parte común a todas las
	 * clases que van a ser generadas.
	 * 
	 * @param args
	 * @return StringBuilder
	 */

	public static StringBuilder crearCodigoComun(String[] args) {
		StringBuilder codigo = new StringBuilder();

		// CABECERA DE LA CLASE Y FUNCIÓN MAIN
		codigo.append(
				"package es.unileon.inco;\n\nimport java.util.Scanner;\nimport java.lang.reflect.Method;\nimport java.lang.reflect.InvocationTargetException;\n\n\n");
		codigo.append("public class " + args[0] + "{\n\n");
		codigo.append("\tpublic static void main(String[] args){\n");
		codigo.append("\t\tScanner sc= new Scanner(System.in);\n");

		if (Integer.parseInt(args[1]) == 1) {
			codigo.append("\t\tint operando1;\n");
			codigo.append("\t\tSystem.out.println(\"Operando1 = \");\n");
			codigo.append("\t\toperando1 = Integer.parseInt(sc.next());\n");
			codigo.append("\t\tsc.close();\n\t\ttry {\n");
			codigo.append(
					"\t\t\tSystem.out.println(\"Resultado = \"+ resolver(operando1)+\"\\n\");\n\t\t} catch (Exception e) {\n\t\t"
							+ "	e.printStackTrace();\n\t\t}\n\t}\n\n");

			// DECLARACIÓN MÉTODO RESOLVER
			codigo.append(
					"\tpublic static int resolver(int op1) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {\n");

		}

		else if (Integer.parseInt(args[1]) == 2) {
			codigo.append("\t\tint operando1,operando2;\n");
			codigo.append("\t\tSystem.out.println(\"Operando1 = \");\n");
			codigo.append("\t\toperando1 = Integer.parseInt(sc.next());\n");
			codigo.append("\t\tSystem.out.println(\"Operando2 = \");\n");
			codigo.append("\t\toperando2 = Integer.parseInt(sc.next());\n");
			codigo.append("\t\tsc.close();\n\t\ttry {\n");
			codigo.append(
					"\t\t\tSystem.out.println(\"Resultado = \"+ resolver(operando1,operando2)+\"\\n\");\n\t\t} catch (Exception e) {\n\t\t"
							+ "	e.printStackTrace();\n\t\t}\n\t}\n\n");

			// DECLARACIÓN MÉTODO RESOLVER
			codigo.append(
					"\tpublic static int resolver(int op1, int op2) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {\n");
		}

		else {
			codigo.append("\t\tint operando1,operando2,operando3;\n");
			codigo.append("\t\tSystem.out.println(\"Operando1 = \");\n");
			codigo.append("\t\toperando1 = Integer.parseInt(sc.next());\n");
			codigo.append("\t\tSystem.out.println(\"Operando2 = \");\n");
			codigo.append("\t\toperando2 = Integer.parseInt(sc.next());\n");
			codigo.append("\t\tSystem.out.println(\"Operando3 = \");\n");
			codigo.append("\t\toperando3 = Integer.parseInt(sc.next());\n");
			codigo.append("\t\tsc.close();\n\t\ttry {\n");
			codigo.append(
					"\t\t\tSystem.out.println(\"Resultado = \"+ resolver(operando1,operando2,operando3)+\"\\n\");\n\t\t} catch (Exception e) {\n\t\t"
							+ "	e.printStackTrace();\n\t\t}\n\t}\n\n");

			// DECLARACIÓN MÉTODO RESOLVER
			codigo.append(
					"\tpublic static int resolver(int op1, int op2, int op3) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {\n");
		}

		return codigo;
	}
}
