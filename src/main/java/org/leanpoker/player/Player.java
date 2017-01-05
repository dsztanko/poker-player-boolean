package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "Default Java folding player";

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

        return 0;
    }

    private static boolean checkMiddlePair(JsonObject jsonObject) {

        return false;
    }

    private static boolean checkPairInHand(JsonArray holeCards) {
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
                if (checkPairInHand(holeCards)) {

                    highPairPreflop(holeCards, actualTeam);
                }
            }
        }
        return 0;
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
        }
        return 0;
    }
}
