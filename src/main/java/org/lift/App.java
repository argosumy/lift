package org.lift;

import org.lift.progect.controller.LiftController;
import org.lift.progect.model.House;
import org.lift.progect.service.*;

public class App
{
    public static void main( String[] args ) {
        House house = House.getInstance();
        house.generatedHouse(new RandomGeneratorImpl());
        new LiftController().liftController();
        System.out.println("         THE END");
        }
}
