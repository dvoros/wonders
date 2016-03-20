package hu.durfi.wonders.game;

import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Deck;
import hu.durfi.wonders.player.DummyStrategy;
import hu.durfi.wonders.player.Player;
import hu.durfi.wonders.player.Strategy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

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
        List<Player> players = mock(List.class);
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
        List<Player> players = mock(List.class);
        Mockito.when(players.size()).thenReturn(num_of_players);
        game.players = players;
        Collection<Card> cards = game.selectCardsForAge(num_of_players);
        assertTrue(cards.stream().allMatch( c -> c.minPlayers >= num_of_players));

        // TODO: when deck is complete, uncomment this assertion:
        // assertEquals(cards.size(), num_of_players * 7);
    }

    @Test
    public void testSetNeighbors3Players() throws Exception {
        List<Player> players = new ArrayList<>();
        Player one = new Player("first", mock(Strategy.class));
        Player two = new Player("second", mock(Strategy.class));
        Player three = new Player("third", mock(Strategy.class));
        players.add(one);
        players.add(two);
        players.add(three);
        game.setNeighbors(players);
        assertThat(one.getLeftNeighbor(), equalTo(three));
        assertThat(one.getRightNeighbor(), equalTo(two));
        assertThat(two.getLeftNeighbor(), equalTo(one));
        assertThat(two.getRightNeighbor(), equalTo(three));
        assertThat(three.getLeftNeighbor(), equalTo(two));
        assertThat(three.getRightNeighbor(), equalTo(one));
    }
}