package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import jdk.nashorn.api.scripting.JSObject;

import java.util.Map;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        try {
            JsonObject jsonObject = request.getAsJsonObject();
            if (jsonObject.getAsJsonArray("community_cards") == null) {
                for (JsonElement team: jsonObject.getAsJsonArray("players")){
                    JsonObject actualTeam = team.getAsJsonObject();
                    if (actualTeam.get("name").equals("BooLean")) {
                        JsonArray holeCards = actualTeam.getAsJsonArray("hole_cards");
                        if (holeCards.get(0).getAsJsonObject().get("rank").equals("A")) {
                            return jsonObject.get("current_buy_in").getAsInt() + jsonObject.get("small_blind").getAsInt();
                        }
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public static void showdown(JsonElement game) {
    }
}
