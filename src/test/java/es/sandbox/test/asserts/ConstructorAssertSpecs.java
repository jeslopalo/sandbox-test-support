package es.sandbox.test.asserts;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.IllegalFormatException;

import static es.sandbox.test.asserts.ParameterAssertions.assertThat;

/**
 * Created by jeslopalo on 18/9/16.
 */
@RunWith(Enclosed.class)
public class ConstructorAssertSpecs {

    public static final class WhenAConstructorHasNoArguments {

        @Test
        public void it_should_not_fail_when_not_raise_exceptions() throws NoSuchMethodException {

            assertThat(FixtureBean.class.getConstructor())
                .wontThrowAnyException()
                .whenInvokedWithoutParams();
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_not_raise_expected_exceptions() throws NoSuchMethodException {

            assertThat(FixtureBean.class.getConstructor())
                .willThrowNullPointerException()
                .whenInvokedWithoutParams();
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_invoked_with_params() throws NoSuchMethodException {

            assertThat(FixtureBean.class.getConstructor())
                .wontThrowAnyException()
                .whenInvokedWith("");
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_invoked_with_null_params() throws NoSuchMethodException {

            assertThat(FixtureBean.class.getConstructor())
                .wontThrowAnyException()
                .whenInvokedWith(new Object[]{null});
        }

    }

    public static final class WhenAConstructorHasAStringArgument {

        @Test
        public void it_should_not_fail_when_assert_expected_behavior() throws NoSuchMethodException {

            assertThat(FixtureBean.class.getConstructor(String.class))
                .willThrowNullPointerException()
                .whenInvokedWithNulls();

            assertThat(FixtureBean.class.getConstructor(String.class))
                .willThrowIllegalArgumentException()
                .whenInvokedWith("");

            assertThat(FixtureBean.class.getConstructor(String.class))
                .willThrowIllegalArgumentException()
                .whenInvokedWith(" ");
        }

        @Test
        public void it_should_be_possible_to_chain_several_invocations() throws NoSuchMethodException {

            assertThat(FixtureBean.class.getConstructor(String.class))
                .wontThrowAnyException()
                .whenInvokedWith("a")
                .whenInvokedWith("b")
                .whenInvokedWith("c");
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_raise_an_unexpected_exception() throws NoSuchMethodException {

            assertThat(FixtureBean.class.getConstructor(String.class))
                .wontThrowAnyException()
                .whenInvokedWith(FixtureBean.BADDATA);
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_not_raise_an_expected_exception() throws NoSuchMethodException {

            assertThat(FixtureBean.class.getConstructor(String.class))
                .willThrow(IllegalFormatException.class)
                .whenInvokedWith("bubu");
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_invoked_without_params() throws NoSuchMethodException {

            assertThat(FixtureBean.class.getConstructor(String.class))
                .wontThrowAnyException()
                .whenInvokedWithoutParams();
        }
    }


    @Ignore
    private static final class FixtureBean {

        public static final String BADDATA = "baddata";

        public FixtureBean() {

        }

        public FixtureBean(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            if (value.trim().length() == 0) {
                throw new IllegalArgumentException();
            }
            if (value.equals(BADDATA)) {
                throw new IllegalArgumentException(BADDATA);
            }
        }
    }
}
