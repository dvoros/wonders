package hu.durfi.wonders.game;

import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Deck;
import hu.durfi.wonders.player.DummyStrategy;
import hu.durfi.wonders.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pudi on 2016.02.25..
 */
public class GameTest {

    private Deck deck;
    private Game game;

    @Before
    public void setUp() throws Exception {
        deck = new Deck();
        game = new Game();
    }

    @Test
    public void testSelectCardsForAgeOnePlayer() throws Exception {
        final int num_of_players = 1;
        List<Player> players = Mockito.mock(List.class);
        Mockito.when(players.size()).thenReturn(num_of_players);
        game.players = players;
        Collection<Card> cards = game.selectCardsForAge(num_of_players);
        assertTrue(cards.stream().allMatch( c -> c.minPlayers >= num_of_players));

        // TODO: when deck is complete, uncomment this assertion:
        // assertEquals(cards.size(), num_of_players * 7);
    }

    @Test
    public void testSelectCardsForAgeFivePlayers() throws Exception {
        final int num_of_players = 5;
        List<Player> players = Mockito.mock(List.class);
        Mockito.when(players.size()).thenReturn(num_of_players);
        game.players = players;
        Collection<Card> cards = game.selectCardsForAge(num_of_players);
        assertTrue(cards.stream().allMatch( c -> c.minPlayers >= num_of_players));

        // TODO: when deck is complete, uncomment this assertion:
        // assertEquals(cards.size(), num_of_players * 7);
    }
}