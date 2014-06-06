package es.sandbox.test.assertion;

import java.util.Arrays;


class Arguments {

   private final Class<?>[] types;


   public Arguments(Class<?>... types) {
      this.types= types == null? new Class[0] : types;
   }

   public Class<?>[] get() {
      return this.types;
   }

   public int size() {
      return this.types.length;
   }

   @Override
   public String toString() {
      return String.format("(%s)", Arrays.toString(this.types));
   }
}
