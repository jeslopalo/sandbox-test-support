package es.sandbox.test.asserts;

import java.lang.reflect.Method;

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
    }
}
