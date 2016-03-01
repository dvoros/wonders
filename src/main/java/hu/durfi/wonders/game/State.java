package hu.durfi.wonders.game;

import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Symbol;
import hu.durfi.wonders.player.Player;

import java.util.*;

/**
 * Created by pudi on 2016.02.25..
 */
public class State {

    // TODO: make these private and access only through clever getters (getOrDefault)
    Map<Player, Set<Card>> buildings = new HashMap<>();
    Map<Player, Integer> coins = new HashMap<>();
    Map<Player, Map<Symbol, Integer>> symbols = new HashMap<>();

    public State() {};

    /**
     * Copy constructor.
     * @param other
     */
    public State(State other) {
        // buildings
        for (Map.Entry<Player, Set<Card>> buildingsEntry : other.buildings.entrySet()) {
            Set<Card> copyOfCards = new HashSet<>(buildingsEntry.getValue());
            this.buildings.put(buildingsEntry.getKey(), copyOfCards);
        }
        // coins
        this.coins = new HashMap<>(other.coins);
        // symbols
        for (Map.Entry<Player, Map<Symbol, Integer>> symbolsEntry : other.symbols.entrySet()) {
            Map<Symbol, Integer> copyOfSymbols = new HashMap<>(symbolsEntry.getValue());
            this.symbols.put(symbolsEntry.getKey(), copyOfSymbols);
        }
    }

    public void addBuilding(Player player, Card building) {
        buildings.putIfAbsent(player, new HashSet<>());
        buildings.get(player).add(building);
    }

    public void addCoins(Player player, int amount) {
        coins.put(player, coins.getOrDefault(player, 0) + amount);
    }

    public void addSymbol(Player player, Symbol symbol) {
        Map<Symbol, Integer> playersSymbols = symbols.getOrDefault(player, new HashMap<>());
        playersSymbols.put(symbol, playersSymbols.getOrDefault(symbol, 0) + 1);
        symbols.put(player, playersSymbols);
    }
}
