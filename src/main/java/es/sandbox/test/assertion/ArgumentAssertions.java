package es.sandbox.test.assertion;


/**
 * @author jeslopalo
 */
public class ArgumentAssertions {

   private final Class<?> type;


   public static ArgumentAssertions assertThatIn(Class<?> type) {
      return new ArgumentAssertions(type);
   }

   private ArgumentAssertions(Class<?> type) {
      this.type= type;
   }

   public ConstructorAsserts constructor(Class<?>... types) {
      return new ConstructorAsserts(this.type, types);
   }

   public StaticMethodAsserts staticMethod(String methodName, Class<?>... arguments) {
      return new StaticMethodAsserts(this.type, methodName).withArguments(arguments);
   }

   public MethodAsserts method(String methodName, Class<?> arguments) {
      return new MethodAsserts(methodName).withArguments(arguments);
   }
}
