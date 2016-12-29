package es.sandbox.test.asserts.parameter;

import java.lang.reflect.Constructor;

/**
 * Created by jeslopalo on 18/9/16.
 */
public final class ConstructorAssert
    extends AbstractParameterAssert<ConstructorAssert, Constructor> {

    ConstructorAssert(Constructor constructor) {
        super(constructor, Constructor::newInstance, ConstructorAssert.class);
    }
}
