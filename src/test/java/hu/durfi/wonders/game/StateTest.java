package hu.durfi.wonders.game;

import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Symbol;
import hu.durfi.wonders.player.Player;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by pudi on 2016.02.29..
 */
public class StateTest {

    @Test
    public void testCopyConstructorCopiesBuildings() {
        Player me = mock(Player.class);
        State uut = new State(Arrays.asList(new Player[] {me}));
        Set<Card> myCards = new HashSet<>(Arrays.asList(new Card[] {mock(Card.class), mock(Card.class)}));
        uut.buildings.put(me, myCards);

        State nextState = new State(uut);
        nextState.buildings.get(me).add(mock(Card.class));

        assertThat(nextState.buildings, is(not(equalTo(uut.buildings))));
        assertThat(nextState.buildings.get(me).size(), is(equalTo(uut.buildings.get(me).size() + 1)));
    }

    @Test
    public void testCopyConstructorCopiesCoins() {
        Player me = mock(Player.class);
        State uut = new State(Arrays.asList(new Player[] {me}));
        uut.coins.put(me, 5);

        State nextState = new State(uut);
        nextState.coins.put(me, 8);

        assertThat(nextState.coins, is(not(equalTo(uut.coins))));
        assertThat(nextState.coins.get(me), equalTo(uut.coins.get(me) + 3));
    }

    @Test
    public void testCopyConstructorCopiesTokens() {
        Player me = mock(Player.class);
        State uut = new State(Arrays.asList(new Player[] {me}));

        Symbol symbol = Symbol.SHIELD;
        Map<Symbol, Integer> mySymbols = new HashMap<>();
        mySymbols.put(symbol, 2);
        uut.tokens.put(me, mySymbols);

        State nextState = new State(uut);
        nextState.addToken(me, symbol);

        assertThat(nextState.tokens, is(not(equalTo(uut.tokens))));
        assertThat(nextState.tokens.get(me).get(symbol), equalTo(uut.tokens.get(me).get(symbol) + 1));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testTransferCoinsNotEnough() {
        Player me = mock(Player.class);
        Player you = mock(Player.class);
        State uut = new State(Arrays.asList(new Player[] {me, you}));
        uut.transferCoins(5, me, you);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testTransferCoinsToMyself() {
        Player me = mock(Player.class);
        State uut = new State(Arrays.asList(new Player[] {me}));
        uut.transferCoins(5, me, me);
    }

    @Test
    public void testTransferCoinsOk() {
        Player me = mock(Player.class);
        Player you = mock(Player.class);
        State uut = new State(Arrays.asList(new Player[] {me, you}));
        uut.coins.put(me, 5);
        uut.transferCoins(5, me, you);
        assertThat(uut.coins.get(me), equalTo(0));
        assertThat(uut.coins.get(you), equalTo(5));
    }

}