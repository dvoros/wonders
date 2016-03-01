package hu.durfi.wonders.wonder;

import hu.durfi.wonders.card.Symbol;

import java.util.Collections;

/**
 * Created by pudi on 2016.02.25..
 */
public class Gizah extends Wonder {

    public Gizah(String name, Symbol resource) {
        super(name, resource);
    }

    @Override
    void setStages() {
        this.stages.add(new Stage(1, Collections.<Symbol>emptyList(), Symbol.two(Symbol.STONE), 3));
        this.stages.add(new Stage(2, Collections.<Symbol>emptyList(), Symbol.three(Symbol.WOOD), 5));
        this.stages.add(new Stage(3, Collections.<Symbol>emptyList(), Symbol.four(Symbol.STONE), 7));
    }
}
