/**
 * Class representing a balance box.
 *
 * @author Chase Reynolds, Tess Avitabile
 */
public class BalanceBox {
    private CreditPayments creditPayments;
    private CoinPayments coinPayments;
    private int funds = 0;

    /**
     * Initializes a balance box.
     */
    public BalanceBox() {
        creditPayments = new CreditPayments();
        coinPayments = new CoinPayments();
    }

    /**
     * Reports the funds available in the balance box.
     * 
     * @return funds available
     */
    public int getFunds() {
        return funds;
    }

    /**
     * Accepts funds by coin or credit.
     * 
     * @param input       the coin or amount
     * @param paymentType coinr or credit
     */
    public void acceptFunds(String input, String paymentType) {
        switch (paymentType) {
            case "coin":
                funds += coinPayments.takePayment(input);
                return;
            case "credit":
                funds += creditPayments.takePayment(input);
                return;
        }
    }

    /**
     * Deducts the amount from the available funds, first by credit, then by coin.
     * 
     * @param amount the amount to deducts
     * @return whether the amount was successfully deducted
     */
    public boolean deductFunds(int amount) {
        if (amount > funds || amount < 0) {
            System.out.printf("Cannot deduct %d, balance is %d\n", amount, funds);
            return false;
        }
        funds -= amount;
        amount -= creditPayments.deductFunds(amount);
        coinPayments.deductFunds(amount);
        return true;
    }

    /**
     * Returns all funds available by credit and coin.
     */
    public void returnFunds() {
        System.out.println(creditPayments.returnFunds());
        System.out.println(coinPayments.returnFunds());
    }
}
