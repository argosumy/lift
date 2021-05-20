package org.lift.progect.model;

import org.lift.progect.service.Move;

public class User implements Comparable {
    private final int id;
    private int position;
    private int newPosition;
    private Move move;

    public User(int id, int position, int newPosition) {
        this.id = id;
        this.position = position;
        setNewPosition(newPosition);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
        if (position < newPosition) {
            move = Move.UP;
        } else {
            move = Move.DOWN;
        }
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        User user = (User)o;
        return this.getNewPosition() - user.getNewPosition();
    }

    @Override
    public String toString() {
        return "User " + id + " position " + position + " move " + move + " newP " + newPosition;
    }
}
