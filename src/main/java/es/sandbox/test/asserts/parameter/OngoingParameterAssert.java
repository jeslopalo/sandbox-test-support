package es.sandbox.test.asserts.parameter;

import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by jeslopalo on 25/9/16.
 */
public final class OngoingParameterAssert<T extends AbstractParameterAssert<T, E>, E extends Executable> {

    private final T ongoingAssert;
    private final Optional<Class<? extends Throwable>> expected;
    private final ExecutableInvoker<E> executableInvoker;

    OngoingParameterAssert(T ongoingAssert, ExecutableInvoker<E> executableInvoker, Class<? extends Throwable> expected) {
        this.ongoingAssert = ongoingAssert;
        this.executableInvoker = executableInvoker;
        this.expected = Optional.of(expected);
    }

    OngoingParameterAssert(T ongoingAssert, ExecutableInvoker<E> executableInvoker) {
        this.ongoingAssert = ongoingAssert;
        this.executableInvoker = executableInvoker;
        this.expected = Optional.empty();
    }

    /**
     * @param arguments
     * @return
     */
    public OngoingParameterAssert whenInvokedWith(Object... arguments) {

        try {
            invoke(arguments);
        } catch (InvocationTargetException exception) {
            onInvocationException(exception);
        } catch (Exception throwable) {
            onUnexpectedException(throwable);
        }
        return this;
    }

    /**
     * @return
     */
    public OngoingParameterAssert whenInvokedWithNulls() {
        return whenInvokedWith(new Object[executable().getParameterCount()]);
    }

    /**
     * @return
     */
    public OngoingParameterAssert whenInvokedWithoutParams() {
        return whenInvokedWith();
    }

    private void invoke(Object[] arguments) throws InstantiationException, IllegalAccessException, InvocationTargetException {

        this.executableInvoker.invoke(executable(), arguments);

        if (this.expected.isPresent()) {
            failBecauseExceptionWasNotThrown(this.expected.get());
        }
    }

    private void onInvocationException(InvocationTargetException exception) {

        if (this.expected.isPresent()) {
            assertThat(exception.getTargetException()).isInstanceOf(this.expected.get());
        } else {
            fail("Unexpected exception was thrown", exception.getTargetException());
        }
    }

    private void onUnexpectedException(Exception exception) {
        fail(String.format("Unable to find executable %s", executable()), exception);
    }

    private E executable() {
        return this.ongoingAssert.getActual();
    }

}

@FunctionalInterface
interface ExecutableInvoker<E extends Executable> {

    void invoke(E executable, Object... arguments) throws InstantiationException, IllegalAccessException, InvocationTargetException;
}
