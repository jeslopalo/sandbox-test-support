package es.sandbox.test.asserts;

import java.lang.reflect.Constructor;

/**
 * Created by jeslopalo on 18/9/16.
 */
public final class ConstructorAssert
    extends AbstractParameterAssert<ConstructorAssert, Constructor> {

    ConstructorAssert(Constructor actual) {
        super(actual, constructorInvoker(), ConstructorAssert.class);
    }

    private static ExecutableInvoker<Constructor> constructorInvoker() {
        return (executable, arguments) -> executable.newInstance(arguments);
    }
}
