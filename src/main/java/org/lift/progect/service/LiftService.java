package org.lift.progect.service;

import org.lift.progect.model.User;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface LiftService {
    void firstStart();
    void usersComeInLift(Map<Move, Queue<User>> allUsersFloor);
    void usersGoOutLift();
    void changeMove();
    void run ();
    void print ();
}