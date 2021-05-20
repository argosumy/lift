package org.lift;

import org.lift.progect.model.House;
import org.lift.progect.model.User;
import org.lift.progect.service.MoveLift;
import org.lift.progect.service.MoveLiftImpl;
import org.lift.progect.service.RandomGenerator;
import org.lift.progect.service.RandomGeneratorImpl;

import java.util.Map;
import java.util.Queue;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        House house = House.getInstance();
        house.generatedHouse(new RandomGeneratorImpl());
        System.out.println(house);
        MoveLift moveLift = new MoveLiftImpl();
        moveLift.stepLift();
//        for (int i = 1; i < 20;i++ ) {
//        System.out.println((int)(Math.random() * 6)+1);
//        }
    }
}
