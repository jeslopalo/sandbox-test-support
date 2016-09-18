package es.sandbox.test.assertion;

import org.fest.reflect.core.Reflection;
import org.fest.reflect.method.Invoker;
import org.fest.reflect.method.StaticMethodName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class StaticMethodAsserts {

    private final Class<?> type;
    private final String method;
    private Class<?> returnType;
    private Arguments arguments;

    private Class<? extends Exception> expectedException;


    StaticMethodAsserts(Class<?> type, String method) {
        this.type = type;
        this.method = method;
    }


    public StaticMethodAsserts withReturnType(Class<?> type) {
        this.returnType = type;
        return this;
    }

    StaticMethodAsserts withArguments(Arguments arguments) {
        this.arguments = arguments;
        return this;
    }

    public StaticMethodAsserts willThrow(Class<? extends Exception> expected) {
        this.expectedException = expected;
        return this;
    }

    public StaticMethodAsserts throwsNullPointerException() {
        this.expectedException = NullPointerException.class;
        return this;
    }

    public StaticMethodAsserts throwsIllegalArgumentException() {
        this.expectedException = IllegalArgumentException.class;
        return this;
    }

    private Invoker<?> invoker() {
        final StaticMethodName staticMethodName = Reflection.staticMethod(this.method);
        if (this.returnType == null) {
            return staticMethodName
                .withParameterTypes(this.arguments.get())
                .in(this.type);
        }
        return staticMethodName
            .withReturnType(this.returnType)
            .withParameterTypes(this.arguments.get())
            .in(this.type);
    }

    public StaticMethodAsserts invokedWith(Object... arguments) {
        try {
            invoker().invoke(arguments);
            failBecauseExceptionWasNotThrown(this.expectedException);
        } catch (final Exception exception) {
            assertThat(exception).isInstanceOf(this.expectedException);
        }
        return this;
    }

    public StaticMethodAsserts invokedWithNulls() {
        final Object[] arguments = new Object[this.arguments.size()];
        return invokedWith(arguments);
    }
}
