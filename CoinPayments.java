/**
 * Class representing payments by coin.
 *
 * @author Chase Reynolds, Tess Avitabile
 */
public class CoinPayments implements Payments {
    private static String acceptable_coin_chars[] = new String[] { "P", "N", "D", "Q", "H", "G" };
    private static int acceptable_coin_vals[] = new int[] { 1, 5, 10, 25, 50, 100 };

    private int changeSet[] = new int[6];
    private int currentChange = 0;

    /**
     * Takes a coin as payment.
     * 
     * @param payment the coin
     */
    public int takePayment(String payment) {
        for (int i = 0; i < acceptable_coin_chars.length; i++) {
            if (acceptable_coin_chars[i].equals(payment)) {
                changeSet[i]++;
                currentChange += acceptable_coin_vals[i];
                return acceptable_coin_vals[i];
            }
        }
        System.out.printf("Invalid coin %s\n", payment);
        return 0;
    }

    /**
     * Dispenses all coins.
     * 
     * @return a string representing the dispensed change
     */
    public String returnFunds() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dispensing change:");
        for (int i = acceptable_coin_vals.length - 1; i >= 0; i--) {
            int iCoins = currentChange / acceptable_coin_vals[i];
            iCoins = Math.min(changeSet[i], iCoins);
            changeSet[i] -= iCoins;
            currentChange -= iCoins * acceptable_coin_vals[i];
            sb.append("\n");
            sb.append(acceptable_coin_chars[i]);
            sb.append(": ");
            sb.append(iCoins);
        }
        return sb.toString();
    }

    /**
     * Deducts the amount, if available.
     * 
     * @param amount the amount to deduct
     * @return the amount that was deducted
     */
    public int deductFunds(int amount) {
        int toDeduct = Math.min(amount, currentChange);
        currentChange -= toDeduct;
        System.out.printf("Deducting %d in change\n", toDeduct);
        return toDeduct;
    }
}
