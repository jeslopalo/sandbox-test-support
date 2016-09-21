package es.sandbox.test.asserts;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

/**
 * Created by jeslopalo on 18/9/16.
 */
public final class ConstructorAssert<T>
    extends AbstractAssert<ConstructorAssert<T>, Constructor<T>> {

    ConstructorAssert(Constructor<T> actual) {
        super(actual, ConstructorAssert.class);
    }

    /**
     * @return
     */
    public OngoingConstructorAssert<T> wontThrowAnyException() {
        return new OngoingConstructorAssert<>(this);
    }

    /**
     * @param expected
     * @return
     */
    public OngoingConstructorAssert<T> willThrow(Class<? extends Throwable> expected) {
        return new OngoingConstructorAssert<>(this, expected);
    }

    /**
     * @return
     */
    public OngoingConstructorAssert<T> willThrowNullPointerException() {
        return willThrow(NullPointerException.class);
    }

    /**
     * @return
     */
    public OngoingConstructorAssert<T> willThrowIllegalArgumentException() {
        return willThrow(IllegalArgumentException.class);
    }


    public static final class OngoingConstructorAssert<T> {

        private final ConstructorAssert<T> constructorAssert;
        private final Optional<Class<? extends Throwable>> expected;

        OngoingConstructorAssert(ConstructorAssert ongoingAssert, Class<? extends Throwable> expected) {
            this.constructorAssert = ongoingAssert;
            this.expected = Optional.of(expected);
        }

        OngoingConstructorAssert(ConstructorAssert ongoingAssert) {
            this.constructorAssert = ongoingAssert;
            this.expected = Optional.empty();
        }

        /**
         * @param arguments
         * @return
         * @throws IllegalAccessException
         * @throws InstantiationException
         */
        public OngoingConstructorAssert whenInvokedWith(Object... arguments) throws IllegalAccessException, InstantiationException {

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
        public OngoingConstructorAssert whenInvokedWithNulls() throws InstantiationException, IllegalAccessException {
            return whenInvokedWith(new Object[constructor().getParameterCount()]);
        }

        /**
         * @return
         * @throws InstantiationException
         * @throws IllegalAccessException
         */
        public OngoingConstructorAssert whenInvokedWithoutParams() throws InstantiationException, IllegalAccessException {
            return whenInvokedWith();
        }

        private void invoke(Object[] arguments) throws InstantiationException, IllegalAccessException, InvocationTargetException {

            constructor().newInstance(arguments);
            if (this.expected.isPresent()) {
                Assertions.failBecauseExceptionWasNotThrown(this.expected.get());
            }
        }

        private void onInvocationException(InvocationTargetException exception) {

            if (this.expected.isPresent()) {
                Assertions.assertThat(exception.getTargetException()).isInstanceOf(this.expected.get());
            } else {
                Assertions.fail("Unexpected exception was thrown", exception.getTargetException());
            }
        }

        private void onUnexpectedException(Throwable throwable) {
            Assertions.fail(String.format("Unable to find constructor %s", constructor()), throwable);
        }

        private Constructor<T> constructor() {
            return this.constructorAssert.actual;
        }
    }
}
