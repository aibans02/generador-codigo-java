package es.unileon.inco;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RepeticionIter {
	public static int resolver(int n, Method operacion)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		int inicio = 1;
		for (int i = 0; i < n; i++) {
			inicio = (int) operacion.invoke(null, inicio, i + 1);
		}

		return inicio;
	}
}