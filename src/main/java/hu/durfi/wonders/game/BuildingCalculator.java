package hu.durfi.wonders.game;

import hu.durfi.wonders.action.InvalidActionException;
import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.card.Symbol;
import hu.durfi.wonders.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pudi on 2016.03.05..
 */
public class BuildingCalculator {
    private static BuildingCalculator ourInstance = new BuildingCalculator();

    public static BuildingCalculator getInstance() {
        return ourInstance;
    }

    private BuildingCalculator() {
    }

    /**
     * Check if player has the resources necessary for building a card. THIS WON'T CHECK
     * COINS!
     * @param player player doing the build
     * @param bought bought resources
     * @param c the card
     * @return true if player has the resources
     */
    public boolean canBuyWithBoughtResources(Player player, List<Symbol> bought, Card c) {
        // TODO: check if free build
        List<Symbol> available = new ArrayList<>(bought);
        available.addAll(player.getBuiltSymbols());
        for (Symbol s : c.cost) {
            if (!available.remove(s)) {
                return false;
            }
        }
        return true;
    }
}
