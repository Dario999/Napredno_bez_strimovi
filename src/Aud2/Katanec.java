package Aud2;

public class Katanec {

    private int combination;
    private boolean isOpen;

    public Katanec(int combination){
        this.combination = combination;
        this.isOpen = false;
    }

    public boolean open(int comb){
        this.isOpen = (comb == combination);
        return this.isOpen;
    }

    public boolean changeCombo(int oldCombination,int newCombination){
        boolean isCorrect = (this.combination == oldCombination);
        if(isCorrect){
            this.combination = newCombination;
        }
        return isCorrect;
    }

    public boolean isOpen(){
        return isOpen;
    }


    public static void main(String[] args) {


    }

}
