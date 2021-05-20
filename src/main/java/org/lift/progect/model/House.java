package org.lift.progect.model;

import org.lift.progect.service.Move;
import org.lift.progect.service.RandomGenerator;

import java.util.Map;
import java.util.Queue;

public class House {
    private static House house;
    private final int amountFloorMAX = 5;//20
    private final int amountFloorMin = 5;
    private final int amountUsersFloor = 5;//10
    private int amountFlor;
    private Map<Integer, Map<Move, Queue<User>>> usersHouse;


    private House() {
    }

    public static synchronized House getInstance() {
        if (house == null) {
            house = new House();
        }
        return house;
    }
    public void generatedHouse (RandomGenerator generator) {
        usersHouse = generator.generateHouse(amountFloorMin, amountFloorMAX, amountUsersFloor);
        amountFlor = usersHouse.size();
    }

    public Map<Integer, Map<Move, Queue<User>>> getUsersHouse() {
        return usersHouse;
    }

    public int getAmountFlor() {
        return amountFlor;
    }
}
