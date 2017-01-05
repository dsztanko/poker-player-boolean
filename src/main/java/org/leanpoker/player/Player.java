package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class Player {

    static final String VERSION = "Sky is the limit";
    private static HashMap<String, Integer> cardValues;
    private static int currentBuyIn;
    private static int smallBlind;

//    {
//        cardValues = new HashMap<>();
//        cardValues.put("A",14);
//        cardValues.put("K",13);
//        cardValues.put("Q",12);
//        cardValues.put("J",11);
//        for (int i = 2; i <= 10; i++) {
//            cardValues.put(String.valueOf(i),i);
//        }
//    }

    public static int betRequest(JsonElement request) {

        cardValues = new HashMap<>();
        cardValues.put("A", 14);
        cardValues.put("K", 13);
        cardValues.put("Q", 12);
        cardValues.put("J", 11);
        for (int i = 2; i <= 10; i++) {
            cardValues.put(String.valueOf(i), i);
        }
        try {
            JsonObject jsonObject = request.getAsJsonObject();
            currentBuyIn = Integer.parseInt(jsonObject.get("current_buy_in").toString());
            smallBlind = Integer.parseInt(jsonObject.get("small_blind").toString());
            // TODO: work in this
            switch (jsonObject.getAsJsonArray("community_cards").size()) {
                case 0: {
                    return preflop(jsonObject);
                }
                case 3:
                    return afterFlop(jsonObject);
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    // AFTER FLOP
    private static int afterFlop(JsonObject jsonObject) {
        JsonObject actualTeam = ownTeam(jsonObject);
        if (checkMiddlePair(actualTeam, jsonObject)) {
            return actualTeam.get("stack").getAsInt();
        }
        if (checkPairInHand(actualTeam)) {
            return drillAfterFlop(jsonObject, actualTeam);
        }
        return 0;
    }

    // check drill
    private static int drillAfterFlop(JsonObject jsonObject, JsonObject actualTeam) {
        JsonObject holeCard = actualTeam.get("hole_cards").getAsJsonArray().get(0).getAsJsonObject();
        for (JsonElement communityCard : jsonObject.getAsJsonArray("community_cards")) {
            if (cardValues.get(communityCard.getAsJsonObject().get("rank").getAsString())
                    == cardValues.get(holeCard.get("rank").getAsString())) {
                return actualTeam.get("stack").getAsInt();
            }
        }
        return 0;
    }

    private static boolean checkMiddlePair(JsonObject actualTeam, JsonObject jsonObject) {
        if (checkPairInHand(actualTeam)) {
            int counter = 0;
            JsonObject holeCard = actualTeam.get("hole_cards").getAsJsonArray().get(0).getAsJsonObject();
            for (JsonElement communityCard : jsonObject.getAsJsonArray("community_cards")) {
                if (cardValues.get(communityCard.getAsJsonObject().get("rank").getAsString())
                        > cardValues.get(holeCard.get("rank").getAsString())) {
                    counter++;
                }
                if (counter > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkPairInHand(JsonObject actualTeam) {
        JsonArray holeCards = actualTeam.getAsJsonArray("hole_cards");
        if (holeCards.get(0).getAsJsonObject().get("rank").getAsString()
                .equals(holeCards.get(1).getAsJsonObject().get("rank").getAsString())) {
            return true;
        }
        return false;
    }

    private static Integer preflop(JsonObject jsonObject) {
        int bet;
        for (JsonElement team : jsonObject.getAsJsonArray("players")) {
            JsonObject actualTeam = team.getAsJsonObject();
            if (actualTeam.get("name").getAsString().equals("BooLean")) {
                JsonArray holeCards = actualTeam.getAsJsonArray("hole_cards");
                // if PAIR
                if (checkPairInHand(actualTeam)) {
                    bet = highPairPreflop(holeCards, jsonObject);
                    if (bet != 0) return bet;
                    bet = mediumPairPreflop(holeCards, actualTeam);
                    if (bet != 0) return bet;
                }
                // is SAME COLOR but not pair
                if (checkSameColor(actualTeam)) {
                    bet = highCardAndSameColorInHandPreFlop(holeCards, actualTeam);
                    if (bet != 0) return bet;
                }
                bet = highCardInHandPreFlop(holeCards, actualTeam);
                if (bet != 0) return bet;
                bet = oneHighOneMiddleInHandPreFlop(holeCards, actualTeam);
                if (bet != 0) return bet;
            }
        }
        return 0;
    }

    // Check same color in hands
    private static boolean checkSameColor(JsonObject actualTeam) {
        JsonArray holeCards = actualTeam.getAsJsonArray("hole_cards");
        return holeCards.get(0).getAsJsonObject().get("suit").getAsString()
                .equals(holeCards.get(1).getAsJsonObject().get("suit").getAsString());
    }

    private static JsonObject ownTeam(JsonObject jsonObject) {
        for (JsonElement team : jsonObject.getAsJsonArray("players")) {
            JsonObject actualTeam = team.getAsJsonObject();
            if (actualTeam.get("name").getAsString().equals("BooLean")) {
                return actualTeam;
            }
        }
        return null;
    }

    public static void showdown(JsonElement game) {

    }

    // PREFLOOOP
    private static Integer highPairPreflop(JsonArray holeCards, JsonObject jsonObject) {

        String card1 = holeCards.get(0).getAsJsonObject().get("rank").getAsString();

        switch (card1) {
            case "A":
                return currentBuyIn + (smallBlind * 12);
            case "K":
                return currentBuyIn + (smallBlind * 12);
            case "Q":
                return currentBuyIn + (smallBlind * 12);
            case "J":
                return currentBuyIn + (smallBlind * 12);
            default:
                return 0;
        }
    }

    private static Integer mediumPairPreflop(JsonArray holeCards, JsonObject actualTeam) {

        String card1 = holeCards.get(0).getAsJsonObject().get("rank").getAsString();

        if (card1.equals("10") || card1.equals("9") || card1.equals("8")) {
            return actualTeam.get("stack").getAsInt()/6;
        }
        if ((card1.equals("7") || card1.equals("6") || card1.equals("5"))) {
            return actualTeam.get("stack").getAsInt()/12;
        }
        return 0;
    }


    private static Integer highCardInHandPreFlop(JsonArray holeCards, JsonObject actualTeam){
        String card1 = holeCards.get(0).getAsJsonObject().get("rank").getAsString();
        String card2 = holeCards.get(1).getAsJsonObject().get("rank").getAsString();
        System.out.println(cardValues.get(card1));

        if (cardValues.get(card1) > 10 && cardValues.get(card2) > 10) {
            return currentBuyIn + (smallBlind * 8);
        }
        return 0;
    }

    private static Integer highCardAndSameColorInHandPreFlop(JsonArray holeCards, JsonObject actualTeam) {
        String card1 = holeCards.get(0).getAsJsonObject().get("rank").getAsString();
        String card2 = holeCards.get(1).getAsJsonObject().get("rank").getAsString();

        if (cardValues.get(card1) >= 7 && cardValues.get(card2) > 10 || cardValues.get(card2) >= 7 && cardValues.get(card1) > 10) {
            if (currentBuyIn + (smallBlind * 5) < actualTeam.get("stack").getAsInt() / 6) {
                return currentBuyIn + (smallBlind * 5);
            }
        }
        return 0;
    }

    private static Integer oneHighOneMiddleInHandPreFlop(JsonArray holeCards, JsonObject actualTeam) {
        String card1 = holeCards.get(0).getAsJsonObject().get("rank").getAsString();
        String card2 = holeCards.get(1).getAsJsonObject().get("rank").getAsString();
        if ((cardValues.get(card1) >= 13 && (cardValues.get(card2) >= 8 && cardValues.get(card2) <= 10)) || (cardValues.get(card2) >= 13 && (cardValues.get(card1) >= 8 && cardValues.get(card1) <= 10))) {
            return actualTeam.get("stack").getAsInt()/10;
        }
        return 0;
    }
}
