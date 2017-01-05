package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;

public class Player {

    static final String VERSION = "Default Java folding player";
//    private static HashMap<String, Integer> cardValues;

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
        try {
            JsonObject jsonObject = request.getAsJsonObject();
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

    private static int afterFlop(JsonObject jsonObject) {
        JsonObject actualTeam = ownTeam(jsonObject);
        if (checkMiddlePair(actualTeam, jsonObject)){
            return actualTeam.get("stack").getAsInt();
        }
        return 0;
    }

    private static boolean checkMiddlePair(JsonObject actualTeam, JsonObject jsonObject){
        HashMap<String, Integer> cardValues = new HashMap<>();
        cardValues.put("A",14);
        cardValues.put("K",13);
        cardValues.put("Q",12);
        cardValues.put("J",11);
        for (int i = 2; i <= 10; i++) {
            cardValues.put(String.valueOf(i),i);
        }
        if (checkPairInHand(actualTeam)){
            System.out.println("chehck middle");
            int counter = 0;
            JsonObject holeCard = actualTeam.get("hole_cards").getAsJsonArray().get(0).getAsJsonObject();
            for (JsonElement communityCard : jsonObject.getAsJsonArray("community_cards")){
                System.out.println("FOR");
                System.out.println(cardValues.get("A").toString());
                System.out.println(cardValues.get(communityCard.getAsJsonObject().get("rank").getAsString()));
                if(cardValues.get(communityCard.getAsJsonObject().get("rank").getAsString())
                        > cardValues.get(holeCard.get("rank").getAsString())){
                    counter++;
                }
                if (counter > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkPairInHand(JsonObject actualTeam){
        JsonArray holeCards = actualTeam.getAsJsonArray("hole_cards");
        if (holeCards.get(0).getAsJsonObject().get("rank").getAsString()
                .equals(holeCards.get(1).getAsJsonObject().get("rank").getAsString())) {
            return true;
        }
        return false;
    }

    private static Integer preflop(JsonObject jsonObject) {
        for (JsonElement team : jsonObject.getAsJsonArray("players")) {
            JsonObject actualTeam = team.getAsJsonObject();
            if (actualTeam.get("name").getAsString().equals("BooLean")) {
                JsonArray holeCards = actualTeam.getAsJsonArray("hole_cards");
                if (checkPairInHand(actualTeam)) {
                    int bet = highPairPreflop(holeCards, actualTeam);
                    if (bet != 0) return bet;
                    else return mediumPairPreflop(holeCards, jsonObject);
                }
            }
        }
        return 0;
    }

    private static JsonObject ownTeam(JsonObject jsonObject){
        for (JsonElement team: jsonObject.getAsJsonArray("players")){
            JsonObject actualTeam = team.getAsJsonObject();
            if (actualTeam.get("name").getAsString().equals("BooLean")) {
                return actualTeam;
            }
        }
        return null;
    }

    public static void showdown(JsonElement game) {

    }

    private static Integer highPairPreflop(JsonArray holeCards, JsonObject actualTeam) {

        String card1 = holeCards.get(0).getAsJsonObject().get("rank").getAsString();

        switch (card1) {
            case "A":
                return actualTeam.get("stack").getAsInt();
            case "K":
                return actualTeam.get("stack").getAsInt();
            case "Q":
                return actualTeam.get("stack").getAsInt();
            case "J":
                return actualTeam.get("stack").getAsInt();
            default:
                return 0;
        }
    }

    private static Integer mediumPairPreflop(JsonArray holeCards, JsonObject jsonObject) {

        String card1 = holeCards.get(0).getAsJsonObject().get("rank").getAsString();

        if (card1.equals("10") || card1.equals("9") || card1.equals("8") || card1.equals("7")) {
            int currentBuyIn = Integer.parseInt(jsonObject.get("current_buy_in").toString());
            int smallBlind = Integer.parseInt(jsonObject.get("small_blind").toString());
            return currentBuyIn + (smallBlind * 2);
        }
        return 0;
    }

}
