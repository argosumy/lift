package org.lift.progect.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.lift.progect.model.House;
import org.lift.progect.model.Lift;
import org.lift.progect.model.User;

import java.util.*;

public class LiftServiceTest extends TestCase {
    House house;
    Lift lift;
    LiftService liftService;
    {
        house = House.getInstance();
        lift = Lift.getInstance();
        RandomGenerator generator = new RandomGeneratorImpl();
        house.generatedHouse(generator);
        liftService = new LiftService(generator);
    }


    @Test
    public void testIsFree() {
        assertTrue(liftService.isFree());
    }

    @Test
    public void testUsersComeInLift() {
    }

    @Test
    public void testUsersGoOutLift() {
        int size = lift.getUsersIntoLift().size();
        int users = house.getUsersHouse().get(lift.getPosition()).get(lift.getMove()).size();
        liftService.usersComeInLift(house.getUsersHouse().get(lift.getPosition()));

    }
    @Test
    public void testTestRun() {
        int next = liftService.getLift().getNextPosition();
        liftService.run();
        int position = liftService.getLift().getPosition();
        assertEquals(next, position);
    }
}