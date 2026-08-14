package com.shpp.p2p.cs.iklindukhov.assignment1;

import com.shpp.karel.KarelTheRobot;
//
// superclass for "basic" methods
//
public class superclass extends KarelTheRobot {
    public void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }
    public void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}
