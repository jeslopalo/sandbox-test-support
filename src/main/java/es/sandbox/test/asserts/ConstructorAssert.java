package es.sandbox.test.asserts;

import java.lang.reflect.Constructor;

/**
 * Created by jeslopalo on 18/9/16.
 */
public final class ConstructorAssert<T>
    extends AbstractParameterAssert<ConstructorAssert<T>, Constructor<T>> {

    ConstructorAssert(Constructor<T> actual) {
        super(actual, constructorInvoker(), ConstructorAssert.class);
    }

    private static <E> ExecutableInvoker<Constructor<E>> constructorInvoker() {
        return (executable, arguments) -> executable.newInstance(arguments);
    }
}
