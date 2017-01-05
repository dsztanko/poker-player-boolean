package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testBetRequest() throws Exception {

        JsonElement jsonElement = new JsonParser().parse("{\n" +
                "  \"players\":[\n" +
                "    {\n" +
                "      \"name\":\"BooLean\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[{\"rank\":\"A\"},{\"rank\":\"A\"}],\n" +
                "      \"version\":\"Version name 1\",\n" +
                "      \"id\":0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\":\"Player 2\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[],\n" +
                "      \"version\":\"Version name 2\",\n" +
                "      \"id\":1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tournament_id\":\"550d1d68cd7bd10003000003\",\n" +
                "  \"game_id\":\"550da1cb2d909006e90004b1\",\n" +
                "  \"round\":0,\n" +
                "  \"bet_index\":0,\n" +
                "  \"small_blind\":10,\n" +
                "  \"orbits\":0,\n" +
                "  \"dealer\":0,\n" +
                "  \"community_cards\":[],\n" +
                "  \"current_buy_in\":0,\n" +
                "  \"pot\":0\n" +
                "}");

        assertEquals(120, Player.betRequest(jsonElement));

    }

    @Test
    public void testMiddleHandAfterFlop() throws Exception {
        JsonElement jsonElement = new JsonParser().parse("{\n" +
                "  \"players\":[\n" +
                "    {\n" +
                "      \"name\":\"BooLean\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[{\"rank\":\"K\"},{\"rank\":\"K\"}],\n" +
                "      \"version\":\"Version name 1\",\n" +
                "      \"id\":0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\":\"Player 2\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[],\n" +
                "      \"version\":\"Version name 2\",\n" +
                "      \"id\":1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tournament_id\":\"550d1d68cd7bd10003000003\",\n" +
                "  \"game_id\":\"550da1cb2d909006e90004b1\",\n" +
                "  \"round\":0,\n" +
                "  \"bet_index\":0,\n" +
                "  \"small_blind\":10,\n" +
                "  \"orbits\":0,\n" +
                "  \"dealer\":0,\n" +
                "  \"community_cards\":[{\"rank\":\"A\"},{\"rank\":\"J\"},{\"rank\":\"10\"}],\n" +
                "  \"current_buy_in\":0,\n" +
                "  \"pot\":0\n" +
                "}");

        assertEquals(1000, Player.betRequest(jsonElement));
    }

    @Test
    public void testMiddlePairPreflop() throws Exception {

        JsonElement jsonElement = new JsonParser().parse("{\n" +
                "  \"players\":[\n" +
                "    {\n" +
                "      \"name\":\"BooLean\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[{\"rank\":\"10\", \"suit\":\"spades\"},{\"rank\":\"10\", \"suit\":\"diamonds\"}],\n" +
                "      \"version\":\"Version name 1\",\n" +
                "      \"id\":0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\":\"Player 2\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[],\n" +
                "      \"version\":\"Version name 2\",\n" +
                "      \"id\":1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tournament_id\":\"550d1d68cd7bd10003000003\",\n" +
                "  \"game_id\":\"550da1cb2d909006e90004b1\",\n" +
                "  \"round\":0,\n" +
                "  \"bet_index\":0,\n" +
                "  \"small_blind\":10,\n" +
                "  \"orbits\":0,\n" +
                "  \"dealer\":0,\n" +
                "  \"community_cards\":[],\n" +
                "  \"current_buy_in\":10,\n" +
                "  \"pot\":0\n" +
                "}");

        assertEquals(1000/6, Player.betRequest(jsonElement));
    }
    @Test
    public void testHighCardsPreflop() throws Exception {

        JsonElement jsonElement = new JsonParser().parse("{\n" +
                "  \"players\":[\n" +
                "    {\n" +
                "      \"name\":\"BooLean\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[{\"rank\":\"J\", \"suit\":\"spades\"},{\"rank\":\"K\", \"suit\":\"diamonds\"}],\n" +
                "      \"version\":\"Version name 1\",\n" +
                "      \"id\":0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\":\"Player 2\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[],\n" +
                "      \"version\":\"Version name 2\",\n" +
                "      \"id\":1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tournament_id\":\"550d1d68cd7bd10003000003\",\n" +
                "  \"game_id\":\"550da1cb2d909006e90004b1\",\n" +
                "  \"round\":0,\n" +
                "  \"bet_index\":0,\n" +
                "  \"small_blind\":10,\n" +
                "  \"orbits\":0,\n" +
                "  \"dealer\":0,\n" +
                "  \"community_cards\":[],\n" +
                "  \"current_buy_in\":10,\n" +
                "  \"pot\":0\n" +
                "}");

        assertEquals(90, Player.betRequest(jsonElement));

    }

    @Test
    public void testColorAndHighBetRequest() throws Exception {

        JsonElement jsonElement = new JsonParser().parse("{\n" +
                "  \"players\":[\n" +
                "    {\n" +
                "      \"name\":\"BooLean\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[{\"rank\":\"A\", \"suit\":\"spades\"},{\"rank\":\"7\", \"suit\":\"spades\"}],\n" +
                "      \"version\":\"Version name 1\",\n" +
                "      \"id\":0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\":\"Player 2\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[],\n" +
                "      \"version\":\"Version name 2\",\n" +
                "      \"id\":1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tournament_id\":\"550d1d68cd7bd10003000003\",\n" +
                "  \"game_id\":\"550da1cb2d909006e90004b1\",\n" +
                "  \"round\":0,\n" +
                "  \"bet_index\":0,\n" +
                "  \"small_blind\":10,\n" +
                "  \"orbits\":0,\n" +
                "  \"dealer\":0,\n" +
                "  \"community_cards\":[],\n" +
                "  \"current_buy_in\":0,\n" +
                "  \"pot\":0\n" +
                "}");

        assertEquals(50, Player.betRequest(jsonElement));

    }

    @Test
    public void testMediumCardsBetRequest() throws Exception {
        JsonElement jsonElement = new JsonParser().parse("{\n" +
                "  \"players\":[\n" +
                "    {\n" +
                "      \"name\":\"BooLean\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[{\"rank\":\"7\", \"suit\":\"diamonds\"},{\"rank\":\"7\", \"suit\":\"spades\"}],\n" +
                "      \"version\":\"Version name 1\",\n" +
                "      \"id\":0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\":\"Player 2\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[],\n" +
                "      \"version\":\"Version name 2\",\n" +
                "      \"id\":1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tournament_id\":\"550d1d68cd7bd10003000003\",\n" +
                "  \"game_id\":\"550da1cb2d909006e90004b1\",\n" +
                "  \"round\":0,\n" +
                "  \"bet_index\":0,\n" +
                "  \"small_blind\":10,\n" +
                "  \"orbits\":0,\n" +
                "  \"dealer\":0,\n" +
                "  \"community_cards\":[],\n" +
                "  \"current_buy_in\":10,\n" +
                "  \"pot\":0\n" +
                "}");
        assertEquals(1000/12, Player.betRequest(jsonElement));
    }

        @Test
            public void testOneHighOneMiddleInHand() throws Exception {
                JsonElement jsonElement = new JsonParser().parse("{\n" +
                        "  \"players\":[\n" +
                        "    {\n" +
                        "      \"name\":\"BooLean\",\n" +
                        "      \"stack\":1000,\n" +
                        "      \"status\":\"active\",\n" +
                        "      \"bet\":0,\n" +

                "      \"hole_cards\":[{\"rank\":\"K\", \"suit\":\"clubs\"},{\"rank\":\"9\", \"suit\":\"spades\"}],\n" +
                "      \"version\":\"Version name 1\",\n" +
                        "      \"id\":0\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\":\"Player 2\",\n" +
                        "      \"stack\":1000,\n" +
                        "      \"status\":\"active\",\n" +
                        "      \"bet\":0,\n" +
                        "      \"hole_cards\":[],\n" +
                        "      \"version\":\"Version name 2\",\n" +
                        "      \"id\":1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"tournament_id\":\"550d1d68cd7bd10003000003\",\n" +
                        "  \"game_id\":\"550da1cb2d909006e90004b1\",\n" +
                        "  \"round\":0,\n" +
                        "  \"bet_index\":0,\n" +
                        "  \"small_blind\":10,\n" +
                        "  \"orbits\":0,\n" +
                        "  \"dealer\":0,\n" +
                        "  \"community_cards\":[],\n" +
                "  \"current_buy_in\":0,\n" +
                        "  \"pot\":0\n" +
                        "}");

                assertEquals(100, Player.betRequest(jsonElement));
            }

    @Test
    public void testDrillAfterFlopBetRequest() throws Exception {
        JsonElement jsonElement = new JsonParser().parse("{\n" +
                "  \"players\":[\n" +
                "    {\n" +
                "      \"name\":\"BooLean\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[{\"rank\":\"7\", \"suit\":\"diamonds\"},{\"rank\":\"7\", \"suit\":\"spades\"}],\n" +
                "      \"version\":\"Version name 1\",\n" +
                "      \"id\":0\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\":\"Player 2\",\n" +
                "      \"stack\":1000,\n" +
                "      \"status\":\"active\",\n" +
                "      \"bet\":0,\n" +
                "      \"hole_cards\":[],\n" +
                "      \"version\":\"Version name 2\",\n" +
                "      \"id\":1\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tournament_id\":\"550d1d68cd7bd10003000003\",\n" +
                "  \"game_id\":\"550da1cb2d909006e90004b1\",\n" +
                "  \"round\":0,\n" +
                "  \"bet_index\":0,\n" +
                "  \"small_blind\":10,\n" +
                "  \"orbits\":0,\n" +
                "  \"dealer\":0,\n" +
                "  \"community_cards\":[{\"rank\":\"7\", \"suit\":\"diamonds\"}," +
                "{\"rank\":\"8\", \"suit\":\"diamonds\"}," +
                "{\"rank\":\"9\", \"suit\":\"diamonds\"}],\n" +
                "  \"current_buy_in\":10,\n" +
                "  \"pot\":0\n" +
                "}");
        assertEquals(1000, Player.betRequest(jsonElement));
    }
}
