package com.example.ohimarc.marc.Model;

public class FlashCardGame extends Game {

    private int nextCard = 0;

    public FlashCardGame(Deck deck){
        super(deck,"FlashcardGame");
    }

    @Override
    public String[] peekNextCard() {
        return deck.getCardInfo(nextCard);
    }
    @Override
    public void goToNextCard() {
        nextCard++;
    }

    public int getNextCard(){
        return nextCard;
    }

    public int getDecksize(){
        return deck.getDeckSize();
    }
}
