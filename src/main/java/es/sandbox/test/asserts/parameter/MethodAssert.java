package es.sandbox.test.asserts.parameter;

import org.assertj.core.api.Assertions;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by jeslopalo on 21/9/16.
 */
final class MethodAssert<T>
    extends AbstractParameterAssert<MethodAssert<T>, Method> {

    private MethodAssert(Method method, T instance) {
        super(method, methodInvoker(instance), MethodAssert.class);
    }

    private static <T> ExecutableInvoker<Method> methodInvoker(T instance) {
        return (executable, arguments) -> executable.invoke(instance, arguments);
    }

    final static class OngoingMethodAssert<T> {

        private final Method method;

        OngoingMethodAssert(Method method) {
            this.method = method;
        }

        public MethodAssert in(T instance) {
            return new MethodAssert(this.method, instance);
        }

        public MethodAssert beingStatic() {
            Assertions.assertThat(Modifier.isStatic(this.method.getModifiers())).isTrue();

            return new MethodAssert(this.method, null);
        }
    }
}
