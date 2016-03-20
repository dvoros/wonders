package hu.durfi.wonders.card;

import java.util.*;

import static hu.durfi.wonders.card.Color.*;
import static hu.durfi.wonders.card.Symbol.*;

/**
 * Created by pudi on 2016.02.25..
 */
public class Deck {
    public final Collection<Card> cards;

    public Deck() {
        List<Card> cards = new ArrayList<>();

        // Age I
        cards.add(new Card(1, "lumber yard", BROWN, one(WOOD), none(), 0, 3));
        cards.add(new Card(1, "lumber yard", BROWN, one(WOOD), none(), 0, 4));
        cards.add(new Card(1, "stone pit", BROWN, one(STONE), none(), 0, 3));
        cards.add(new Card(1, "stone pit", BROWN, one(STONE), none(), 0, 5));
        cards.add(new Card(1, "clay pool", BROWN, one(CLAY), none(), 0, 3));
        cards.add(new Card(1, "clay pool", BROWN, one(CLAY), none(), 0, 5));
        cards.add(new Card(1, "ore vein", BROWN, one(ORE), none(), 0, 3));
        cards.add(new Card(1, "ore vein", BROWN, one(ORE), none(), 0, 4));

        cards.add(new Card(1, "pawnshop", BLUE, none(), none(), 3, 4));
        cards.add(new Card(1, "pawnshop", BLUE, none(), none(), 3, 7));
        cards.add(new Card(1, "baths", BLUE, none(), one(STONE), 3, 3));
        cards.add(new Card(1, "baths", BLUE, none(), one(STONE), 3, 7));

        // Age II
        cards.add(new Card(2, "sawmill", BROWN, two(WOOD), one(COIN), 0, 3));
        cards.add(new Card(2, "sawmill", BROWN, two(WOOD), one(COIN), 0, 4));

        // Age III
        cards.add(new Card(3, "pantheon", BLUE, none(), Arrays.asList(new Symbol[] {CLAY, CLAY, ORE, PAPYRUS, LOOM, GLASS}), 7, 3));
        cards.add(new Card(3, "pantheon", BLUE, none(), Arrays.asList(new Symbol[] {CLAY, CLAY, ORE, PAPYRUS, LOOM, GLASS}), 7, 7));

        this.cards = Collections.unmodifiableList(cards);
    }

    public Card getCard(String name) {
        return cards.stream().filter(c -> c.name == name).findFirst()
                .orElseThrow(() -> new RuntimeException("Card not found: " + name));
    }
}
