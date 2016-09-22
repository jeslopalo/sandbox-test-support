package es.sandbox.test.asserts;

import es.sandbox.test.asserts.MethodAssert.OngoingMethodAssert;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by jeslopalo on 20/9/16.
 */
public final class ParameterAssertions {

    /**
     * @param constructor
     * @return
     */
    public static ConstructorAssert assertThat(final Constructor constructor) {
        return new ConstructorAssert(constructor);
    }

    /**
     * @param method
     * @return
     */
    public static <T> OngoingMethodAssert<T> assertThat(final Method method) {
        return new OngoingMethodAssert<>(method);
    }

    private ParameterAssertions() {
        throw new UnsupportedOperationException();
    }
}
