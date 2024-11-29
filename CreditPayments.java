import java.util.Scanner;

public class CreditPayments implements Payments {
    private int funds = 0;
    private Scanner scan;

    public CreditPayments(Scanner scan) {
        this.scan = scan;
    }

    public int takePayment(String input) {     
        try {
            int amount = Integer.parseInt(input);
            funds += amount;
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("Must enter a number");
        }
        return 0;
    }

    public String returnFunds() {
        String toReturn = String.format("Returned %d cents on credit card", funds);
        funds = 0;
        return toReturn;
    }

    public int deductFunds(int amount) {
        int toDeduct = Math.min(funds, amount);
        funds -= toDeduct;
        System.out.printf("Deducting %d cents on credit card\n", toDeduct);
        return toDeduct;
    }
}
