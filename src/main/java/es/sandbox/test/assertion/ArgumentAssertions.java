package es.sandbox.test.assertion;


/**
 * @author jeslopalo
 */
public class ArgumentAssertions {

   public static <Type> ConstructorAsserts assertThatConstructor(Class<Type> type, Class<?>... types) {
      return new ConstructorAsserts(type, types);
   }

   public static <Type> StaticMethodAsserts assertThatStaticMethod(Class<Type> type, String methodName, Class<?>... arguments) {
      return new StaticMethodAsserts(type, methodName).withArguments(arguments);
   }

   public static <Type> MethodAsserts assertThatMethod(Type instance, String methodName, Class<?>... arguments) {
      return new MethodAsserts(instance, methodName).withArguments(arguments);
   }
}
