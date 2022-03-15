package art.com.models;

import java.math.BigDecimal;

public class TwoDayPackage extends Package {
    private final BigDecimal flatFee;

    private TwoDayPackage(final UserInformation sender,
                          final UserInformation recipient,
                          final BigDecimal weightInOunces,
                          final BigDecimal costPerOunce,
                          final BigDecimal flatFee) {
        super(sender, recipient, weightInOunces, costPerOunce);
        this.flatFee = flatFee;
    }

    @Override
    public BigDecimal calculateCost() {
        return super.calculateCost().add(flatFee);
    }

    public static final class Builder {
        private UserInformation sender;
        private UserInformation recipient;
        private BigDecimal weightInOunces;
        private BigDecimal costPerOunce;
        private BigDecimal flatFee;

        public Builder withFlatFee(final BigDecimal flatFee) {
            if (BigDecimal.ZERO.compareTo(flatFee) >= 0)
                throw new IllegalArgumentException("Flat fee should be positive value!");
            this.flatFee = flatFee;
            return this;
        }

        public Builder withPackage(final Package basePackage) {
            this.sender = basePackage.sender;
            this.recipient = basePackage.recipient;
            this.weightInOunces = basePackage.weightInOunces;
            this.costPerOunce = basePackage.costPerOunce;
            return this;
        }

        public TwoDayPackage build() {
            return new TwoDayPackage(sender, recipient, weightInOunces, costPerOunce, flatFee);
        }
    }
}
