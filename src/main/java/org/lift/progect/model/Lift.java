package org.lift.progect.model;

import org.lift.progect.service.Move;

import java.util.*;

public class Lift {
    private static Lift lift;
    private static final int MAX_USER = 5;
    private Move move;
    private int position;
    private int nextPosition;
    private List<User> usersIntoLift = new ArrayList<>(MAX_USER);

    private Lift() {
        position = 0;
        move = Move.UP;
    }

    public static synchronized Lift getInstance() {
        if (lift == null) {
            lift = new Lift();
        }
        return lift;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition() {
        if(usersIntoLift.size() != 0) {
            if (move == Move.UP) {
                nextPosition = usersIntoLift.stream().min(User::compareTo).get().getNewPosition();
            } else {
                nextPosition = usersIntoLift.stream().max(User::compareTo).get().getNewPosition();
            }
        } else {
            nextPosition = 0;
        }
    }

    public static int getMaxUser() {
        return MAX_USER;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<User> getUsersIntoLift() {
        return usersIntoLift;
    }

    public void setUsersIntoLift(List<User> usersIntoLift) {
        this.usersIntoLift = usersIntoLift;
    }
}
