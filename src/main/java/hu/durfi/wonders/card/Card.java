package hu.durfi.wonders.card;

import java.util.Collection;
import java.util.Collections;
import static hu.durfi.wonders.card.Symbol.*;
import static hu.durfi.wonders.card.Color.*;

/**
 * Immutable.
 * Created by pudi on 2016.02.25..
 */
public class Card {

    public final int age;
    public final String name;
    public final Color color;
    public final Collection<Symbol> symbols;
    public final Collection<Symbol> cost;
    public final int victoryPoints;
    public final int minPlayers;

    public Card(int age, String name, Color color, Collection<Symbol> symbols, Collection<Symbol> cost, int victoryPoints, int minPlayers) {
        this.age = age;
        this.name = name;
        this.color = color;
        this.symbols = symbols;
        this.cost = cost;
        this.victoryPoints = victoryPoints;
        this.minPlayers = minPlayers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return name.equals(card.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
