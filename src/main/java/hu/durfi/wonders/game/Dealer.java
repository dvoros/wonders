package hu.durfi.wonders.game;

import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Deck;
import hu.durfi.wonders.player.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pudi on 2016.04.30..
 */
public class Dealer {

    Deck deck = new Deck();
    private Game game;

    public Dealer(Game game) {
        this.game = game;
    }

    /**
     * Discard last card in players hand when an age ends.
     */
    public void discardLastCard() {
        for (Player p : game.getPlayers()) {
            if (p.cardsInHand.size() != 1) {
                throw new RuntimeException("Can't end round! More than one card in hand for player: " + p.name);
            }
            game.discardPile.add(p.cardsInHand.remove(0));
        }
    }

    /**
     * Deal new cards for a new age. Clears discard pile too.
     * @param age
     */
    public void dealNewAge(int age) {
        // Clear discard pile
        game.discardPile.clear();
        // Discard previous cards
        for (Player p : game.getPlayers()) {
            p.cardsInHand.clear();
        }
        // Deal cards
        Collection<Card> cardInThisAge = selectCardsForAge(age);
        Iterator<Card> iter = cardInThisAge.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Card c = iter.next();
            game.getPlayers().get(i % game.getPlayers().size()).cardsInHand.add(c);
            i ++;
        }
    }

    /**
     * Pass around cards when a round ends. Direction of passing around
     * depends on the current age.
     */
    public void passAroundCards() {
        List<List<Card>> cardsInHands = game.getPlayers().stream().map(p -> p.cardsInHand).collect(Collectors.toList());
        if (game.currentAge % 2 == 1) {
            cardsInHands.add(0, cardsInHands.remove(cardsInHands.size()-1));
        } else {
            cardsInHands.add(cardsInHands.remove(0));
        }
        for (int i = 0; i < game.getPlayers().size(); i ++) {
            game.getPlayers().get(i).cardsInHand = cardsInHands.get(i);
        }
    }

    /**
     * Shuffle and select enough cards for the current age.
     * @param age
     * @return
     */
    Collection<Card> selectCardsForAge(int age) {
        List<Card> cardsInAge = deck.cards.stream()
                .filter(c -> c.age == age && c.minPlayers >= game.getPlayers().size())
                .collect(Collectors.toList());
        Collections.shuffle(cardsInAge);
        return cardsInAge;

        // TODO: check if this sublist is needed or not when deck is complete:
        // return cardsInAge.subList(0, players.size() * 7);
    }
}
