package es.unileon.inco;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RepeticionTanteo {

	public static int resolver(int maximo, Method operacion)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int i = 1;
		while ((int) operacion.invoke(null, i, i) <= maximo) {
			i++;
		}
		return i - 1;
	}
}