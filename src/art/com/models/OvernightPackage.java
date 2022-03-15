package art.com.models;

import java.math.BigDecimal;

public class OvernightPackage extends Package {
    private final BigDecimal additionalFeePerOunce;

    public OvernightPackage(final UserInformation sender,
                            final UserInformation recipient,
                            final BigDecimal weightInOunces,
                            final BigDecimal costPerOunce,
                            final BigDecimal additionalFeePerOunce) {
        super(sender, recipient, weightInOunces, costPerOunce);
        this.additionalFeePerOunce = additionalFeePerOunce;
    }

    @Override
    public BigDecimal calculateCost() {
        return super.calculateCost().add(additionalFeePerOunce.multiply(weightInOunces));
    }

    public static final class Builder {
        private UserInformation sender;
        private UserInformation recipient;
        private BigDecimal weightInOunces;
        private BigDecimal costPerOunce;
        private BigDecimal additionalFeePerOunce;

        public Builder withAdditionalFeePerOunce(BigDecimal additionalFeePerOunce) {
            if (BigDecimal.ZERO.compareTo(additionalFeePerOunce) >= 0)
                throw new IllegalArgumentException("Additional fee should be positive value!");
            this.additionalFeePerOunce = additionalFeePerOunce;
            return this;
        }

        public Builder withPackage(final Package basePackage) {
            this.sender = basePackage.sender;
            this.recipient = basePackage.recipient;
            this.weightInOunces = basePackage.weightInOunces;
            this.costPerOunce = basePackage.costPerOunce;
            return this;
        }

        public OvernightPackage build() {
            return new OvernightPackage(sender, recipient, weightInOunces, costPerOunce, additionalFeePerOunce);
        }
    }
}
