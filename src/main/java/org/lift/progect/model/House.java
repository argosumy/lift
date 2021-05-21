package org.lift.progect.model;

import org.lift.progect.configuration.AppConfig;
import org.lift.progect.service.Move;
import org.lift.progect.service.RandomGenerator;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;

public class House {
    private static House house;
    private final int AMOUNT_FLOOR_MAX;
    private final int AMOUNT_FLOOR_MIN;
    private final int AMOUNT_USERS_FLOOR;
    private int amountFlor;
    private Map<Integer, Map<Move, Queue<User>>> usersHouse;

    private House() throws IOException {
        AppConfig config = new AppConfig();
        AMOUNT_FLOOR_MAX = config.AMOUNT_FLOOR_MAX;
        AMOUNT_FLOOR_MIN = config.AMOUNT_FLOOR_MIN;
        AMOUNT_USERS_FLOOR = config.AMOUNT_USERS_FLOOR;
    }

    public static synchronized House getInstance() throws IOException {
        if (house == null) {
            house = new House();
        }
        return house;
    }
    public void generatedHouse (RandomGenerator generator) {
        usersHouse = generator.generateHouse(AMOUNT_FLOOR_MIN, AMOUNT_FLOOR_MAX, AMOUNT_USERS_FLOOR);
        amountFlor = usersHouse.size();
    }

    public Map<Integer, Map<Move, Queue<User>>> getUsersHouse() {
        return usersHouse;
    }

    public int getAmountFlor() {
        return amountFlor;
    }
}