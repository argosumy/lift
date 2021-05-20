package org.lift.progect.service;

import org.lift.progect.model.User;

import java.util.Map;
import java.util.Queue;

public interface RandomGenerator {
    Map<Integer, Map<Move, Queue<User>>> generateHouse(int amountFlorMin, int amountFloorMax, int amountUsers);
    int generatePositionForUser (int position, int amountFloor);
}
