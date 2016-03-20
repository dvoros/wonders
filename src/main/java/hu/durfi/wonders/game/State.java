package hu.durfi.wonders.game;

import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Symbol;
import hu.durfi.wonders.player.Player;

import java.util.*;

/**
 * Created by pudi on 2016.02.25..
 */
public class State {

    final List<Player> players;

    // TODO: make these private and access only through clever getters (getOrDefault)
    Map<Player, Set<Card>> buildings = new HashMap<>();
    Map<Player, Integer> coins = new HashMap<>();
    Map<Player, Map<Symbol, Integer>> tokens = new HashMap<>();

    /**
     * Copy constructor.
     * @param other
     */
    public State(State other) {
        // players
        this.players = other.players;
        // buildings
        for (Map.Entry<Player, Set<Card>> buildingsEntry : other.buildings.entrySet()) {
            Set<Card> copyOfCards = new HashSet<>(buildingsEntry.getValue());
            this.buildings.put(buildingsEntry.getKey(), copyOfCards);
        }
        // coins
        this.coins = new HashMap<>(other.coins);
        // tokens
        for (Map.Entry<Player, Map<Symbol, Integer>> symbolsEntry : other.tokens.entrySet()) {
            Map<Symbol, Integer> copyOfSymbols = new HashMap<>(symbolsEntry.getValue());
            this.tokens.put(symbolsEntry.getKey(), copyOfSymbols);
        }
    }

    public State(List<Player> players) {
        this.players = players;
    }

    public void addBuilding(Player player, Card building) {
        buildings.putIfAbsent(player, new HashSet<>());
        buildings.get(player).add(building);
    }

    public void addCoins(Player player, int amount) {
        coins.put(player, coins.getOrDefault(player, 0) + amount);
    }

    public void addToken(Player player, Symbol symbol) {
        Map<Symbol, Integer> playersSymbols = tokens.getOrDefault(player, new HashMap<>());
        playersSymbols.put(symbol, playersSymbols.getOrDefault(symbol, 0) + 1);
        tokens.put(player, playersSymbols);
    }

    public void transferCoins(int amount, Player from, Player to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("Can't transfer coins to themself: " + from.name);
        }
        if (coins.getOrDefault(from, 0) < amount) {
            throw new IllegalArgumentException("Not enough coins at " + from.name);
        }
        coins.put(from, coins.get(from) - amount);
        coins.put(to, coins.getOrDefault(to, 0) + amount);
    }
}
