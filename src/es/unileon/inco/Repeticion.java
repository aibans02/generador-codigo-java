package es.unileon.inco;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Repeticion {
	public static int resolver(int op, int n, int inicio, Method operacion)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		for (int i = 0; i < n; i++) {
			inicio = (int) operacion.invoke(null, inicio, op);
		}

		return inicio;
	}

}