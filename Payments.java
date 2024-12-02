/**
 * Interface representing a form of payment.
 *
 * @author Chase Reynolds, Tess Avitabile
 */
public interface Payments {
    /**
     * Takes a payment.
     * 
     * @param payment string representation of the payment
     * @return amount that was paid
     */
    int takePayment(String payment);

    /**
     * Returns all funds of this payment type.
     * 
     * @return string representation of returned funds
     */
    String returnFunds();

    /**
     * Deducts the amount (if available).
     * 
     * @param amount the amount to deduct
     * @return actual amount that was deducted
     */
    int deductFunds(int amount);
}
