package roo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CardGameShuffler {

    public static class Card {
        public String suit;
        public int rank;

        Card(String suit, int rank) {
            this.suit = suit;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "[RANK : " + rank + "] , [SUIT : " + suit + "]";
        }
    }

    private List<Card> cards;
    private int nextCardIndex = 0; // next card pointer to avoid same card draw

    public CardGameShuffler() {
        this.cards = createDeck();
        shuffle();
    }

    public List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String suit : suits) {
            for (int rank = 1; rank <= 13; rank++) {
                deck.add(new Card(suit, rank));
            }
        }
        return deck;
    }

    public void shuffle() {
        Random random = new Random();
        // moving right to left optimizes the shuffle operation
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1); // make sure whats shuffled is not used again
            // swap card with random index
            // shuffle logic
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
        nextCardIndex = 0; // remember to reset after shuffle
    }

    public Card getNextCard() {
        if (nextCardIndex >= cards.size()) return null; // cant draw anymore
        return cards.get(nextCardIndex++);
    }
}
