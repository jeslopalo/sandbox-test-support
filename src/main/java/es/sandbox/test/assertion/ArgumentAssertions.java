package es.sandbox.test.assertion;


public class ArgumentAssertions {

   public static final Object NULL= null;
   public static final String EMPTY= "";
   public static final String BLANK= " ";

   private final Class<?> type;


   public static <Type> ArgumentAssertions assertThatIn(Class<Type> type) {
      return new ArgumentAssertions(type);
   }

   private ArgumentAssertions(Class<?> type) {
      this.type= type;
   }

   public ConstructorAsserts constructor(Class<?>... types) {
      return new ConstructorAsserts(this.type, types);
   }

   public StaticMethodAsserts staticMethod(String methodName) {
      return new StaticMethodAsserts(this.type, methodName);
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
}
