package org.lift.progect.service;

import org.apache.log4j.Logger;
import org.lift.progect.model.House;
import org.lift.progect.model.Lift;
import org.lift.progect.model.User;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class LiftServiceImpl implements LiftService{
    private Lift lift;
    private final House house;
    private final RandomGenerator generator;
    private final static Logger logger = Logger.getLogger(LiftServiceImpl.class);

    public LiftServiceImpl(RandomGenerator generator) throws IOException {
        lift = Lift.getInstance();
        house = House.getInstance();
        this.generator = generator;
    }
    @Override
    public void firstStart() {
        logger.info("FLOOR - " + lift.getPosition());
        lift.getUsersIntoLift().removeIf(Objects::isNull);
        print();
        usersComeInLift(house.getUsersHouse().get(lift.getPosition()));
        run();
        usersGoOutLift();
        if(lift.getUsersIntoLift().size() > 0) {
            firstStart();
        }
    }

    private boolean isFree() {
        return (lift.getMaxUser() > lift.getUsersIntoLift().size());
    }

    @Override
    public void usersComeInLift(Map<Move, Queue<User>> allUsersFloor) {
        while(isFree()) {
            lift.getUsersIntoLift().add(allUsersFloor.get(lift.getMove()).poll());
        }
        logger.info("GO INTO LIFT");
    }

    @Override
    public void usersGoOutLift() {
       ArrayList<User> usersLift = (ArrayList<User>) lift.getUsersIntoLift();
       logger.info(usersLift);
        List <User> goOut = usersLift.stream().filter(Objects::nonNull)
                .filter(x -> x.getNewPosition() == lift.getPosition())
                .map(this::updateUser)
                .collect(Collectors.toList());
        logger.info("RESULT OF STREAM " + goOut);
        for (User user: goOut) {
            lift.getUsersIntoLift().remove(user);
        }
        updateFloor(goOut);
        logger.info("USERS EXIT LIFT  - ");
    }

    private void updateFloor (List <User> usersGoOutLift) {
        if (usersGoOutLift.size() > 0) {
            Move moveUsers = usersGoOutLift.get(0).getMove();
            int floor = usersGoOutLift.get(0).getPosition();
            for(User user : usersGoOutLift) {
                house.getUsersHouse().get(floor).get(moveUsers).offer(user);
            }
        }
        logger.info("UPDATE FLOOR");
    }

    @Override
    public void run () {
        lift.setNextPosition();
        lift.setPosition(lift.getNextPosition());
        logger.info("RUN LIFT");
    }

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

    private User updateUser(User userStream) {
        userStream.setPosition(lift.getPosition());
        userStream.setNewPosition(generator.generatePositionForUser(userStream.getPosition(), house.getAmountFlor()));
        return userStream;
    }

    @Override
    public void changeMove() {
        if (lift.getMove() == Move.UP) {
            lift.setMove(Move.DOWN);
        } else {
            lift.setMove(Move.UP);
        }
    }

    @Override
    public void print () {
        List<User> usersLift = lift.getUsersIntoLift();
        Map<Move, Queue<User>> usersFloor = house.getUsersHouse().get(lift.getPosition());
        LinkedList<User> usersUp = (LinkedList<User>) usersFloor.get(Move.UP);
        LinkedList<User> usersDown = (LinkedList<User>) usersFloor.get(Move.DOWN);

        boolean iter = true;
        for(int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println("Этаж " + lift.getPosition() + " Лифт едет " + lift.getMove());
        System.out.format("%16s %55s \r\n %50s %35s \r\n","В лифте","На площадке", "UP", "DOWN");
        int i = 0;
        while (iter) {
            if (usersLift.size() > i && usersLift.get(i) != null) {
                System.out.format("%-35s",usersLift.get(i));
            } else {
                System.out.format("%-35s", " ");
            }
            if (usersUp.size() > i ) {
                System.out.format("%-35s", usersUp.get(i));
            } else {
                System.out.format("%-35s", " ");
            }
            if (usersDown.size() > i ) {
                System.out.format("%-35s \r\n", usersDown.get(i));
               // System.out.println();
            } else {
                System.out.format("%-35s \r\n", " ");
            }
            i++;
            if (usersLift.size() < i & usersUp.size() < i) {
                iter = false;
            }
        }
    }
}