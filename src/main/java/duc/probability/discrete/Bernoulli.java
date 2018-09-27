package duc.probability.discrete;


public class Bernoulli {
    private boolean value;
    private double confidence;

    public Bernoulli(boolean value, double confidence) {
        this.value = value;
        this.confidence = confidence;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public Bernoulli or(Bernoulli b2) {
        return or(this, b2);
    }

    public Bernoulli and(Bernoulli b2) {
        return and(this, b2);
    }

    public boolean equals(Bernoulli b2) {
        return equals(this, b2);
    }

    public boolean notEquals(Bernoulli b2) {
        return notEquals(this, b2);
    }

    public Bernoulli not() {
        return not(this);
    }

    public boolean resolveValue(double conf) {
        return resolveValue(this,conf);
    }

    public boolean exists(double conf) {
        return exists(this,conf);
    }


    public static Bernoulli or(Bernoulli b1, Bernoulli b2) {
        double confB1 = (b1.value) ? b1.confidence : 1 - b1.confidence;
        double confB2 = (b2.value) ? b2.confidence : 1 - b2.confidence;

        boolean value = b1.value || b2.value;
        double confidence = (value) ? (confB1 + confB2 - confB1 * confB2) : 1 - (confB1 + confB2 - confB1 * confB2);


        return new Bernoulli(value, confidence);
    }

    public static Bernoulli and(Bernoulli b1, Bernoulli b2) {
        double confB1 = (b1.value) ? b1.confidence : 1 - b1.confidence;
        double confB2 = (b2.value) ? b2.confidence : 1 - b2.confidence;

        boolean value = b1.value && b2.value;
        double confidence = (value) ? confB1 * confB2 : 1 - (confB1 * confB2);


        return new Bernoulli(value, confidence);
    }

    public static boolean equals(Bernoulli b1, Bernoulli b2) {
        double trueConfB1 = (b1.getValue()) ? b1.getConfidence() : 1 - b1.getConfidence();
        double trueConfB2 = (b2.getValue()) ? b2.getConfidence() : 1 - b2.getConfidence();

        return trueConfB1 == trueConfB2;
    }

    public static boolean notEquals(Bernoulli b1, Bernoulli b2) {
        return !equals(b1, b2);
    }

    public static Bernoulli not(Bernoulli b1) {
        return new Bernoulli(!b1.value, b1.confidence);
    }

    public static boolean resolveValue(Bernoulli b1, double conf) {
        boolean isValPossible = b1.confidence >= conf;
        boolean isOppositePossible = (1 - b1.confidence) >= conf;

        if(isValPossible && !isOppositePossible) {
            return b1.value;
        }

        if(!isValPossible && isOppositePossible) {
            return !b1.value;
        }

        if(!isValPossible && !isOppositePossible) {
            return false;
        }

        if(b1.confidence >= (1-b1.confidence)) {
            return b1.value;
        }

        return !b1.value;
    }

    public static boolean exists(Bernoulli b1, double conf) {
        return b1.confidence >= conf || (1 -b1.confidence) >= conf;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bernoulli)) {
            return false;
        }

        return equals(this, (Bernoulli) obj);
    }

    @Override
    public String toString() {
        return "Bernoulli(" + value + ", " + confidence + ")";
    }
}
