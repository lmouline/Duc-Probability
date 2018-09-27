package duc.probability.discrete;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BernoulliTest {
    private boolean[] values1 = {true, true, false, false};
    private double[] confidence1 = {1.0, .1, .2, .3};

    private boolean[] values2 = {true, false, true, false};
    private double[] confidence2 = {.6, .5, .8, .1};




    @Test
    public void testAnd() {
        boolean[] expectedVal = {true, false, false, false};
        double[] expectedConf = {.6, .95, .36, .37};

        for (int i = 0; i < values1.length; i++) {
            Bernoulli b1 = new Bernoulli(values1[i], confidence1[i]);
            Bernoulli b2 = new Bernoulli(values2[i], confidence2[i]);
            Bernoulli expected = new Bernoulli(expectedVal[i], expectedConf[i]);
            internal_testAnd(b1, b2, expected);
        }
    }

    private void internal_testAnd(Bernoulli b1, Bernoulli b2, Bernoulli expected) {
        Bernoulli and = Bernoulli.and(b1, b2);
        Assertions.assertNotNull(and);
        Assertions.assertEquals(expected.getValue(), and.getValue());
        Assertions.assertEquals(expected.getConfidence(), and.getConfidence(), 0.001);
    }

    @Test
    public void testOr() {
        boolean[] expectedVal = {true, true, true, false};
        double[] expectedConf = {1.0, .55, .96, .03};

        for (int i = 0; i < values1.length; i++) {
            Bernoulli b1 = new Bernoulli(values1[i], confidence1[i]);
            Bernoulli b2 = new Bernoulli(values2[i], confidence2[i]);
            Bernoulli expected = new Bernoulli(expectedVal[i], expectedConf[i]);
            internal_testOr(b1, b2, expected);
        }
    }

    private void internal_testOr(Bernoulli b1, Bernoulli b2, Bernoulli expected) {
        Bernoulli or = Bernoulli.or(b1, b2);
        Assertions.assertNotNull(or);
        Assertions.assertEquals(expected.getValue(), or.getValue());
        Assertions.assertEquals(expected.getConfidence(), or.getConfidence(), 0.001);
    }

    @Test
    public void testEquals() {
        boolean[] expectedVal = {false, false, true, false};

        for (int i = 0; i < values1.length; i++) {
            Bernoulli b1 = new Bernoulli(values1[i], confidence1[i]);
            Bernoulli b2 = new Bernoulli(values2[i], confidence2[i]);
            internal_testEquals(b1, b2, expectedVal[i]);
        }
    }

    private void internal_testEquals(Bernoulli b1, Bernoulli b2, boolean expectedVal) {
        boolean eq = Bernoulli.equals(b1, b2);
        Assertions.assertEquals(expectedVal, eq);
    }

    @Test
    public void testNotEquals() {
        boolean[] expectedVal = {true, true, false, true};

        for (int i = 0; i < values1.length; i++) {
            Bernoulli b1 = new Bernoulli(values1[i], confidence1[i]);
            Bernoulli b2 = new Bernoulli(values2[i], confidence2[i]);
            internal_testNotEquals(b1, b2, expectedVal[i]);
        }
    }

    private void internal_testNotEquals(Bernoulli b1, Bernoulli b2, boolean expectedVal) {
        boolean eq = Bernoulli.notEquals(b1, b2);
        Assertions.assertEquals(expectedVal, eq);
    }


    @Test
    public void testNot() {
        boolean[] expectedVal = {false, false, true, true};
        double[] expectedConf = {1.0, .1, .2, .3};

        for (int i = 0; i < values1.length; i++) {
            Bernoulli b1 = new Bernoulli(values1[i], confidence1[i]);
            Bernoulli expected = new Bernoulli(expectedVal[i], expectedConf[i]);
            internal_testNot(b1, expected);
        }
    }

    private void internal_testNot(Bernoulli b1, Bernoulli expected) {
        Bernoulli not = Bernoulli.not(b1);
        Assertions.assertNotNull(not);
        Assertions.assertEquals(expected.getValue(), not.getValue());
        Assertions.assertEquals(expected.getConfidence(), not.getConfidence());
    }


    @Test
    public void testExists() {
        boolean[] expectedVal = {false, true, true, true};
        double[] conf = {1.0, .1, .2, .3};

        Bernoulli tested = new Bernoulli(values1[2], confidence1[2]);

        for (int i = 0; i < expectedVal.length; i++) {
            internal_testExists(tested, conf[i], expectedVal[i]);
        }
    }

    private void internal_testExists(Bernoulli b1, double conf, boolean expected) {
        boolean exists = Bernoulli.exists(b1, conf);
        Assertions.assertEquals(expected, exists);
    }

    @Test
    public void testResolveValue() {
        boolean[] expectedVal = {true, true, true, true};
        double[] conf = {.6, .1, .2, .3};

        Bernoulli tested = new Bernoulli(values1[2], confidence1[2]);

        for (int i = 0; i < expectedVal.length; i++) {
            internal_testResolveValue(tested, conf[i], expectedVal[i]);
        }
    }

    private void internal_testResolveValue(Bernoulli b1, double conf, boolean expected) {
        boolean exists = Bernoulli.resolveValue(b1, conf);
        Assertions.assertEquals(expected, exists, "Assertion false for confidence " + conf);
    }


}
