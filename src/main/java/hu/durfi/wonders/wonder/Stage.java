package hu.durfi.wonders.wonder;

import hu.durfi.wonders.card.Symbol;

import java.util.Collection;
import java.util.List;

/**
 * Created by pudi on 2016.02.25..
 */
public class Stage {
    public final int number;
    public final List<Symbol> rewards;
    public final Collection<Symbol> cost;
    public final int victoryPoints;

    public boolean built = false;

    public Stage(int number, List<Symbol> rewards, Collection<Symbol> cost, int victoryPoints) {
        this.number = number;
        this.rewards = rewards;
        this.cost = cost;
        this.victoryPoints = victoryPoints;
    }
}
