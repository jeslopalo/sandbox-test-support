package es.sandbox.test.assertion;

import static org.fest.assertions.api.Assertions.failBecauseExceptionWasNotThrown;

import org.fest.assertions.api.Assertions;
import org.fest.reflect.constructor.Invoker;
import org.fest.reflect.core.Reflection;


public class ArgumentAssertions {

   public static final Object NULL= null;
   public static final String EMPTY= "";
   public static final String BLANK= " ";

   private final Class<?> type;


   public static <Type> ArgumentAssertions assertThatIn(Class<Type> type) {
      return new ArgumentAssertions(type);
   }

   public ArgumentAssertions(Class<?> type) {
      this.type= type;
   }

   public ConstructorAsserts constructorWithArguments(Class<?>... types) {
      return new ConstructorAsserts(this.type, types);
   }


   @SuppressWarnings("unchecked")
   public static <T> T nullArgument() {
      return (T) NULL;
   }

   public static String empty() {
      return EMPTY;
   }

   public static String blank() {
      return BLANK;
   }


   public static class ConstructorAsserts {

      private final Invoker<?> constructor;
      private Class<? extends Exception> expectedException;


      public ConstructorAsserts(Class<?> type, Class<?>... parameterTypes) {
         this.constructor= Reflection
               .constructor()
               .withParameterTypes(parameterTypes)
               .in(type);
      }

      public ConstructorAsserts willThrow(Class<? extends Exception> expected) {
         this.expectedException= expected;
         return this;
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
}
