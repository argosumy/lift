package org.lift.progect.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.lift.progect.model.User;

import java.util.*;

public class LiftServiceTest extends TestCase {
    LiftService liftService = new LiftService(new RandomGeneratorImpl());
    {

        List<User> usersIntoLift = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            usersIntoLift.add(new User(i, 0, 4));
        }
        liftService.getLift().setUsersIntoLift(usersIntoLift);
    }

    @Test
    public void testIsFree() {
        assertFalse(liftService.isFree());
        liftService.getLift().getUsersIntoLift().remove(1);
        assertTrue(liftService.isFree());
    }

    @Test
    public void testUsersComeInLift() {
        liftService.getLift().getUsersIntoLift().remove(1);
        Map<Move, Queue<User>> allUsersFloor = new HashMap<>();
        Queue<User> users = new LinkedList<>();
        users.offer(new User(6, 0, 4));
        allUsersFloor.put(Move.UP, users);
        liftService.usersComeInLift(allUsersFloor);
        assertEquals(5, liftService.getLift().getUsersIntoLift().size());
        assertEquals(0, allUsersFloor.get(Move.UP).size());
    }

    public void testUsersGoOutLift() {
        List<User> users = liftService.getLift().getUsersIntoLift();
        users.get(1).setNewPosition(0);
        System.out.println(users);
        int size = users.size();
        assertEquals(1, liftService.usersGoOutLift().size());//Колличество вышедших из лифта
        assertEquals(size - 1, liftService.getLift().getUsersIntoLift().size());//Колличество оставшихся в лифте
    }

    public void testTestRun() {
    }
}