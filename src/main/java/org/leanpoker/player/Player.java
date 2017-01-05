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
            }
            if (jsonObject.getAsJsonArray("community_cards").size() == 0) {
                for (JsonElement team: jsonObject.getAsJsonArray("players")){
                    JsonObject actualTeam = team.getAsJsonObject();
                    if (actualTeam.get("name").getAsString().equals("BooLean")) {
                        JsonArray holeCards = actualTeam.getAsJsonArray("hole_cards");
                        if (holeCards.get(0).getAsJsonObject().get("rank").getAsString()
                                .equals(holeCards.get(1).getAsJsonObject().get("rank").getAsString())) {
                            return actualTeam.get("stack").getAsInt();
                        }
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static Integer preflop(JsonObject reqest) {

        return 0;
    }

    private static Integer highPairPreflop(JsonObject request) {
        JsonObject jsonObject = request.getAsJsonObject();


        for (JsonElement team : jsonObject.getAsJsonArray("players")) {
            JsonObject actualTeam = team.getAsJsonObject();
            if (actualTeam.get("name").getAsString().equals("BooLean")) {
                JsonArray holeCards = actualTeam.getAsJsonArray("hole_cards");

                String card1 = holeCards.get(0).getAsJsonObject().get("rank").getAsString();
                String card2 = holeCards.get(1).getAsJsonObject().get("rank").getAsString();


                switch (card1) {
                    case "A":
                        if (card1.equals(card2)) {
                            return actualTeam.get("stack").getAsInt();
                        }
                        return 0;

                    case "K":
                        if (card1.equals(card2)) {
                            return actualTeam.get("stack").getAsInt();
                        }
                        return 0;

                    case "Q":
                        if (card1.equals(card2)) {
                            return actualTeam.get("stack").getAsInt();
                        }
                        return 0;
                }
            }

        }
        return 0;
    }

    public static void showdown(JsonElement game) {
    }
}
