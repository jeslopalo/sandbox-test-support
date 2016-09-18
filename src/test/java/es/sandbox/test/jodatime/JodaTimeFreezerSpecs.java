package es.sandbox.test.jodatime;

import es.sandbox.test.utils.ReflectionInvoker;
import org.joda.time.DateTime;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;


public class JodaTimeFreezerSpecs {

    private static final DateTime THE_DATE_TIME = new DateTime(2014, 11, 2, 21, 45);


    @Test(expected = UnsupportedOperationException.class)
    public void it_should_raise_an_exception_when_force_to_call_to_private_constructor()
        throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        ReflectionInvoker.privateDefaultConstructor(JodaTimeFreezer.class, UnsupportedOperationException.class);
    }

    @Test
    public void it_should_freeze_time() {

        JodaTimeFreezer.freeze(THE_DATE_TIME);

        assertThat(new DateTime()).isEqualTo(THE_DATE_TIME);
    }

    @Test(expected = NullPointerException.class)
    public void it_should_raise_an_exception_when_try_to_freeze_with_null_datetime() {
        JodaTimeFreezer.freeze(null);
    }

    @Test
    public void it_should_unfreeze_time() {
        JodaTimeFreezer.unfreeze();

        assertThat(new DateTime()).isNotEqualTo(THE_DATE_TIME);
        assertThat(new DateTime().isEqualNow()).isTrue();
    }
}
