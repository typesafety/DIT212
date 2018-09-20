package com.example.ohimarc.marc;

import com.example.ohimarc.marc.Model.*;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FlashCardGameUnitTest {
    @Test
    public void createEmptyFlashcardGame() {
        Deck d = new Deck("Test");
        FlashCardGame g = new FlashCardGame(d);

        String[] s = g.peekNextCard();

        assertTrue(s == null);
    }

    @Test
    public void createCard() {
        Deck d = new Deck("Test");
        d.addCard("Front", "Back");

        FlashCardGame g = new FlashCardGame(d);

        String[] s = g.peekNextCard();

        assertEquals(s[0], "Front");
        assertEquals(s[1], "Back");
    }

    @Test
    public void createCards() {
        Deck d = new Deck("Test");
        d.addCard("Front", "Back");

        FlashCardGame g = new FlashCardGame(d);

        String[] s = g.peekNextCard();

        assertEquals(s[0], "Front");
        assertEquals(s[1], "Back");
    }

    @Test
    public void stepNextCard() {
        Deck d = new Deck("Test");
        d.addCard("Front", "Back");
        d.addCard("Front1", "Back1");

        FlashCardGame g = new FlashCardGame(d);

        g.goToNextCard();
        String[] s = g.peekNextCard();

        assertEquals(s[0], "Front1");
        assertEquals(s[1], "Back1");
    }

}
