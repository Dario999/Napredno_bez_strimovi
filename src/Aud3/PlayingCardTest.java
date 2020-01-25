package Aud3;

import javafx.css.StyleableProperty;

import java.awt.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class PlayingCard{

    public enum TYPE{
        HEAR,
        DIAMONDS,
        SPADES,
        CLUBS
    }

    private TYPE boja;
    private int rang;

    public PlayingCard(TYPE boja,int rang){
        this.boja = boja;
        this.rang = rang;
    }

    public TYPE getBoja(){
        return this.boja;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rang;
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        PlayingCard other = (PlayingCard)obj;
        if(rang != other.rang)
            return false;
        if (boja != other.boja)
            return false;
        return true;
    }

    public int getRang(){
        return this.rang;
    }

    @Override
    public String toString() {
        return String.format("(%d,%s)",rang,boja.toString());
    }
}

class Deck{

    private PlayingCard[] komplet;
    private int brKarti;
    private boolean[] izvlecena;

    public Deck(){
        this.brKarti = 52;
        this.komplet = new PlayingCard[52];
        izvlecena = new boolean[52];
        int counter = 0;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < PlayingCard.TYPE.values().length; j++) {
                komplet[counter] = new PlayingCard(PlayingCard.TYPE.values()[j],i+1);
                counter++;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < 52; i++) {
            st.append(komplet[i]);
            st.append(",");
        }
        return st.toString();
    }

    public void shuffle(){
        int x,y;
        for (int i = 0; i < 52; i++) {
            x = (int)(Math.random() * 52);
            y = (int)(Math.random() * 52);
            PlayingCard temp = komplet[x];
            komplet[x] = komplet[y];
            komplet[y] = temp;
        }
    }

    public PlayingCard dealCard(){
        if(brKarti == 0)
            return null;
        int card = (int)(52 * Math.random());
        if(!izvlecena[card]){
            --brKarti;
            izvlecena[card] = true;
            return komplet[card];
        }
        return dealCard();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        PlayingCard card;
        while ((card = deck.dealCard()) != null){
            System.out.println(card);
        }
    }

}

class MultipleDeck{

    private int brSpilobi;
    private Deck[] spilovi;

    public MultipleDeck(int brSpilobi){
        this.brSpilobi = brSpilobi;
        spilovi = new Deck[brSpilobi];
        for (int i = 0; i < brSpilobi; i++) {
            spilovi[i] = new Deck();
        }
    }

    public void shuffleAll(){
        for (Deck deck : spilovi){
            deck.shuffle();
        }
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        for (Deck deck : spilovi){
            st.append(deck);
            st.append("\n");
        }
        return st.toString();

        /*return Arrays.stream(spilovi)
                .map(Deck::toString)
                .collect(Collectors.joining("\n"));*/
    }

    public static void main(String[] args) {
        MultipleDeck md = new MultipleDeck(3);
        md.shuffleAll();
        System.out.println(md);
    }

}

public class PlayingCardTest {

    public static void main(String[] args) {

    }

}
