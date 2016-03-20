package hu.durfi.wonders.game;

import hu.durfi.wonders.action.InvalidActionException;
import hu.durfi.wonders.action.Purchase;
import hu.durfi.wonders.card.Symbol;
import hu.durfi.wonders.player.Player;
import hu.durfi.wonders.player.Strategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

/**
 * Created by pudi on 2016.03.05..
 */
public class PurchaseCalculatorTest {

    private PurchaseCalculator uut;

    @Before
    public void init() {
        uut = PurchaseCalculator.getInstance();
    }

    @Test(expected = InvalidActionException.class)
    public void testIsEligibleForTradingPriceNotFromNeighbor() throws Exception {
        Player buyer = mock(Player.class);
        when(buyer.getLeftNeighbor()).thenReturn(mock(Player.class));
        when(buyer.getRightNeighbor()).thenReturn(mock(Player.class));
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = mock(Player.class);
        purchase.resource = Symbol.CLAY;
        uut.isEligibleForTradingPrice(purchase);
    }

    @Test
    public void testIsEligibleForTradingLeftNoTradingBrown() throws Exception {
        Player buyer = mock(Player.class);
        Player neighbor = mock(Player.class);
        when(buyer.getLeftNeighbor()).thenReturn(neighbor);
        when(buyer.hasSymbolBuilt(Symbol.TRADE_BROWN_LEFT)).thenReturn(false);
        when(neighbor.hasSymbolBuilt(Symbol.CLAY)).thenReturn(true);
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = neighbor;
        purchase.resource = Symbol.CLAY;

        assertFalse(uut.isEligibleForTradingPrice(purchase));
    }

    @Test
    public void testIsEligibleForTradingLeftNoTradingGray() throws Exception {
        Player buyer = mock(Player.class);
        Player neighbor = mock(Player.class);
        when(buyer.getLeftNeighbor()).thenReturn(neighbor);
        when(buyer.hasSymbolBuilt(Symbol.TRADE_GRAY)).thenReturn(false);
        when(neighbor.hasSymbolBuilt(Symbol.PAPYRUS)).thenReturn(true);
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = neighbor;
        purchase.resource = Symbol.PAPYRUS;

        assertFalse(uut.isEligibleForTradingPrice(purchase));
    }

    @Test
    public void testIsEligibleForTradingLeftNoResourceAtNeighbor() throws Exception {
        Player buyer = mock(Player.class);
        Player neighbor = mock(Player.class);
        when(buyer.getLeftNeighbor()).thenReturn(neighbor);
        when(buyer.hasSymbolBuilt(Symbol.TRADE_BROWN_LEFT)).thenReturn(true);
        when(neighbor.hasSymbolBuilt(Symbol.CLAY)).thenReturn(false);
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = neighbor;
        purchase.resource = Symbol.CLAY;

        assertFalse(uut.isEligibleForTradingPrice(purchase));
    }

    @Test
    public void testIsEligibleForTradingLeftEligibleBrown() throws Exception {
        Player buyer = mock(Player.class);
        Player neighbor = mock(Player.class);
        when(buyer.getLeftNeighbor()).thenReturn(neighbor);
        when(buyer.hasSymbolBuilt(Symbol.TRADE_BROWN_LEFT)).thenReturn(true);
        when(neighbor.hasSymbolBuilt(Symbol.CLAY)).thenReturn(true);
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = neighbor;
        purchase.resource = Symbol.CLAY;

        assertTrue(uut.isEligibleForTradingPrice(purchase));
    }

    @Test
    public void testIsEligibleForTradingLeftEligibleGray() throws Exception {
        Player buyer = mock(Player.class);
        Player neighbor = mock(Player.class);
        when(buyer.getLeftNeighbor()).thenReturn(neighbor);
        when(buyer.hasSymbolBuilt(Symbol.TRADE_GRAY)).thenReturn(true);
        when(neighbor.hasSymbolBuilt(Symbol.PAPYRUS)).thenReturn(true);
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = neighbor;
        purchase.resource = Symbol.PAPYRUS;

        assertTrue(uut.isEligibleForTradingPrice(purchase));
    }

    @Test
    public void testIsEligibleForTradingRightNoTradingBrown() throws Exception {
        Player buyer = mock(Player.class);
        Player neighbor = mock(Player.class);
        when(buyer.getRightNeighbor()).thenReturn(neighbor);
        when(buyer.hasSymbolBuilt(Symbol.TRADE_BROWN_RIGHT)).thenReturn(false);
        when(neighbor.hasSymbolBuilt(Symbol.CLAY)).thenReturn(true);
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = neighbor;
        purchase.resource = Symbol.CLAY;

        assertFalse(uut.isEligibleForTradingPrice(purchase));
    }

    @Test
    public void testIsEligibleForTradingRightNoTradingGray() throws Exception {
        Player buyer = mock(Player.class);
        Player neighbor = mock(Player.class);
        when(buyer.getRightNeighbor()).thenReturn(neighbor);
        when(buyer.hasSymbolBuilt(Symbol.TRADE_GRAY)).thenReturn(false);
        when(neighbor.hasSymbolBuilt(Symbol.PAPYRUS)).thenReturn(true);
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = neighbor;
        purchase.resource = Symbol.PAPYRUS;

        assertFalse(uut.isEligibleForTradingPrice(purchase));
    }

    @Test
    public void testIsEligibleForTradingRightNoResourceAtNeighbor() throws Exception {
        Player buyer = mock(Player.class);
        Player neighbor = mock(Player.class);
        when(buyer.getRightNeighbor()).thenReturn(neighbor);
        when(buyer.hasSymbolBuilt(Symbol.TRADE_BROWN_RIGHT)).thenReturn(true);
        when(neighbor.hasSymbolBuilt(Symbol.CLAY)).thenReturn(false);
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = neighbor;
        purchase.resource = Symbol.CLAY;

        assertFalse(uut.isEligibleForTradingPrice(purchase));
    }

    @Test
    public void testIsEligibleForTradingRightEligibleBrown() throws Exception {
        Player buyer = mock(Player.class);
        Player neighbor = mock(Player.class);
        when(buyer.getRightNeighbor()).thenReturn(neighbor);
        when(buyer.hasSymbolBuilt(Symbol.TRADE_BROWN_RIGHT)).thenReturn(true);
        when(neighbor.hasSymbolBuilt(Symbol.CLAY)).thenReturn(true);
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = neighbor;
        purchase.resource = Symbol.CLAY;

        assertTrue(uut.isEligibleForTradingPrice(purchase));
    }

    @Test
    public void testIsEligibleForTradingRightEligibleGray() throws Exception {
        Player buyer = mock(Player.class);
        Player neighbor = mock(Player.class);
        when(buyer.getRightNeighbor()).thenReturn(neighbor);
        when(buyer.hasSymbolBuilt(Symbol.TRADE_GRAY)).thenReturn(true);
        when(neighbor.hasSymbolBuilt(Symbol.PAPYRUS)).thenReturn(true);
        Purchase purchase = new Purchase();
        purchase.buyer = buyer;
        purchase.seller = neighbor;
        purchase.resource = Symbol.PAPYRUS;

        assertTrue(uut.isEligibleForTradingPrice(purchase));
    }




}