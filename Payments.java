public interface Payments {
    int takePayment();

    String returnFunds();

    int deductFunds(int amount);
}
