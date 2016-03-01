package hu.durfi.wonders.player;

import hu.durfi.wonders.action.Action;
import hu.durfi.wonders.game.State;

/**
 * Created by pudi on 2016.02.25..
 */
public interface Strategy {
    Action play(Player player, State state);
}
