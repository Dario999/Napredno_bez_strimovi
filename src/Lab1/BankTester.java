package Lab1;

import java.util.*;
import java.util.stream.Collectors;

class Account{

    private String name;
    private long id;
    public String balance;

    public Account(String name,String balance){
        this.name = name;
        this.balance = balance;
        this.id = new Random().nextLong();
    }

    public String getBalance(){
        return balance;
    }

    public void setBalance(float balance){
        String newBalance = balance + "$";
        this.balance = newBalance;
    }

    public String getName(){
        return name;
    }

    public long getId(){
        return id;
    }

    public void setBalance(String balance){
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nBalance: %s\n",this.name,this.balance);
    }
}

abstract class Transaction{

    private final long fromId;
    private final long toId;
    private final String description;
    private final String amount;

    public Transaction(long fromId,long toId,String description,String amount){
        this.fromId = fromId;
        this.toId = toId;
        this.description = description;
        this.amount = amount;
    }

    public String getDescription(){
        return description;
    }

    public String getAmount(){
        return amount;
    }

    public long getFromId(){
        return fromId;
    }

    public long getToId(){
        return toId;
    }


}

class FlatAmountProvisionTransaction extends Transaction{

    final float flatProvision;

    public FlatAmountProvisionTransaction(long fromId,long toId,String amount,String flatProvision){
        super(fromId,toId,"FlatAmount",amount);
        this.flatProvision = Float.parseFloat(flatProvision.substring(0,flatProvision.length()-1));
    }

    public String getFlatAmount(){
        return getAmount();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public float getFlatProvision(){
        return flatProvision;
    }

}

class FlatPercentProvisionTransaction extends Transaction{

    final int centPerDolar;
    //final float percentProvision;


    public FlatPercentProvisionTransaction(long fromId,long toId,String amount,int centPerDolar){
        super(fromId,toId,"FlatPercent",amount);
        String temp = amount.substring(0,amount.length()-1);
        float sumAmount = Float.parseFloat(temp) * centPerDolar;
        String newAmount = sumAmount + "$";
        this.centPerDolar = centPerDolar;
        // treba da se presmeta amount * centPerDolar
    }

