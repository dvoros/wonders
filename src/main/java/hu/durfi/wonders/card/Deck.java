package hu.durfi.wonders.card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static hu.durfi.wonders.card.Color.*;
import static hu.durfi.wonders.card.Symbol.*;

/**
 * Created by pudi on 2016.02.25..
 */
public class Deck {
    public final Collection<Card> cards;

    public Deck() {
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(1, "lumber yard", BROWN, one(WOOD), none(), 0, 3));
        cards.add(new Card(1, "lumber yard", BROWN, one(WOOD), none(), 0, 4));
        cards.add(new Card(1, "stone pit", BROWN, one(STONE), none(), 0, 3));
        cards.add(new Card(1, "stone pit", BROWN, one(STONE), none(), 0, 5));
        cards.add(new Card(1, "clay pool", BROWN, one(CLAY), none(), 0, 3));
        cards.add(new Card(1, "clay pool", BROWN, one(CLAY), none(), 0, 5));
        cards.add(new Card(1, "ore vein", BROWN, one(ORE), none(), 0, 3));
        cards.add(new Card(1, "ore vein", BROWN, one(ORE), none(), 0, 4));

        this.cards = Collections.unmodifiableList(cards);
    }

    public Card getCard(String name) {
        return cards.stream().filter(c -> c.name == name).findFirst()
                .orElseThrow(() -> new RuntimeException("Card not found: " + name));
    }
}
