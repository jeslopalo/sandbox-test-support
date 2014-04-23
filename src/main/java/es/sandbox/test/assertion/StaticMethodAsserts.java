package es.sandbox.test.assertion;

import static org.fest.assertions.api.Assertions.failBecauseExceptionWasNotThrown;

import org.fest.assertions.api.Assertions;
import org.fest.reflect.core.Reflection;
import org.fest.reflect.method.Invoker;


public class StaticMethodAsserts {

   private final Class<?> type;
   private final String method;
   private Class<?> returnType;
   private Class<?> argumentTypes;

   private Class<? extends Exception> expectedException;


   StaticMethodAsserts(Class<?> type, String method) {
      this.type= type;
      this.method= method;
   }


   public StaticMethodAsserts withReturnType(Class<?> type) {
      this.returnType= type;
      return this;
   }

   public StaticMethodAsserts withArguments(Class<?> types) {
      this.argumentTypes= types;
      return this;
   }

   public StaticMethodAsserts willThrow(Class<? extends Exception> expected) {
      this.expectedException= expected;
      return this;
   }

   public StaticMethodAsserts willThrowNullPointerException() {
      this.expectedException= NullPointerException.class;
      return this;
   }

   public StaticMethodAsserts willThrowIllegalArgumentException() {
      this.expectedException= IllegalArgumentException.class;
      return this;
   }

   private Invoker<?> invoker() {
      return Reflection.staticMethod(this.method)
            .withReturnType(this.returnType == null? Void.class : this.returnType)
            .withParameterTypes(this.argumentTypes)
            .in(this.type);
   }

   public StaticMethodAsserts invokedWith(Object... arguments) {
      try {
         invoker().invoke(arguments);
         failBecauseExceptionWasNotThrown(this.expectedException);
      }
      catch (final Exception exception) {
         Assertions.assertThat(exception).isInstanceOf(this.expectedException);
      }
      return this;
   }
}
