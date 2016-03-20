package hu.durfi.wonders.game;

import hu.durfi.wonders.action.InvalidActionException;
import hu.durfi.wonders.action.Purchase;
import hu.durfi.wonders.card.Symbol;

/**
 * Created by pudi on 2016.03.01..
 */
public class PurchaseCalculator {
    public static final int DEFAULT_PRICE = 2;
    /**
     * Price with trading post or marketplace
     */
    public static final int TRADING_PRICE = 1;

    private static PurchaseCalculator ourInstance = new PurchaseCalculator();

    public static PurchaseCalculator getInstance() {
        return ourInstance;
    }

    private PurchaseCalculator() {
    }

    public int getPrice(Purchase purchase) throws InvalidActionException {
        int price = DEFAULT_PRICE;
        if (isEligibleForTradingPrice(purchase)) {
            price = TRADING_PRICE;
        }
        return price;
    }

    public boolean isEligibleForTradingPrice(Purchase purchase) throws InvalidActionException {
        if (purchase.buyer.getLeftNeighbor() == purchase.seller) {
            if (purchase.resource.isBrown()
                    && purchase.buyer.hasSymbolBuilt(Symbol.TRADE_BROWN_LEFT)
                    && purchase.seller.hasSymbolBuilt(purchase.resource)) {
                return true;
            }
            if (purchase.resource.isGray()
                    && purchase.buyer.hasSymbolBuilt(Symbol.TRADE_GRAY)
                    && purchase.seller.hasSymbolBuilt(purchase.resource)) {
                return true;
            }
            return false;
        } else if (purchase.buyer.getRightNeighbor() == purchase.seller) {
            if (purchase.resource.isBrown()
                    && purchase.buyer.hasSymbolBuilt(Symbol.TRADE_BROWN_RIGHT)
                    && purchase.seller.hasSymbolBuilt(purchase.resource)) {
                return true;
            }
            if (purchase.resource.isGray()
                    && purchase.buyer.hasSymbolBuilt(Symbol.TRADE_GRAY)
                    && purchase.seller.hasSymbolBuilt(purchase.resource)) {
                return true;
            }
            return false;
        }
        throw new InvalidActionException(purchase.buyer + " buying from non-neighbor: " + purchase.seller);
    }
}
