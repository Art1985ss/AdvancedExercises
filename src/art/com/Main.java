package art.com;

import art.com.models.OvernightPackage;
import art.com.models.Package;
import art.com.models.TwoDayPackage;
import art.com.models.UserInformation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UserInformation senderInformation = new UserInformation.Builder()
                .withName("Vasja")
                .withCity("Riga")
                .withState("Riga")
                .withAddress("Test street")
                .withZipCode("1000")
                .build();
        UserInformation receiverInformation = new UserInformation.Builder()
                .withName("Petja")
                .withCity("Vilnius")
                .withState("Vilnius")
                .withAddress("Test street2")
                .withZipCode("5000")
                .build();

        Package basePackage = new Package.Builder()
                .withSender(senderInformation)
                .withRecipient(receiverInformation)
                .withWeightInOunces(new BigDecimal("10"))
                .withCostPerOunce(new BigDecimal("20"))
                .build();

        TwoDayPackage twoDayPackage = new TwoDayPackage.Builder()
                .withPackage(basePackage)
                .withFlatFee(new BigDecimal("5"))
                .build();

        OvernightPackage overnightPackage = new OvernightPackage.Builder()
                .withPackage(basePackage)
                .withAdditionalFeePerOunce(new BigDecimal("5"))
                .build();

        //Testing calculations
        testCalculations(new BigDecimal("200"), basePackage.calculateCost());
        testCalculations(new BigDecimal("205"), twoDayPackage.calculateCost());
        testCalculations(new BigDecimal("250"), overnightPackage.calculateCost());

        List<Package> packages = new ArrayList<>();
        packages.add(basePackage);
        packages.add(twoDayPackage);
        packages.add(overnightPackage);

        //Print addresses
        printAddresses(packages);

        //Calculate sum
        calculateTotalPrice(packages);

        //this function allows user to interact with this program
        userInteraction();
    }

    private static void printAddresses(final List<Package> packages) {
        packages.stream()
                .map(Package::toString)
                .forEach(System.out::println);
    }

    private static void calculateTotalPrice(final List<Package> packages) {
        final BigDecimal sum = packages.stream()
                .map(Package::calculateCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total price : " + sum);
    }

    public static void testCalculations(final BigDecimal expected, final BigDecimal actual) {
        System.out.println("Calculations results are correct : " + expected.equals(actual));
    }

    public static void userInteraction() {
        System.out.println("Do you want to try this programme by inputting custom data? (y/n)");
        if ("n".equals(sc.nextLine())) {
            System.out.println("You have chosen not to try this program, have a good day!");
            return;
        }
        List<Package> packages = new ArrayList<>();
        do {
            do {
                System.out.println("Add package to the list : ");
                try {
                    UserInformation sender = createUserInfo("sender");
                    UserInformation recipient = createUserInfo("recipient");
                    Package.Builder packageBuilder = new Package.Builder()
                            .withSender(sender)
                            .withRecipient(recipient);
                    System.out.println("Please enter cost per ounce : ");
                    packageBuilder.withCostPerOunce(new BigDecimal(sc.nextLine()));
                    System.out.println("Please enter weight in ounces : ");
                    packageBuilder.withWeightInOunces(new BigDecimal(sc.nextLine()));
                    Package basePackage = packageBuilder.build();

                    System.out.println("Select package type");
                    System.out.println("1. Base package");
                    System.out.println("2. Two day package");
                    System.out.println("3. Overnight package");
                    switch (Integer.parseInt(sc.nextLine())) {
                        case 1:
                            packages.add(basePackage);
                            break;
                        case 2:
                            System.out.println("Please enter flat fee : ");
                            BigDecimal flatFee = new BigDecimal(sc.nextLine());
                            TwoDayPackage twoDayPackage = new TwoDayPackage.Builder().withPackage(basePackage).withFlatFee(flatFee).build();
                            packages.add(twoDayPackage);
                            break;
                        case 3:
                            System.out.println("Please enter additional fee : ");
                            BigDecimal additionalFee = new BigDecimal(sc.nextLine());
                            OvernightPackage overnightPackage = new OvernightPackage.Builder().withPackage(basePackage).withAdditionalFeePerOunce(additionalFee).build();
                            packages.add(overnightPackage);
                            break;
                        default:
                            throw new IllegalArgumentException("You should enter integers between 1 and 3 including!");

                    }
                } catch (IllegalArgumentException e) {
                    System.out.println(e);
                }
                System.out.println("Would you like to add more packages to the list? (y/n)");
            } while (!"n".equals(sc.nextLine()));
            printAddresses(packages);
            calculateTotalPrice(packages);
            packages.clear();
            System.out.println("Would you like to try this once more? (y/n)");
        } while (!"n".equals(sc.nextLine()));
        System.out.println("Thank tou for using this testing application! And have a nice day!");
    }

    private static UserInformation createUserInfo(final String user) {
        UserInformation.Builder userInformationBuilder = new UserInformation.Builder();
        final String s = "Enter ";
        System.out.println(s + user + " name : ");
        userInformationBuilder.withName(sc.nextLine());
        System.out.println(s + user + " address : ");
        userInformationBuilder.withAddress(sc.nextLine());
        System.out.println(s + user + " city : ");
        userInformationBuilder.withCity(sc.nextLine());
        System.out.println(s + user + " state : ");
        userInformationBuilder.withState(sc.nextLine());
        System.out.println(s + user + " zipCode : ");
        userInformationBuilder.withZipCode(sc.nextLine());
        return userInformationBuilder.build();
    }
}
