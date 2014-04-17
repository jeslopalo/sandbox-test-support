package es.sandbox.test.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.ClassUtils;


public class ReflectionInvoker {

	/**
	 * Try to invoke the default constructor even if it is private
	 * 
	 * @param <T>
	 * @param type
	 * @param exceptionType
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static final <T> T privateDefaultConstructor(Class<T> type, Class<? extends Throwable> exceptionType)
			throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {

		try {
			final Constructor<T> constructor= type.getDeclaredConstructor();
			constructor.setAccessible(true);
			return constructor.newInstance();
		}
		catch (final InvocationTargetException e) {
			if (ClassUtils.isAssignable(e.getTargetException().getClass(), exceptionType)) {
				throw RuntimeException.class.cast(e.getTargetException());
			}
			throw e;
		}
	}

	public static final <T> T constructor(Class<T> type, Class<?> parameterTypes, Object... initargs)
			throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {

		final Constructor<T> constructor= type.getDeclaredConstructor(parameterTypes);
		return constructor.newInstance(initargs);
	}
}
