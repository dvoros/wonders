package hu.durfi.wonders.player;

import hu.durfi.wonders.action.Action;
import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Symbol;
import hu.durfi.wonders.game.State;
import hu.durfi.wonders.wonder.Wonder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pudi on 2016.02.25..
 */
public class Player {

    public final String name;
    public final Strategy strategy;
    public Wonder wonder;
    public Collection<Card> cardsInHand;
    public Player leftNeighbor;
    public Player rightNeighbor;
    public Set<Card> cardsBuilt = new HashSet<Card>();

    public Player(String name, Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public Action play(State state) {
        return this.strategy.play(this, state);
    }


    public int countSymbolsBuilt(Symbol symbol) {
        int count = 0;
        for (Card c : cardsBuilt) {
            for (Symbol s : c.symbols) {
                if (symbol == s) {
                    count ++;
                }
            }
        }
        return count;
    }

    public boolean hasSymbolBuilt(Symbol symbol) {
        return countSymbolsBuilt(symbol) > 0;
    }

    public Collection<Symbol> getBuiltSymbols() {
        Collection<Symbol> allSymbols = new ArrayList<>();
        for (Card c : cardsBuilt) {
            allSymbols.addAll(c.symbols);
        }
        return allSymbols;
    }

    public Player getLeftNeighbor() {
        return leftNeighbor;
    }

    public void setLeftNeighbor(Player leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public Player getRightNeighbor() {
        return rightNeighbor;
    }

    public void setRightNeighbor(Player rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }
}
