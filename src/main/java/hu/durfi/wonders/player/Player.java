package hu.durfi.wonders.player;

import hu.durfi.wonders.action.Action;
import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.game.State;
import hu.durfi.wonders.wonder.Wonder;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pudi on 2016.02.25..
 */
public class Player {

    public final String name;
    public final Strategy strategy;
    public Wonder wonder;
    public Collection<Card> cardsInHand;

    public Player(String name, Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public Action play(State state) {
        return this.strategy.play(this, state);
    }

    public Set<Card> cardsBuilt = new HashSet<Card>();
}
