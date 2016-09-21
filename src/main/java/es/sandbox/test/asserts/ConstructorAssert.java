package es.sandbox.test.asserts;

import java.lang.reflect.Constructor;

/**
 * Created by jeslopalo on 18/9/16.
 */
public final class ConstructorAssert
    extends AbstractParameterAssert<ConstructorAssert, Constructor> {

    ConstructorAssert(Constructor actual) {
        super(actual, Constructor::newInstance, ConstructorAssert.class);
    }
}
