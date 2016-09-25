package es.sandbox.test.asserts;

import org.assertj.core.api.AbstractAssert;

import java.lang.reflect.Executable;

/**
 * Created by jeslopalo on 21/9/16.
 */
class AbstractParameterAssert<A extends AbstractParameterAssert<A, E>, E extends Executable>
    extends AbstractAssert<A, E> {

    private final ExecutableInvoker<E> executableInvoker;

    public AbstractParameterAssert(E actual, ExecutableInvoker<E> executableInvoker, Class<?> selfType) {
        super(actual, selfType);

        this.executableInvoker = executableInvoker;
    }

    E getActual() {
        return this.actual;
    }

    /**
     * @return
     */
    public OngoingParameterAssert<A, E> wontThrowAnyException() {
        return new OngoingParameterAssert(this, this.executableInvoker);
    }

    /**
     * @param expected
     * @return
     */
    public OngoingParameterAssert<A, E> willThrow(Class<? extends Throwable> expected) {
        return new OngoingParameterAssert(this, this.executableInvoker, expected);
    }

    /**
     * @return
     */
    public OngoingParameterAssert<A, E> willThrowNullPointerException() {
        return willThrow(NullPointerException.class);
    }

    /**
     * @return
     */
    public OngoingParameterAssert<A, E> willThrowIllegalArgumentException() {
        return willThrow(IllegalArgumentException.class);
    }
}
