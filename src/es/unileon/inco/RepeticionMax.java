package es.unileon.inco;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RepeticionMax {
	public static int resolver(int maximo, int operando, Method operacion)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int variable = 0;
		int i = 1;
		while (variable <= maximo) {
			variable = (int) operacion.invoke(null, variable, operando);
			i++;
		}
		return i - 2;
	}
}