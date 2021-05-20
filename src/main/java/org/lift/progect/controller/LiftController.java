package org.lift.progect.controller;

import org.lift.progect.service.LiftService;
import org.lift.progect.service.Move;
import org.lift.progect.service.RandomGeneratorImpl;

public class LiftController {

    public void liftController () {
        LiftService liftService = new LiftService(new RandomGeneratorImpl());
        liftService.firstStart();
        liftService.print();
        liftService.getLift().setMove(Move.DOWN);
        liftService.firstStart();
        liftService.print();
    }
}
