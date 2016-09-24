package es.sandbox.test.asserts;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.IllegalFormatException;

import static es.sandbox.test.asserts.ParameterAssertions.assertThat;

/**
 * Created by jeslopalo on 22/9/16.
 */
@RunWith(Enclosed.class)
public class MethodAssertSpecs {

    public static final class WhenAMethodHasNoArguments {

        @Test
        public void it_should_not_fail_wnen_not_raise_exceptions() throws NoSuchMethodException, IllegalAccessException, InstantiationException {

            assertThat(FixtureBean.class.getMethod("methodWithoutArgumentsAndVoidReturn"))
                .in(new FixtureBean())
                .wontThrowAnyException()
                .whenInvokedWithoutParams();
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_not_raise_expected_exceptions() throws NoSuchMethodException, IllegalAccessException, InstantiationException {

            assertThat(FixtureBean.class.getMethod("methodWithoutArgumentsAndVoidReturn"))
                .in(new FixtureBean())
                .willThrowNullPointerException()
                .whenInvokedWithoutParams();
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_invoked_with_params() throws NoSuchMethodException, InstantiationException, IllegalAccessException {

            assertThat(FixtureBean.class.getMethod("methodWithoutArgumentsAndVoidReturn"))
                .in(new FixtureBean())
                .wontThrowAnyException()
                .whenInvokedWith("");
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_invoked_with_null_params() throws NoSuchMethodException, InstantiationException, IllegalAccessException {

            assertThat(FixtureBean.class.getMethod("methodWithoutArgumentsAndVoidReturn"))
                .in(new FixtureBean())
                .wontThrowAnyException()
                .whenInvokedWith(new Object[]{null});
        }
    }

    public static final class WhenAMethodHasAStringArgument {

        @Test
        public void it_should_not_fail_when_assert_expected_behavior() throws NoSuchMethodException, IllegalAccessException, InstantiationException {

            assertThat(FixtureBean.class.getMethod("methodWithAStringArgumentAndVoidReturn", String.class))
                .in(new FixtureBean())
                .willThrowNullPointerException()
                .whenInvokedWithNulls();

            assertThat(FixtureBean.class.getMethod("methodWithAStringArgumentAndVoidReturn", String.class))
                .in(new FixtureBean())
                .willThrowIllegalArgumentException()
                .whenInvokedWith("");

            assertThat(FixtureBean.class.getMethod("methodWithAStringArgumentAndVoidReturn", String.class))
                .in(new FixtureBean())
                .willThrowIllegalArgumentException()
                .whenInvokedWith(" ");
        }

        @Test
        public void it_should_be_possible_to_chain_several_invocations() throws NoSuchMethodException, InstantiationException, IllegalAccessException {

            assertThat(FixtureBean.class.getMethod("methodWithAStringArgumentAndVoidReturn", String.class))
                .in(new FixtureBean())
                .wontThrowAnyException()
                .whenInvokedWith("a")
                .whenInvokedWith("b")
                .whenInvokedWith("c");
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_raise_an_unexpected_exception() throws NoSuchMethodException, InstantiationException, IllegalAccessException {

            assertThat(FixtureBean.class.getMethod("methodWithAStringArgumentAndVoidReturn", String.class))
                .in(new FixtureBean())
                .wontThrowAnyException()
                .whenInvokedWith(FixtureBean.BADDATA);
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_not_raise_an_expected_exception() throws NoSuchMethodException, InstantiationException, IllegalAccessException {

            assertThat(FixtureBean.class.getMethod("methodWithAStringArgumentAndVoidReturn", String.class))
                .in(new FixtureBean())
                .willThrow(IllegalFormatException.class)
                .whenInvokedWith("bubu");
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_invoked_without_params() throws NoSuchMethodException, IllegalAccessException, InstantiationException {

            assertThat(FixtureBean.class.getMethod("methodWithAStringArgumentAndVoidReturn", String.class))
                .in(new FixtureBean())
                .wontThrowAnyException()
                .whenInvokedWithoutParams();
        }
    }

    public static final class WhenAStaticMethodHasNoArguments {

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_method_is_not_static() throws NoSuchMethodException, IllegalAccessException, InstantiationException {

            assertThat(FixtureBean.class.getMethod("methodWithoutArgumentsAndVoidReturn"))
                .beingStatic()
                .wontThrowAnyException()
                .whenInvokedWithoutParams();
        }

        @Test
        public void it_should_not_fail_when_not_raise_exceptions() throws NoSuchMethodException, IllegalAccessException, InstantiationException {

            assertThat(FixtureBean.class.getMethod("staticMethod"))
                .beingStatic()
                .wontThrowAnyException()
                .whenInvokedWithoutParams();
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_not_raise_expected_exception() throws NoSuchMethodException, IllegalAccessException, InstantiationException {

            assertThat(FixtureBean.class.getMethod("staticMethod"))
                .beingStatic()
                .willThrowNullPointerException()
                .whenInvokedWithoutParams();
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_invoked_with_params() throws NoSuchMethodException, InstantiationException, IllegalAccessException {

            assertThat(FixtureBean.class.getMethod("staticMethod"))
                .beingStatic()
                .wontThrowAnyException()
                .whenInvokedWith("");
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_invoked_with_null_params() throws NoSuchMethodException, InstantiationException, IllegalAccessException {
            assertThat(FixtureBean.class.getMethod("staticMethod"))
                .beingStatic()
                .wontThrowAnyException()
                .whenInvokedWith(new Object[]{null});
        }
    }

    public static final class WhenAStaticMethodHasAStringArgument {

        @Test
        public void it_should_not_fail_when_assert_expected_behavior() throws NoSuchMethodException, IllegalAccessException, InstantiationException {

            assertThat(FixtureBean.class.getMethod("staticMethodWithAStringArgumentAndVoidReturn", String.class))
                .beingStatic()
                .willThrowNullPointerException()
                .whenInvokedWithNulls();

            assertThat(FixtureBean.class.getMethod("staticMethodWithAStringArgumentAndVoidReturn", String.class))
                .beingStatic()
                .willThrowIllegalArgumentException()
                .whenInvokedWith("");

            assertThat(FixtureBean.class.getMethod("staticMethodWithAStringArgumentAndVoidReturn", String.class))
                .beingStatic()
                .willThrowIllegalArgumentException()
                .whenInvokedWith(" ");
        }

        @Test
        public void it_should_be_possible_to_chain_several_invocations() throws NoSuchMethodException, InstantiationException, IllegalAccessException {

            assertThat(FixtureBean.class.getMethod("staticMethodWithAStringArgumentAndVoidReturn", String.class))
                .beingStatic()
                .wontThrowAnyException()
                .whenInvokedWith("a")
                .whenInvokedWith("b")
                .whenInvokedWith("c");
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_raise_an_unexpected_exception() throws NoSuchMethodException, InstantiationException, IllegalAccessException {

            assertThat(FixtureBean.class.getMethod("staticMethodWithAStringArgumentAndVoidReturn", String.class))
                .beingStatic()
                .wontThrowAnyException()
                .whenInvokedWith(FixtureBean.BADDATA);
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_not_raise_an_expected_exception() throws NoSuchMethodException, InstantiationException, IllegalAccessException {

            assertThat(FixtureBean.class.getMethod("staticMethodWithAStringArgumentAndVoidReturn", String.class))
                .beingStatic()
                .willThrow(IllegalFormatException.class)
                .whenInvokedWith("bubu");
        }

        @Test(expected = AssertionError.class)
        public void it_should_fail_when_invoked_without_params() throws NoSuchMethodException, IllegalAccessException, InstantiationException {

            assertThat(FixtureBean.class.getMethod("staticMethodWithAStringArgumentAndVoidReturn", String.class))
                .beingStatic()
                .wontThrowAnyException()
                .whenInvokedWithoutParams();
        }
    }

    @Ignore
    private static final class FixtureBean {

        public static final String BADDATA = "baddata";

        public FixtureBean() {

        }

        public static void staticMethod() {

        }

        public static void staticMethodWithAStringArgumentAndVoidReturn(final String argument) {

            if (argument == null) {
                throw new NullPointerException();
            }
            if (argument.trim().length() == 0) {
                throw new IllegalArgumentException();
            }
            if (argument.equals(BADDATA)) {
                throw new IllegalArgumentException(BADDATA);
            }
        }

        public void methodWithoutArgumentsAndVoidReturn() {

        }

        public void methodWithAStringArgumentAndVoidReturn(final String argument) {

            if (argument == null) {
                throw new NullPointerException();
            }
            if (argument.trim().length() == 0) {
                throw new IllegalArgumentException();
            }
            if (argument.equals(BADDATA)) {
                throw new IllegalArgumentException(BADDATA);
            }
        }
    }

}
