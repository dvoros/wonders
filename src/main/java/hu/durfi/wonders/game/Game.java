package hu.durfi.wonders.game;

import hu.durfi.wonders.action.Action;
import hu.durfi.wonders.action.ActionType;
import hu.durfi.wonders.action.InvalidActionException;
import hu.durfi.wonders.action.Purchase;
import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Deck;
import hu.durfi.wonders.card.Symbol;
import hu.durfi.wonders.player.DummyStrategy;
import hu.durfi.wonders.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by pudi on 2016.02.25..
 */
public class Game {
    private static final Logger log = Logger.getLogger(Game.class.getName());

    public static final int NUMBER_OF_AGES = 3;
    public static final int NUMBER_OF_ROUNDS = 6;

    public static final int SELL_VALUE_OF_CARD = 3;

    public int currentAge = 1;
    public int currentRound = 1;

    public State state;

    PurchaseCalculator purchaseCalc = PurchaseCalculator.getInstance();
    BuildingCalculator buildingCalc = BuildingCalculator.getInstance();

    Deck deck = new Deck();
    List<Card> discardPile = new ArrayList<>();
    List<Player> players;

    public static void main(String[] args) {
        Game game = new Game();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("pudi", new DummyStrategy()));
        game.players = players;

        game.start();
    }

    void setNeighbors(List<Player> players) {
        for (int i = 0; i < players.size(); i ++) {
            Player p = players.get(i);
            p.setLeftNeighbor(players.get((i-1+players.size())%players.size()));
            p.setRightNeighbor(players.get((i+1)%players.size()));
        }
    }

    public void start() {
        state = new State(players);
        for (int i = 0; i < NUMBER_OF_AGES; i ++) {
            playAge(i);
        }
    }

    private void playAge(int age) {
        for (int i = 0; i < NUMBER_OF_ROUNDS; i ++) {
            playRound(age, i);
        }
    }

    private void playRound(int age, int i) {
        State nextState = new State(state);
        for (Player p : players) {
            Action action = p.play(state);
            try {
                playAction(p, action, state, nextState);
            } catch (InvalidActionException e) {
                // TODO: or throw away a random card maybe?
                throw new RuntimeException(p.name + " returned invalid action. Can not continue, fix your strategy please!", e);
            }
        }
    }

    private void playAction(Player player, Action action, State state, State nextState) throws InvalidActionException {
        if (action.type == ActionType.BUILD) {
            if (player.cardsBuilt.contains(action.card)) {
                throw new InvalidActionException(player.name + " can't build already built card: " + action.card.name);
            }
            // Do purchases
            List<Symbol> bought = new ArrayList<>();
            for (Purchase p : action.purchases) {
                doPurchase(p, state, nextState);
                bought.add(p.resource);
            }

            // check if enough resources
            if (!buildingCalc.canBuyWithBoughtResources(player, bought, action.card)) {
                throw new InvalidActionException(player.name + "doesn't have enough resources to build this card: " + action.card.name);
            }
            // pay coin cost
            // TODO: don't pay if free build
            payCoinCost(player, action.card, state, nextState);
            
            player.cardsBuilt.add(action.card);
        } else if (action.type == ActionType.SELL) {
            nextState.addCoins(player, SELL_VALUE_OF_CARD);
            discardPile.add(action.card);
        } else if (action.type == ActionType.WONDER) {
            player.wonder.upgrade();
        }
        player.cardsInHand.remove(action.card);
    }

    void payCoinCost(Player player, Card card, State state, State nextState) throws InvalidActionException {
        int coinCost = (int)card.cost.stream().filter(s -> s == Symbol.COIN).count();
        if (state.coins.get(player) >= coinCost) {
            nextState.addCoins(player, (-1) * coinCost);
        } else {
            throw new InvalidActionException("Not enough coins to buy this card.");
        }
    }

    void doPurchase(Purchase purchase, State state, State nextState) throws InvalidActionException {
        if (state.coins.get(purchase.buyer) >= purchaseCalc.getPrice(purchase)) {
            nextState.transferCoins(purchaseCalc.getPrice(purchase), purchase.buyer, purchase.seller);
        } else {
            throw new InvalidActionException(purchase.buyer + " doesn't have enough coins to buy "
                    + purchase.resource + " from " + purchase.seller);
        }
    }

    public Collection<Card> selectCardsForAge(int age) {
        List<Card> cardsInAge = deck.cards.stream()
                .filter(c -> c.age == age && c.minPlayers >= players.size())
                .collect(Collectors.toList());
        Collections.shuffle(cardsInAge);
        return cardsInAge;

        // TODO: check if this sublist is needed or not when deck is complete:
        // return cardsInAge.subList(0, players.size() * 7);
    }
}
