package org.lift.progect.service;

import org.lift.progect.model.House;
import org.lift.progect.model.Lift;
import org.lift.progect.model.User;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class LiftService {
    private Lift lift;
    private RandomGenerator generator;

    public LiftService(RandomGenerator generator) {
        lift = Lift.getInstance();
        this.generator = generator;
    }

    public boolean isFree() {
        return (Lift.getMaxUser() > lift.getUsersIntoLift().size());
    }

    public void usersComeInLift(Map<Move, Queue<User>> allUsersFloor) {
        while(isFree()) {
            lift.getUsersIntoLift().add(allUsersFloor.get(lift.getMove()).poll());
        }
    }

    public List<User> usersGoOutLift() {
        List <User> goOut = lift.getUsersIntoLift().stream()
                .filter(x -> x.getNewPosition() == lift.getPosition())
                .map(this::updateUser)
                .collect(Collectors.toList());
        for (User user: goOut) {
            lift.getUsersIntoLift().remove(user);
        }
        return goOut;
    }

    public void run () {}

    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

    private User updateUser(User userStream) {
        User user = userStream;
        user.setPosition(lift.getPosition());
        user.setNewPosition(generator.generatePositionForUser(user.getPosition(), House.getInstance().getAmountFlor()));
        return user;
    }
}