    public int getPercent(){
        return centPerDolar;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

class Bank{

    String name;
    Account accounts[];
    float sumTranscations;
    float totalProvision;

    public Bank(String name,Account accounts[]){
        this.name = name;
        this.accounts = accounts;
        this.sumTranscations = 0;
    }

    public boolean makeTransaction(Transaction t){
        long fromId = t.getFromId();
        long toId = t.getToId();
        String amount = t.getAmount();
        float amountFloat = Float.parseFloat(amount.substring(0,amount.length()-1));
        Account accountFrom = null;
        Account accountTo = null;
        int indexFrom = 0;
        int indexTo = 0;
        for (int i = 0; i < accounts.length; i++) {
            if(accounts[i].getId() == fromId){
                accountFrom = accounts[i];
                indexFrom = i;
            }
            if(accounts[i].getId() == toId){
                accountTo = accounts[i];
                indexTo = i;
            }
        }
        if(accountFrom == null || accountTo == null){
            return false;
        }
        float balanceFloat = Float.parseFloat(accountFrom.getBalance().substring(0,accountFrom.getBalance().length()-1));
        if(balanceFloat < amountFloat){
            return false;
        }else{
            accounts[indexFrom].setBalance(balanceFloat-amountFloat);
            float balanceToFloat = Float.parseFloat(accountTo.getBalance().substring(0,accountFrom.getBalance().length()-1));
            accounts[indexTo].setBalance(balanceToFloat+amountFloat);
            return true;
        }
    }

    public String totalTransfers(){
        return sumTranscations + "$";
    }

    public String totalProvision(){
        return "prr";
    }

    public Account[] getAccounts(){
        return accounts;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder("");
        st.append("Name: " + name + "\n");
        st.append("\n");
        for(Account temp : accounts){
            st.append(temp.toString());
        }
        return st.toString();
    }
}

public class BankTester {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        String test_type = jin.nextLine();
        switch (test_type) {
            case "typical_usage":
                testTypicalUsage(jin);
                break;
            case "equals":
                testEquals();
                break;
        }
        jin.close();
    }

    private static void testEquals() {
        Account a1 = new Account("Andrej", "20.00$");
        Account a2 = new Account("Andrej", "20.00$");
        Account a3 = new Account("Andrej", "30.00$");
        Account a4 = new Account("Gajduk", "20.00$");
        List<Account> all = Arrays.asList(a1, a2, a3, a4);
        if (!(a1.equals(a1)&&!a1.equals(a2)&&!a2.equals(a1) && !a3.equals(a1)
                && !a4.equals(a1)
                && !a1.equals(null))) {
            System.out.println("Your account equals method does not work properly.");
            return;
        }
        Set<Long> ids = all.stream().map(Account::getId).collect(Collectors.toSet());
        if (ids.size() != all.size()) {
            System.out.println("Different accounts have the same IDS. This is not allowed");
            return;
        }
        FlatAmountProvisionTransaction fa1 = new FlatAmountProvisionTransaction(10, 20, "20.00$", "10.00$");
        FlatAmountProvisionTransaction fa2 = new FlatAmountProvisionTransaction(20, 20, "20.00$", "10.00$");
        FlatAmountProvisionTransaction fa3 = new FlatAmountProvisionTransaction(20, 10, "20.00$", "10.00$");
        FlatAmountProvisionTransaction fa4 = new FlatAmountProvisionTransaction(10, 20, "50.00$", "50.00$");
        FlatAmountProvisionTransaction fa5 = new FlatAmountProvisionTransaction(30, 40, "20.00$", "10.00$");
        FlatPercentProvisionTransaction fp1 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
        FlatPercentProvisionTransaction fp2 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 10);
        FlatPercentProvisionTransaction fp3 = new FlatPercentProvisionTransaction(10, 10, "20.00$", 10);
        FlatPercentProvisionTransaction fp4 = new FlatPercentProvisionTransaction(10, 20, "50.00$", 10);
        FlatPercentProvisionTransaction fp5 = new FlatPercentProvisionTransaction(10, 20, "20.00$", 30);
        FlatPercentProvisionTransaction fp6 = new FlatPercentProvisionTransaction(30, 40, "20.00$", 10);
        if (fa1.equals(fa1) &&
                !fa2.equals(null) &&
                fa2.equals(fa1) &&
                fa1.equals(fa2) &&
                fa1.equals(fa3) &&
                !fa1.equals(fa4) &&
                !fa1.equals(fa5) &&
                !fa1.equals(fp1) &&
                fp1.equals(fp1) &&
                !fp2.equals(null) &&
                fp2.equals(fp1) &&
                fp1.equals(fp2) &&
                fp1.equals(fp3) &&
                !fp1.equals(fp4) &&
                !fp1.equals(fp5) &&
                !fp1.equals(fp6)) {
            System.out.println("Your transactions equals methods do not work properly.");
            return;
        }
        Account accounts[] = new Account[]{a1, a2, a3, a4};
        Account accounts1[] = new Account[]{a2, a1, a3, a4};
        Account accounts2[] = new Account[]{a1, a2, a3};
        Account accounts3[] = new Account[]{a1, a2, a3, a4};

        Bank b1 = new Bank("Test", accounts);
        Bank b2 = new Bank("Test", accounts1);
        Bank b3 = new Bank("Test", accounts2);
        Bank b4 = new Bank("Sample", accounts);
        Bank b5 = new Bank("Test", accounts3);

        if (!(b1.equals(b1) &&
                !b1.equals(null) &&
                !b1.equals(b2) &&
                !b2.equals(b1) &&
                !b1.equals(b3) &&
                !b3.equals(b1) &&
                !b1.equals(b4) &&
                b1.equals(b5))) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        accounts[2] = a1;
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        long from_id = a2.getId();
        long to_id = a3.getId();
        Transaction t = new FlatAmountProvisionTransaction(from_id, to_id, "3.00$", "3.00$");
        b1.makeTransaction(t);
        if (b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        b5.makeTransaction(t);
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        System.out.println("All your equals methods work properly.");
    }

    private static void testTypicalUsage(Scanner jin) {
        String bank_name = jin.nextLine();
        int num_accounts = jin.nextInt();
        jin.nextLine();
        Account accounts[] = new Account[num_accounts];
        for (int i = 0; i < num_accounts; ++i)
            accounts[i] = new Account(jin.nextLine(), jin.nextLine());
        Bank bank = new Bank(bank_name, accounts);
        while (true) {
            String line = jin.nextLine();
            switch (line) {
                case "stop":
                    return;
                case "transaction":
                    String descrption = jin.nextLine();
                    String amount = jin.nextLine();
                    String parameter = jin.nextLine();
                    int from_idx = jin.nextInt();
                    int to_idx = jin.nextInt();
                    jin.nextLine();
                    Transaction t = getTransaction(descrption, from_idx, to_idx, amount, parameter, bank);
                    System.out.println("Transaction amount: " + t.getAmount());
                    System.out.println("Transaction description: " + t.getDescription());
                    System.out.println("Transaction successful? " + bank.makeTransaction(t));
                    break;
                case "print":
                    System.out.println(bank.toString());
                    System.out.println("Total provisions: " + bank.totalProvision());
                    System.out.println("Total transfers: " + bank.totalTransfers());
                    System.out.println();
                    break;
            }
        }
    }

    private static Transaction getTransaction(String description, int from_idx, int to_idx, String amount, String o, Bank bank) {
        switch (description) {
            case "FlatAmount":
                return new FlatAmountProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, o);
            case "FlatPercent":
                return new FlatPercentProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, Integer.parseInt(o));
        }
        return null;
    }


}
