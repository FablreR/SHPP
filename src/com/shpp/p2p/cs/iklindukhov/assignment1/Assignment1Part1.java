package com.shpp.p2p.cs.iklindukhov.assignment1;

/**
 * Karol moves to "newspaper", picks it and returns.
 */
public class Assignment1Part1 extends superclass {

    public void run() throws Exception {
        moveToNews();
        pickNews();
        returnToStartPosition();
}
    /**
     * Karol moves to "newspaper"
     */
    private void moveToNews () throws Exception {
        turnRight();       //you will be surprised but Karol turns right. Look for this method in superclass
        move();            //makes one little step
        turnLeft();        //dont be scared. I warned you. Karel turns left now
        move4Tiles();      //and makes 4 steps with "for" cycle
    }
    /**
     * Karol picks a "newspaper"
     */
    private void pickNews() throws Exception {
        pickBeeper();     //well, by this Karel picks a newspaper. Trust me
    }

    /**
     * Karol returns/ Actually does the same as "moveToNews" but in reverse order.
     */
    private void returnToStartPosition() throws Exception {
        turnAround();
        move4Tiles();
        turnRight();
        move();
        while (!facingEast()) { //turn left while not facing east, just where Karol was looking at start
            turnLeft();
        }
    }
    /**
     * Short cycle so you can regulate how far moves Karel (idk why u may need this).
     */
    private void move4Tiles() throws Exception {
        for (int i = 0;i < 4;i++) {
            move();
        }
    }
}
/*
  0**0 = 1
    2**0 = 1
    2**1 = 2
    2**3 = 8
    2**−2 = 1/4
    3**4 = 81
    (−2)**3 = −8
    (−2)**4 = 16
    (−3)**5 = −243
    10**6 = 1000000
    10**−3 = 0.001
    5**−1 = 0.2
    (−1)**17 = −1
    (−1)**100 = 1
    2.5**2 = 6.25
    2.5**−1 = 0.4
    0.5**3 = 0.125
    (−3.5)**2 = 12.25
 */

