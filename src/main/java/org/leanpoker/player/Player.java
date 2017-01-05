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
                       // two A -> allin
//                        if (holeCards.get(0).getAsJsonObject().get("rank").equals("A")
//                                && holeCards.get(1).getAsJsonObject().get("rank").equals("A")) {
//                            return actualTeam.get("stack").getAsInt();
//                        }
                        // have a pair -> allin
                        if (holeCards.get(0).getAsJsonObject().get("rank").equals(holeCards.get(1).getAsJsonObject().get("rank"))) {
                            return jsonObject.get("stack").getAsInt();
                        }
                        // one A -> raise with sb
                        if (holeCards.get(0).getAsJsonObject().get("rank").equals("A")
                                || holeCards.get(1).getAsJsonObject().get("rank").equals("A")) {
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
