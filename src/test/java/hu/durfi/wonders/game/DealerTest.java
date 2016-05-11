package hu.durfi.wonders.game;

import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by pudi on 2016.04.30..
 */
public class DealerTest {

    Dealer uut;
    Game gameMock;

    @Before
    public void init() {
        gameMock = mock(Game.class);
        uut = new Dealer(gameMock);
    }

    @Test
    public void testSelectCardsForAgeOnePlayer() throws Exception {
        final int num_of_players = 1;
        List<Player> players = mock(List.class);
        Mockito.when(players.size()).thenReturn(num_of_players);
        Mockito.when(gameMock.getPlayers()).thenReturn(players);
        Collection<Card> cards = uut.selectCardsForAge(1);
        assertTrue(cards.stream().allMatch( c -> c.minPlayers >= num_of_players));

        // TODO: when deck is complete, uncomment this assertion:
        // assertEquals(cards.size(), num_of_players * 7);
    }

    @Test
    public void testSelectCardsForAgeFivePlayers() throws Exception {
        final int num_of_players = 5;
        List<Player> players = mock(List.class);
        Mockito.when(players.size()).thenReturn(num_of_players);
        Mockito.when(gameMock.getPlayers()).thenReturn(players);
        Collection<Card> cards = uut.selectCardsForAge(1);
        assertTrue(cards.stream().allMatch( c -> c.minPlayers >= num_of_players));

        // TODO: when deck is complete, uncomment this assertion:
        // assertEquals(cards.size(), num_of_players * 7);
    }
}