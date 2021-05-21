package org.lift;

import org.apache.log4j.Logger;
import org.lift.progect.controller.LiftController;
import org.lift.progect.model.House;
import org.lift.progect.service.*;

import java.io.IOException;

public class App {
    private final static Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args ) {
        try {
            RandomGenerator generator = new RandomGeneratorImpl();
            LiftService liftService = new LiftServiceImpl(generator);
            House house = House.getInstance();
            house.generatedHouse(generator);
            new LiftController().liftController(liftService);
        } catch (IOException e) {
            logger.error(e);
            System.out.println("Config fail. Config file is not exist.");
        }
        System.out.println("         THE END");
    }
}