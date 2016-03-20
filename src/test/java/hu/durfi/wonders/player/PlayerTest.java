package hu.durfi.wonders.player;

import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Deck;
import hu.durfi.wonders.card.Symbol;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by pudi on 2016.03.05..
 */
public class PlayerTest {

    private Deck deck = new Deck();

    @Test
    public void testCountSymbolsBuiltNoCards() {
        Player uut = new Player("name", mock(Strategy.class));
        assertThat(uut.countSymbolsBuilt(Symbol.STONE), equalTo(0));
    }

    @Test
    public void testCountSymbolsBuiltNoSymbol() {
        Player uut = new Player("name", mock(Strategy.class));
        uut.cardsBuilt.add(deck.getCard("clay pool"));
        assertThat(uut.countSymbolsBuilt(Symbol.STONE), equalTo(0));
    }

    @Test
    public void testCountSymbolsBuiltOneSymbol() {
        Player uut = new Player("name", mock(Strategy.class));
        uut.cardsBuilt.add(deck.getCard("clay pool"));
        assertThat(uut.countSymbolsBuilt(Symbol.CLAY), equalTo(1));
    }

    @Test
    public void testCountSymbolsBuiltTwoSymbolsSameCard() {
        Player uut = new Player("name", mock(Strategy.class));
        uut.cardsBuilt.add(deck.getCard("sawmill"));
        assertThat(uut.countSymbolsBuilt(Symbol.WOOD), equalTo(2));
    }
}