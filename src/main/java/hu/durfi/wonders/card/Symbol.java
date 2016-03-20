package hu.durfi.wonders.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by pudi on 2016.02.25..
 */
public enum Symbol {
    CLAY, ORE, STONE, WOOD,
    GLASS, LOOM, PAPYRUS,
    COIN,
    SHIELD,
    TRADE_BROWN_RIGHT,
    TRADE_BROWN_LEFT,
    TRADE_GRAY;

    public boolean isBrown() {
        return Arrays.asList(new Symbol[] {CLAY, ORE, STONE, WOOD}).contains(this);
    }
    public boolean isGray() {
        return Arrays.asList(new Symbol[] {GLASS, LOOM, PAPYRUS}).contains(this);
    }

    public static List<Symbol> more(int count, Symbol symbol) {
        List<Symbol> list = new ArrayList<Symbol>();
        for (int i = 0; i < count; i ++) {
            list.add(symbol);
        }
        return list;
    }

    public static List<Symbol> none() {
        return Collections.emptyList();
    }

    public static List<Symbol> one(Symbol symbol) {
        return more(1, symbol);
    }

    public static List<Symbol> two(Symbol symbol) {
        return more(2, symbol);
    }

    public static List<Symbol> three(Symbol symbol) {
        return more(3, symbol);
    }

    public static List<Symbol> four(Symbol symbol) {
        return more(4, symbol);
    }
}
