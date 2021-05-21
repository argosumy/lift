package org.lift;

import org.lift.progect.controller.LiftController;
import org.lift.progect.model.House;
import org.lift.progect.service.*;

public class App {
    public static void main( String[] args ) {
        RandomGenerator generator = new RandomGeneratorImpl();
        LiftService liftService = new LiftServiceImpl(generator);
        House house = House.getInstance();
        house.generatedHouse(generator);
        new LiftController().liftController(liftService, generator);
        System.out.println("         THE END");
        }
}
