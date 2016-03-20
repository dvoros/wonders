package hu.durfi.wonders.game;

import static hu.durfi.wonders.card.Symbol.*;

import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Deck;
import hu.durfi.wonders.card.Symbol;
import hu.durfi.wonders.player.Player;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pudi on 2016.03.15..
 */
public class BuildingCalculatorTest {

    BuildingCalculator uut = BuildingCalculator.getInstance();
    Deck deck = new Deck();

    @Test
    public void testCanBuyWithBoughtResourcesNotEnoughWithoutBuying() throws Exception {
        Player player = mock(Player.class);
        when(player.getBuiltSymbols()).thenReturn(Arrays.asList(new Symbol[] {WOOD}));
        List<Symbol> bought = Collections.emptyList();
        Card card = deck.getCard("baths");

        assertFalse(uut.canBuyWithBoughtResources(player, bought, card));
    }

    @Test
    public void testCanBuyWithBoughtResourcesEnoughWithBuying() throws Exception {
        Player player = mock(Player.class);
        when(player.getBuiltSymbols()).thenReturn(Arrays.asList(new Symbol[] {WOOD}));
        List<Symbol> bought = one(STONE);
        Card card = deck.getCard("baths");

        assertTrue(uut.canBuyWithBoughtResources(player, bought, card));
    }

    @Test
    public void testCanBuyWithBoughtResourcesCanBuyPantheon() throws Exception {
        Player player = mock(Player.class);
        when(player.getBuiltSymbols()).thenReturn(Arrays.asList(new Symbol[] {WOOD, CLAY, ORE, PAPYRUS}));
        List<Symbol> bought = Arrays.asList(new Symbol[] {CLAY, LOOM, GLASS});
        Card card = deck.getCard("pantheon");

        assertTrue(uut.canBuyWithBoughtResources(player, bought, card));
    }

}