package org.lift.progect.model;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LiftTest extends TestCase {
    Lift lift;
    {
        List<User> usersIntoLift = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            usersIntoLift.add(new User(i, 0, 6));
        }
        usersIntoLift.get(1).setNewPosition(3);
        lift = Lift.getInstance();
        lift.setUsersIntoLift(usersIntoLift);
    }

    @Test
    public void testSetNextPosition() {
        assertEquals(0, lift.getPosition());
        lift.setNextPosition();
        assertEquals(3, lift.getNextPosition());
        lift.getUsersIntoLift().get(3).setNewPosition(2);
        lift.setNextPosition();
        assertEquals(2, lift.getNextPosition());
        lift.getUsersIntoLift().get(4).setNewPosition(1);
        lift.setNextPosition();
        assertEquals(1, lift.getNextPosition());
        lift.setUsersIntoLift(null);
        lift.setNextPosition();
        assertEquals(0, lift.getNextPosition());
        lift.setUsersIntoLift(new ArrayList<>());
        lift.setNextPosition();
        assertEquals(0, lift.getNextPosition());
    }
}