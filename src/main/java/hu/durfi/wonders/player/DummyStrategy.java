package hu.durfi.wonders.player;

import hu.durfi.wonders.action.Action;
import hu.durfi.wonders.action.ActionType;
import hu.durfi.wonders.game.State;

/**
 * Always sells first card in hand.
 * Created by pudi on 2016.02.25..
 */
public class DummyStrategy implements Strategy {

    public Action play(Player player, State state) {
        Action action = new Action();
        action.type = ActionType.SELL;
        action.card = player.cardsInHand.iterator().next();
        return action;
    }
}
