package org.lift.progect.controller;

import org.lift.progect.service.*;

public class LiftController {

    public void liftController (LiftService liftService) {
        liftService.firstStart();
        liftService.print();
        liftService.changeMove();
        liftService.firstStart();
        liftService.print();
    }
}