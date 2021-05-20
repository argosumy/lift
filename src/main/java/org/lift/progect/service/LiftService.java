package org.lift.progect.service;

import org.apache.log4j.Logger;
import org.lift.progect.model.House;
import org.lift.progect.model.Lift;
import org.lift.progect.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class LiftService {
    private Lift lift;
    private House house;
    private RandomGenerator generator;
    int step = 1;
    private final static Logger logger = Logger.getLogger(LiftService.class);

    public LiftService(RandomGenerator generator) {
        lift = Lift.getInstance();
        house = House.getInstance();
        this.generator = generator;
    }

    public void firstStart() {
        logger.info("FLOOR - " + lift.getPosition());
        lift.getUsersIntoLift().removeIf(x -> x == null);
        print();
//        if (house.getUsersHouse().get(Move.UP).size() != 0) {
            usersComeInLift(house.getUsersHouse().get(lift.getPosition()));
            run();
            usersGoOutLift();
            if(lift.getUsersIntoLift().size() > 0) {
                logger.info("HREN " + lift);
                firstStart();

            }
//        }
        System.out.println("ВЫХОД");
    }

    public boolean isFree() {
        return (Lift.getMaxUser() > lift.getUsersIntoLift().size());
    }

    public void usersComeInLift(Map<Move, Queue<User>> allUsersFloor) {
        while(isFree()) {
            lift.getUsersIntoLift().add(allUsersFloor.get(lift.getMove()).poll());
        }
        logger.info("Вход в лифт ");
    }

    public List<User> usersGoOutLift() {
        ArrayList<User> usersLift = (ArrayList<User>) lift.getUsersIntoLift();
//        usersLift.trimToSize();
        logger.info(usersLift);
        List <User> goOut = usersLift.stream().filter(x -> x != null)
                .filter(x -> x.getNewPosition() == lift.getPosition())
                .map(this::updateUser)
                .collect(Collectors.toList());
        logger.info("Result stream " + goOut);
        for (User user: goOut) {
            lift.getUsersIntoLift().remove(user);
        }
        updateFloor(goOut);
        logger.info("Выход из лифта  - ");
        return goOut;
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

    private void print () {
        List<User> usersLift = lift.getUsersIntoLift();
        Map<Move, Queue<User>> usersFloor = house.getUsersHouse().get(lift.getPosition());
        LinkedList<User> usersUp = (LinkedList<User>) usersFloor.get(Move.UP);
        LinkedList<User> usersDown = (LinkedList<User>) usersFloor.get(Move.DOWN);

        boolean iter = true;
        System.out.println("Этаж " + lift.getPosition() + " Лифт едет " + lift.getMove());

        System.out.println("          В лифте                                 " + " На площадке");
        System.out.println("                       " + "                  UP     " + "                   DOWN          ");
        int i = 0;
        int j = 0;
        while (iter) {
            if (usersLift.size() > i && usersLift.get(i) != null) {
                System.out.print(usersLift.get(i) + "     ");
            } else {
                System.out.print("                             ");
            }
            if (usersUp.size() > i ) {
                System.out.print(usersUp.get(i) + "     ");
            } else {
                System.out.print("                              ");
            }
            if (usersDown.size() > i ) {
                System.out.println(usersDown.get(i) + "     ");
            } else {
                System.out.println("                              ");
            }
            i++;
            if (usersLift.size() < i & usersUp.size() < i) {
                iter = false;
            }
        }
        System.out.println();

    }
}
