package Aud4;


import java.util.Arrays;

abstract class Account{

    private String ime;
    private int brSmetka;
    private double sostojba;

    public Account(String ime,int brSmetka,double sostojba){
        this.ime = ime;
        this.brSmetka = brSmetka;
        this.sostojba = sostojba;
    }

    public double getCurrentAmount(){
        return sostojba;
    }

    public void addAmount(double amount){
        sostojba += amount;
    }

    public void withdrawAmount(double amount){
        sostojba -= amount;
    }

}


//okej
interface InterestBearingAccount{
    public void addInterest();
}

class Bank{

    private Account[] accounts;
    private int totalAccounts;
    private int max;

    public Bank(int max){
        this.totalAccounts = 0;
        this.max = max;
        accounts = new Account[max];
    }

    public void addAccount(Account account){
        if(totalAccounts == accounts.length){
            //account = Arrays.copyOf(accounts,max*2);
        }
        accounts[totalAccounts++] = account;
    }

    public double totalAssets(){
        double sum = 0;
        for(Account account : accounts){
            sum += account.getCurrentAmount();
        }
        return sum;
    }

    public void addInterest(){
        for (Account account : accounts){
            if(account instanceof InterestBearingAccount){
                InterestBearingAccount iba = (InterestBearingAccount) account;
                iba.addInterest();
            }
        }
    }

}


class NonInterestCheckingAccount extends Account{

    public NonInterestCheckingAccount(String name,int num,double amo){
        super(name,num,amo);
    }

}


// okej
class InterestCheckingAccount extends Account implements InterestBearingAccount{

    public static final double INTEREST_RATE = .03;   // 3%

    public InterestCheckingAccount(String name,int number,double amount){
        super(name,number,amount);
    }

    @Override
    public void addInterest(){
        addAmount(getCurrentAmount() * INTEREST_RATE);
    }

}

class PlatinumCheckingAccount extends Account implements InterestBearingAccount{

    public PlatinumCheckingAccount(String name,int number,double amount){
        super(name,number,amount);
    }

    @Override
    public void addInterest(){
        addAmount(getCurrentAmount() * InterestCheckingAccount.INTEREST_RATE * 2);
    }


}



public class BankTest {
}
