package org.lift.progect.model;

import org.lift.progect.configuration.AppConfig;
import org.lift.progect.service.Move;
import org.lift.progect.service.RandomGenerator;

import java.util.Map;
import java.util.Queue;

public class House {
    private static House house;
    private static final int AMOUNT_FLOOR_MAX;
    private static final int AMOUNT_FLOOR_MIN;
    private static final int AMOUNT_USERS_FLOOR;
    private int amountFlor;
    private Map<Integer, Map<Move, Queue<User>>> usersHouse;

    static {
        AMOUNT_FLOOR_MAX = AppConfig.AMOUNT_FLOOR_MAX;
        AMOUNT_FLOOR_MIN = AppConfig.AMOUNT_FLOOR_MIN;
        AMOUNT_USERS_FLOOR = AppConfig.AMOUNT_USERS_FLOOR;
    }

    private House() {
    }

    public static synchronized House getInstance() {
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
