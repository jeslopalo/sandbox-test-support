package es.sandbox.test.jodatime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JodaTimeFreezer {

   private static final Logger LOGGER= LoggerFactory.getLogger(JodaTimeFreezer.class);


   /**
    * @param frozenDateTime
    */
   public static void freeze(DateTime frozenDateTime) {
      LOGGER.debug("Freezing time at {}", frozenDateTime);
      DateTimeUtils.setCurrentMillisFixed(frozenDateTime.getMillis());
   }

   /**
	 * 
	 */
   public static void unfreeze() {
      LOGGER.debug("Unfreezing time");
      DateTimeUtils.setCurrentMillisSystem();
   }
}
