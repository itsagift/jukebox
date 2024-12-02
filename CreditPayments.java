/**
 * Class representing payments by credit.
 *
 * @author Chase Reynolds, Tess Avitabile
 */
public class CreditPayments implements Payments {
    private int funds = 0;

    /**
     * Takes a payment.
     * 
     * @param input the amount
     * @return amount taken as payment
     */
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

    /**
     * Returns all funds on credit card.
     * 
     * @return a string representing the fund return
     */
    public String returnFunds() {
        String toReturn = String.format("Returned %d cents on credit card", funds);
        funds = 0;
        return toReturn;
    }

    /**
     * Deducts the amount (if available).
     * 
     * @param amount the amount to deduct
     * @return the amount actually deducted
     */
    public int deductFunds(int amount) {
        int toDeduct = Math.min(funds, amount);
        funds -= toDeduct;
        System.out.printf("Deducting %d cents on credit card\n", toDeduct);
        return toDeduct;
    }
}
