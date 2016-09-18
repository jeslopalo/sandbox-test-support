package es.sandbox.test.assertion;

import org.fest.reflect.core.Reflection;
import org.fest.reflect.method.Invoker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;


public class MethodAsserts {

    private final String method;
    private Class<?> returnType;
    // private Class<?>[] argumentTypes;
    private Arguments arguments;
    private Object instance;

    private Class<? extends Exception> expectedException;


    public MethodAsserts(Object instance, String method) {
        this.instance = instance;
        this.method = method;
    }


    public MethodAsserts withReturnType(Class<?> type) {
        this.returnType = type;
        return this;
    }

    MethodAsserts withArguments(Arguments arguments) {
        this.arguments = arguments;
        return this;
    }

    public MethodAsserts willThrow(Class<? extends Exception> expected) {
        this.expectedException = expected;
        return this;
    }

    public MethodAsserts throwsNullPointerException() {
        this.expectedException = NullPointerException.class;
        return this;
    }

    public MethodAsserts throwsIllegalArgumentException() {
        this.expectedException = IllegalArgumentException.class;
        return this;
    }

    MethodAsserts in(Object instance) {
        this.instance = instance;
        return this;
    }

    private Invoker<?> invoker() {
        return Reflection.method(this.method)
            .withReturnType((Class<?>) (this.returnType == null ? Void.class : this.returnType))
            .withParameterTypes(this.arguments.get())
            .in(this.instance);
    }

    public MethodAsserts invokedWith(Object... arguments) {
        try {
            invoker().invoke(arguments);
            failBecauseExceptionWasNotThrown(this.expectedException);
        } catch (final Exception exception) {
            assertThat(exception).isInstanceOf(this.expectedException);
        }
        return this;
    }

    public MethodAsserts invokedWithNulls() {
        final Object[] arguments = new Object[this.arguments.size()];
        return invokedWith(arguments);
    }
}
