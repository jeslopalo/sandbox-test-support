package es.sandbox.test.asserts;

import java.lang.reflect.Constructor;

/**
 * Created by jeslopalo on 20/9/16.
 */
public final class ParameterAssertions {

    /**
     * @param constructor
     * @param <T>
     * @return
     */
    public static <T> ConstructorAssert<T> assertThat(final Constructor<T> constructor) {
        return new ConstructorAssert<>(constructor);
    }

    private ParameterAssertions() {
        throw new UnsupportedOperationException();
    }
}
