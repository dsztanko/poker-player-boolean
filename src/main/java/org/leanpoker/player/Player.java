package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {

        JsonObject jsonObject = request.getAsJsonObject();
        if (jsonObject.getAsJsonArray("community_cards").size() == 0) {
            for (JsonElement team: jsonObject.getAsJsonArray("players")){
                JsonObject actualTeam = team.getAsJsonObject();
                if (actualTeam.get("name").getAsString().equals("BooLean")) {
                    JsonArray holeCards = actualTeam.getAsJsonArray("hole_cards");
                    if (holeCards.get(0).getAsJsonObject().get("rank").getAsString().equals("A")
                            && holeCards.get(1).getAsJsonObject().get("rank").getAsString().equals("A")) {
                        return actualTeam.get("stack").getAsInt();
                    }
                }
            }
        }
        return 0;
    }

    public static void showdown(JsonElement game) {
    }
}
