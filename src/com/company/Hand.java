package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Hand extends Deck {
    Deck parentDeck;
    //The main thing from the parent class that is used here is the Set cardSet.
    //The secondPile is also used when ordering the hand.

    public Hand(Deck parent, int handSize) {
        super(false);
        this.parentDeck = parent;
        HashSet<Card> handCards = new HashSet<Card>();
        for (int i=0; i<handSize; i++) {
            handCards.add(parentDeck.draw());
        }
        this.setCardSet(handCards);
    }

    public void discard(Card c) {
        this.cardSet.remove(c);
        parentDeck.sendDiscard(c);
    }

    @Override
    Card draw() {
        Card c = parentDeck.draw();
        this.cardSet.add(c);
        return c;
    }

    @Override
    public ArrayList<Card> draw(int i) {
        ArrayList<Card> ret = new ArrayList<Card>();
        for (int j=0; j<i; j++) {
            ret.add(draw());
        }
        return ret;
    }

    public String toString() {
        StringBuilder retString = new StringBuilder();
        retString.append("This hand contains: ");
        Iterator<Card> iter = this.cardSet.iterator();
        while (iter.hasNext()) {
            retString.append(iter.next().toString());
            retString.append("; ");
        }
        return retString.toString();
    }

    public String orderedToString() {
        ArrayList<Card> orderedHand = orderHand();
        Iterator<Card> iter = orderedHand.iterator();
        StringBuilder retString = new StringBuilder();
        Integer i = 1;
        while (iter.hasNext()) {
            retString.append(i.toString()+": ");
            retString.append(iter.next().toString());
            retString.append(";   ");
            i++;
        }
        return retString.toString();
    }

    public ArrayList<Card> orderHand() {
        ArrayList retList = new ArrayList<Card>();
        Iterator<Card> iter = this.cardSet.iterator();
        while (iter.hasNext()) {
            retList.add(iter.next());
        }
        this.secondPile = retList;
        return retList;
    }

}
