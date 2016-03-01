package hu.durfi.wonders.game;

import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Symbol;
import hu.durfi.wonders.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

/**
 * Created by pudi on 2016.02.29..
 */
public class StateTest {

    private State uut;

    @Before
    public void setUp() throws Exception {
        uut = new State();
    }

    @Test
    public void testCopyConstructorCopiesBuildings() {
        Player me = mock(Player.class);
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
        uut.coins.put(me, 5);

        State nextState = new State(uut);
        nextState.coins.put(me, 8);

        assertThat(nextState.coins, is(not(equalTo(uut.coins))));
        assertThat(nextState.coins.get(me), equalTo(uut.coins.get(me) + 3));
    }

    @Test
    public void testCopyConstructorCopiesSymbols() {
        Player me = mock(Player.class);
        Symbol symbol = Symbol.SHIELD;
        Map<Symbol, Integer> mySymbols = new HashMap<>();
        mySymbols.put(symbol, 2);
        uut.symbols.put(me, mySymbols);

        State nextState = new State(uut);
        nextState.addSymbol(me, symbol);

        assertThat(nextState.symbols, is(not(equalTo(uut.symbols))));
        assertThat(nextState.symbols.get(me).get(symbol), equalTo(uut.symbols.get(me).get(symbol) + 1));
    }

}