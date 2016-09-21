package es.sandbox.test.asserts;

import org.assertj.core.api.AbstractAssert;

import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by jeslopalo on 21/9/16.
 */
public class AbstractParameterAssert<A extends AbstractParameterAssert<A, E>, E extends Executable>
    extends AbstractAssert<A, E> {

    private final ExecutableInvoker<E> executableInvoker;

    public AbstractParameterAssert(E actual, ExecutableInvoker<E> executableInvoker, Class<?> selfType) {
        super(actual, selfType);

        this.executableInvoker = executableInvoker;
    }

    /**
     * @return
     */
    public OngoingAssert<A, E> wontThrowAnyException() {
        return new OngoingAssert(this, this.executableInvoker);
    }

    /**
     * @param expected
     * @return
     */
    public OngoingAssert<A, E> willThrow(Class<? extends Throwable> expected) {
        return new OngoingAssert(this, this.executableInvoker, expected);
    }

    /**
     * @return
     */
    public OngoingAssert<A, E> willThrowNullPointerException() {
        return willThrow(NullPointerException.class);
    }

    /**
     * @return
     */
    public OngoingAssert<A, E> willThrowIllegalArgumentException() {
        return willThrow(IllegalArgumentException.class);
    }

    @FunctionalInterface
    interface ExecutableInvoker<E extends Executable> {

        void invoke(E executable, Object... arguments) throws InstantiationException, IllegalAccessException, InvocationTargetException;
    }

    public static final class OngoingAssert<T extends AbstractParameterAssert<T, E>, E extends Executable> {

        private final T ongoingAssert;
        private final Optional<Class<? extends Throwable>> expected;
        private final ExecutableInvoker<E> executableInvoker;

        OngoingAssert(T ongoingAssert, ExecutableInvoker<E> executableInvoker, Class<? extends Throwable> expected) {
            this.ongoingAssert = ongoingAssert;
            this.executableInvoker = executableInvoker;
            this.expected = Optional.of(expected);
        }

        OngoingAssert(T ongoingAssert, ExecutableInvoker<E> executableInvoker) {
            this.ongoingAssert = ongoingAssert;
            this.executableInvoker = executableInvoker;
            this.expected = Optional.empty();
        }

        /**
         * @param arguments
         * @return
         * @throws IllegalAccessException
         * @throws InstantiationException
         */
        public OngoingAssert whenInvokedWith(Object... arguments) throws IllegalAccessException, InstantiationException {

            try {
                invoke(arguments);
            } catch (InvocationTargetException exception) {
                onInvocationException(exception);
            } catch (Throwable throwable) {
                onUnexpectedException(throwable);
            }
            return this;
        }

        /**
         * @return
         * @throws InstantiationException
         * @throws IllegalAccessException
         */
        public OngoingAssert whenInvokedWithNulls() throws InstantiationException, IllegalAccessException {
            return whenInvokedWith(new Object[executable().getParameterCount()]);
        }

        /**
         * @return
         * @throws InstantiationException
         * @throws IllegalAccessException
         */
        public OngoingAssert whenInvokedWithoutParams() throws InstantiationException, IllegalAccessException {
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

        private void onUnexpectedException(Throwable throwable) {
            fail(String.format("Unable to find executable %s", executable()), throwable);
        }

        private E executable() {
            return this.ongoingAssert.actual;
        }
    }
}
