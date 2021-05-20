package org.lift.progect.model;

import org.apache.log4j.Logger;
import org.lift.progect.configuration.AppConfig;
import org.lift.progect.service.Move;

import java.util.*;

public class Lift {
    private static Lift lift;
    private static final int MAX_USER = AppConfig.LIFT_SIZE;
    private Move move;
    private int position;
    private int nextPosition;
    private List<User> usersIntoLift;
    private final static Logger logger = Logger.getLogger(Lift.class);


    private Lift() {
        position = 1;
        move = Move.UP;
        usersIntoLift = new ArrayList<>();
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
        if(usersIntoLift != null) {
            usersIntoLift.removeIf(Objects::isNull);
            if(usersIntoLift.size() > 1) {
                usersIntoLift.removeIf(Objects::isNull);
                if (move == Move.UP) {
                    logger.info("USERS INTO LIFT" + usersIntoLift + " size " + usersIntoLift.size());
                    nextPosition = usersIntoLift.stream().filter(Objects::nonNull).min(User::compareTo).get().getNewPosition();
                } else {
                    nextPosition = usersIntoLift.stream().max(User::compareTo).get().getNewPosition();
                }
            } else if (usersIntoLift.size() != 0) {
                nextPosition = usersIntoLift.get(0).getNewPosition();
            }
        } else {
            nextPosition = 1;
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
