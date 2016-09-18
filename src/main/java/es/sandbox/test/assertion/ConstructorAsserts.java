package es.sandbox.test.assertion;

import org.fest.reflect.constructor.Invoker;
import org.fest.reflect.core.Reflection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;


public class ConstructorAsserts {

    private final Invoker<?> constructor;
    private Class<? extends Exception> expectedException;


    ConstructorAsserts(Class<?> type, Arguments arguments) {
        this.constructor = Reflection
            .constructor()
            .withParameterTypes(arguments.get())
            .in(type);
    }


    public ConstructorAsserts willThrow(Class<? extends Exception> expected) {
        this.expectedException = expected;
        return this;
    }

    public ConstructorAsserts throwsNullPointerException() {
        return willThrow(NullPointerException.class);
    }

    public ConstructorAsserts throwsIllegalArgumentException() {
        return willThrow(IllegalArgumentException.class);
    }


    public ConstructorAsserts invokedWith(Object... arguments) {
        try {
            this.constructor.newInstance(arguments);
            failBecauseExceptionWasNotThrown(this.expectedException);
        } catch (final Exception exception) {
            assertThat(exception).isInstanceOf(this.expectedException);
        }
        return this;
    }

    public ConstructorAsserts invokedWithNulls() {
        final Object[] arguments = new Object[this.constructor.info().getParameterTypes().length];
        return invokedWith(arguments);
    }
}
