package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
    Set getCardSet() {
        return cardSet;
    }

    void setCardSet(HashSet cardSet) {
        this.cardSet = cardSet;
    }

    HashSet cardSet;

    protected ArrayList<Card> getDrawPile() {
        return drawPile;
    }

    private ArrayList<Card> drawPile;
    ArrayList<Card> secondPile;
    private HashSet<Card> cardsNotInDraw;
    private ArrayList<Card> discardPile;

    protected ArrayList<Card> getSecondPile() {
        return secondPile;
    }

    public Deck() {
        this.cardSet = new HashSet<Card>();
        this.drawPile = new ArrayList<Card>();
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                Card newCard = new Card(s, r);
                //System.out.println(newCard.toString());
                this.cardSet.add(newCard);
                drawPile.add(newCard);
            }
        }
        this.cardsNotInDraw = new HashSet<Card>();
        this.secondPile = new ArrayList<Card>();
    }

    public Deck(boolean hasStartingCards) {
        this.cardSet = new HashSet<Card>();
        this.secondPile = new ArrayList<Card>();
    }

    protected ArrayList<Card> shuffle() {
        for (int i=0; i<this.getDrawPile().size(); i++) {
            int j = ThreadLocalRandom.current().nextInt(0, this.getDrawPile().size());
            //Randomization not secure enough to bet real money
            Card c = drawPile.remove(j);
            secondPile.add(c);
        }
        this.drawPile = secondPile;
        cardsNotInDraw = new HashSet<Card>();
        secondPile = new ArrayList<Card>();
        discardPile = new ArrayList<Card>();
        return this.getDrawPile();
    }

    public void blindShuffle() {
        this.shuffle();
    }

    Card draw() {
        int j = ThreadLocalRandom.current().nextInt(0, this.getDrawPile().size());
        Card c = drawPile.remove(j);
        cardsNotInDraw.add(c);
        return c;
    }

    public ArrayList<Card> draw(int i) {
        ArrayList<Card> ret = new ArrayList<Card>();
        for (int k=0; k<i; k++) {
            ret.add(this.draw());
        }
        return ret;
    }

    protected ArrayList<Card> shuffleAll() {
        if (!cardsNotInDraw.isEmpty()) {
            Iterator<Card> iter = cardsNotInDraw.iterator();
            while (iter.hasNext()) {
                drawPile.add(iter.next());
            }
        }
        return this.shuffle();
    }

    public void blindShuffleAll() {
        this.shuffleAll();
    }

    public void sendDiscard(Card c) {
        // Method should not be used by itself; should be called by method that removes card from other location
        if (this.discardPile == null) {
            this.discardPile = new ArrayList<Card>();
        }
        this.discardPile.add(c);
    }
}
