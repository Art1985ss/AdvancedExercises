package art.com.models;

import java.math.BigDecimal;

public class Package {
    protected final UserInformation sender;
    protected final UserInformation recipient;
    protected final BigDecimal weightInOunces;
    protected final BigDecimal costPerOunce;

    protected Package(final UserInformation sender, final UserInformation recipient, final BigDecimal weightInOunces, final BigDecimal costPerOunce) {
        this.sender = sender;
        this.recipient = recipient;
        this.weightInOunces = weightInOunces;
        this.costPerOunce = costPerOunce;
    }

    public UserInformation getSender() {
        return sender;
    }

    public UserInformation getRecipient() {
        return recipient;
    }

    public BigDecimal calculateCost() {
        return weightInOunces.multiply(costPerOunce);
    }

    @Override
    public String toString() {
        return "From : " + sender + "\nTo : " + recipient;
    }

    public static final class Builder {
        private UserInformation sender;
        private UserInformation recipient;
        private BigDecimal weightInOunces;
        private BigDecimal costPerOunce;

        public Builder withSender(UserInformation sender) {
            this.sender = sender;
            return this;
        }

        public Builder withRecipient(UserInformation recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder withWeightInOunces(BigDecimal weightInOunces) {
            if (BigDecimal.ZERO.compareTo(weightInOunces) >= 0)
                throw new IllegalArgumentException("Weight should be positive value!");
            this.weightInOunces = weightInOunces;
            return this;
        }

        public Builder withCostPerOunce(BigDecimal costPerOunce) {
            if (BigDecimal.ZERO.compareTo(costPerOunce) >= 0)
                throw new IllegalArgumentException("Cost should be positive value!");
            this.costPerOunce = costPerOunce;
            return this;
        }

        public Package build() {
            return new Package(sender, recipient, weightInOunces, costPerOunce);
        }
    }
}
