public interface Payments {
    int takePayment(String payment);

    String returnFunds();

    int deductFunds(int amount);
}
