import java.util.Scanner;

public class BalanceBox {
    private CreditPayments creditPayments;
    private CoinPayments coinPayments;
    private int funds = 0;
    private Scanner scan;

    public BalanceBox(Scanner scan) {
        this.scan = scan;
        creditPayments = new CreditPayments(scan);
        coinPayments = new CoinPayments(scan);
    }

    public void acceptFunds() {
        System.out.print("Enter 1 for credit, 2 for coin: ");
        String input = scan.nextLine();
        switch (input) {
            case "1":
                funds += creditPayments.takePayment();
                return;
            case "2":
                funds += coinPayments.takePayment();
                return;
            default:
                System.out.println("Must enter 1 or 2");
                return;
        }
    }

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

    public void returnFunds() {
        System.out.println(creditPayments.returnFunds());
        System.out.println(coinPayments.returnFunds());
    }

    public void promptBalance(){
        System.out.println(scan);
        while (true) {
            System.out.print("Enter 1 to add funds, 2 to make a purchase, 3 to return funds, 4 to quit: ");
            String input = scan.nextLine();
            switch (input) {
                case "1":
                    this.acceptFunds();
                    break;
                case "2":
                    System.out.print("Enter the purchase cost: ");
                    input = scan.nextLine();
                    try {
                        int amount = Integer.parseInt(input);
                        if (deductFunds(amount)) {
                            System.out.println("Successfully deducted funds");
                        } else {
                            System.out.println("Unable to deduct funds");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Must enter an integer");
                    }
                    break;
                case "3":
                    this.returnFunds();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

   
}
