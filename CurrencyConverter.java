import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Currency Converter");
        System.out.println("Available currencies: USD, EUR, GBP, JPY, INR ");
        System.out.print("Enter the base currency: ");
        String baseCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Enter the target currency: ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        double exchangeRate = ExchangeRateAPI.getExchangeRate(baseCurrency, targetCurrency);

        if (exchangeRate == -1) {
            System.out.println("Failed to fetch exchange rates. Please try again later.");
            return;
        }

        System.out.print("Enter the amount to convert from " + baseCurrency + " to " + targetCurrency + ": ");
        double amount = scanner.nextDouble();

        double convertedAmount = amount * exchangeRate;

        System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);

        scanner.close();
    }
}

class ExchangeRateAPI {
    public static double getExchangeRate(String baseCurrency, String targetCurrency) {
        if (baseCurrency.equals("USD") && targetCurrency.equals("EUR")) {
            return 0.85;
        } else if (baseCurrency.equals("USD") && targetCurrency.equals("GBP")) {
            return 0.72;
        } else if (baseCurrency.equals("USD") && targetCurrency.equals("JPY")) {
            return 110.25;
        } else if (baseCurrency.equals("USD") && targetCurrency.equals("INR")) {
            return 73.25;
        } else if (baseCurrency.equals("EUR") && targetCurrency.equals("USD")) {
            return 1.18;
        } else if (baseCurrency.equals("EUR") && targetCurrency.equals("GBP")) {
            return 0.85;
        } else if (baseCurrency.equals("EUR") && targetCurrency.equals("JPY")) {
            return 130.27;
        } else if (baseCurrency.equals("EUR") && targetCurrency.equals("INR")) {
            return 88.32;
        } else if (baseCurrency.equals("GBP") && targetCurrency.equals("USD")) {
            return 1.39;
        } else if (baseCurrency.equals("GBP") && targetCurrency.equals("EUR")) {
            return 1.17;
        } else if (baseCurrency.equals("GBP") && targetCurrency.equals("JPY")) {
            return 153.54;
        } else if (baseCurrency.equals("GBP") && targetCurrency.equals("INR")) {
            return 104.32;
        } else if (baseCurrency.equals("JPY") && targetCurrency.equals("USD")) {
            return 0.0091;
        } else if (baseCurrency.equals("JPY") && targetCurrency.equals("EUR")) {
            return 0.0077;
        } else if (baseCurrency.equals("JPY") && targetCurrency.equals("GBP")) {
            return 0.0065;
        } else if (baseCurrency.equals("JPY") && targetCurrency.equals("INR")) {
            return 0.68;
        } else if (baseCurrency.equals("INR") && targetCurrency.equals("USD")) {
            return 0.014;
        } else if (baseCurrency.equals("INR") && targetCurrency.equals("EUR")) {
            return 0.011;
        } else if (baseCurrency.equals("INR") && targetCurrency.equals("GBP")) {
            return 0.0096;
        } else if (baseCurrency.equals("INR") && targetCurrency.equals("JPY")) {
            return 1.47;
        } else {
            return -1;
        }
    }
}
