package es.sandbox.test.assertion;

import static org.fest.assertions.api.Assertions.failBecauseExceptionWasNotThrown;

import org.fest.assertions.api.Assertions;
import org.fest.reflect.constructor.Invoker;
import org.fest.reflect.core.Reflection;


public class ConstructorAsserts {

   private final Invoker<?> constructor;
   private Class<? extends Exception> expectedException;


   ConstructorAsserts(Class<?> type, Class<?>... parameterTypes) {
      this.constructor= Reflection
            .constructor()
            .withParameterTypes(parameterTypes)
            .in(type);
   }


   public ConstructorAsserts willThrow(Class<? extends Exception> expected) {
      this.expectedException= expected;
      return this;
   }

   public ConstructorAsserts willThrowNullPointerException() {
      return willThrow(NullPointerException.class);
   }

   public ConstructorAsserts willThrowIllegalArgumentException() {
      return willThrow(IllegalArgumentException.class);
   }


   public ConstructorAsserts invokedWith(Object... arguments) {
      try {
         this.constructor.newInstance(arguments);
         failBecauseExceptionWasNotThrown(this.expectedException);
      }
      catch (final Exception exception) {
         Assertions.assertThat(exception).isInstanceOf(this.expectedException);
      }
      return this;
   }
}
