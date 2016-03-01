package hu.durfi.wonders.game;

import hu.durfi.wonders.action.Action;
import hu.durfi.wonders.action.InvalidActionException;
import hu.durfi.wonders.action.Purchase;
import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Deck;
import hu.durfi.wonders.player.DummyStrategy;
import hu.durfi.wonders.player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pudi on 2016.02.25..
 */
public class Game {
    public static final int NUMBER_OF_AGES = 3;
    public static final int NUMBER_OF_ROUNDS = 6;

    public int currentAge = 1;
    public int currentRound = 1;

    public State state;

    Deck deck = new Deck();
    List<Player> players;

    public static void main(String[] args) {
        Game game = new Game();
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("pudi", new DummyStrategy()));
        game.players = players;
        game.start();
    }

    public void start() {
        state = new State();
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

    private void playAction(Player p, Action action, State state, State nextState) throws InvalidActionException {

    }

    private void buy(Purchase purchase, State state, State nextState) throws InvalidActionException {

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
