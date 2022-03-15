package art.com.models;

public class UserInformation {
    private final String name;
    private final String address;
    private final String city;
    private final String state;
    private final String zipCode;

    private UserInformation(final String name, final String address, final String city, final String state, final String zipCode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return name + " " + address + " " + city + " " + state + " " + zipCode;
    }

    public static class Builder {
        private String name;
        private String address;
        private String city;
        private String state;
        private String zipCode;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withAddress(final String address) {
            this.address = address;
            return this;
        }

        public Builder withCity(final String city) {
            this.city = city;
            return this;
        }

        public Builder withState(final String state) {
            this.state = state;
            return this;
        }

        public Builder withZipCode(final String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public UserInformation build() {
            return new UserInformation(name, address, city, state, zipCode);
        }
    }
}
