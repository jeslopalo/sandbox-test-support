package es.sandbox.test.assertion;


/**
 * @author jeslopalo
 */
public class ArgumentAssertions {

   public static <Type> ConstructorAsserts assertThatConstructor(Class<Type> type, Arguments arguments) {
      return new ConstructorAsserts(type, arguments);
   }

   public static <Type> StaticMethodAsserts assertThatStaticMethod(Class<Type> type, String methodName, Arguments arguments) {
      return new StaticMethodAsserts(type, methodName).withArguments(arguments);
   }

   public static <Type> MethodAsserts assertThatMethod(Type instance, String methodName, Arguments arguments) {
      return new MethodAsserts(instance, methodName).withArguments(arguments);
   }

   public static Arguments arguments(Class<?>... types) {
      return new Arguments(types);
   }
}
