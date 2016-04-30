package hu.durfi.wonders.player;

import hu.durfi.wonders.action.Action;
import hu.durfi.wonders.action.ActionType;
import hu.durfi.wonders.card.Card;
import hu.durfi.wonders.game.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by pudi on 2016.03.20..
 */
public class HumanInputStrategy implements Strategy {

    @Override
    public Action play(Player player, State state) {
        System.out.println("Your turn, " + player.name + "!");
        System.out.println("Cards in your hand: ");
        int i = 0;
        for (Card c : player.cardsInHand) {
            System.out.print(i + ": " + c.name + "  ");
            i ++;
        }
        System.out.println();

        Action action = new Action();

        // Read action type
        ActionType actionType = null;
        while (actionType == null) {
            System.out.print("Choose an action (b = build, s = sell, w = wonder): ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Error reading action!");
            }
            if ("b".equals(str)) {
                actionType = ActionType.BUILD;
            } else if ("s".equals(str)) {
                actionType = ActionType.SELL;
            } else if ("w".equals(str)) {
                actionType = ActionType.WONDER;
            }
        }
        action.type = actionType;

        // Read card
        Card card = null;
        while (card == null) {
            System.out.print("Select a card (0-" + (player.cardsInHand.size() - 1) + "): ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Integer num = 0;
            try {
                num = Integer.parseInt(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException("Error reading action!");
            }
            card = player.cardsInHand.get(num);
        }
        action.card = card;

        // TODO: input purchases if buying
        return action;
    }
}
