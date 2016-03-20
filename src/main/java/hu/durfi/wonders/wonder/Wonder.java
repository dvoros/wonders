package hu.durfi.wonders.wonder;

import hu.durfi.wonders.action.InvalidActionException;
import hu.durfi.wonders.card.Symbol;

import java.util.List;

/**
 * Created by pudi on 2016.02.25..
 */
public abstract class Wonder {
    public static final int NUMBER_OF_STAGES = 3;

    public final String name;
    public final Symbol resource;
    public List<Stage> stages;

    public int currentStage;

    public Wonder(String name, Symbol resource) {
        this.name = name;
        this.resource = resource;
        this.setStages();
    }

    public void upgrade() throws InvalidActionException {
        if (this.currentStage >= stages.size()) {
            throw new InvalidActionException("Wonder already at maximum level: " + name);
        }
        this.currentStage += 1;
    }

    abstract void setStages();
}
