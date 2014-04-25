package es.sandbox.test.assertion;

import static org.fest.assertions.api.Assertions.failBecauseExceptionWasNotThrown;

import org.fest.assertions.api.Assertions;
import org.fest.reflect.core.Reflection;
import org.fest.reflect.method.Invoker;


public class MethodAsserts {

   private final String method;
   private Class<?> returnType;
   private Class<?>[] argumentTypes;
   private Object instance;

   private Class<? extends Exception> expectedException;


   public MethodAsserts(String method) {
      this.method= method;
   }


   public MethodAsserts withReturnType(Class<?> type) {
      this.returnType= type;
      return this;
   }

   MethodAsserts withArguments(Class<?>... types) {
      this.argumentTypes= types;
      return this;
   }

   public MethodAsserts willThrow(Class<? extends Exception> expected) {
      this.expectedException= expected;
      return this;
   }

   public MethodAsserts throwsNullPointerException() {
      this.expectedException= NullPointerException.class;
      return this;
   }

   public MethodAsserts throwsIllegalArgumentException() {
      this.expectedException= IllegalArgumentException.class;
      return this;
   }

   public MethodAsserts in(Object instance) {
      this.instance= instance;
      return this;
   }

   private Invoker<?> invoker() {
      return Reflection.method(this.method)
            .withReturnType(this.returnType == null? Void.class : this.returnType)
            .withParameterTypes(this.argumentTypes)
            .in(this.instance);
   }

   public MethodAsserts invokedWith(Object... arguments) {
      try {
         invoker().invoke(arguments);
         failBecauseExceptionWasNotThrown(this.expectedException);
      }
      catch (final Exception exception) {
         Assertions.assertThat(exception).isInstanceOf(this.expectedException);
      }
      return this;
   }

   public MethodAsserts invokedWithNulls() {
      final Object[] arguments= new Object[this.argumentTypes.length];
      return invokedWith(arguments);
   }
}
