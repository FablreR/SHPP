package com.shpp.p2p.cs.iklindukhov.assignment1;

/**
 * Making a chessboard. Moving to the end of the line, check if there is beeper on the last tile,
 * turn correct side and depending on result continue movement while putting beepers. So we have
 * a "snake" which makes the board.
 */
public class
Assignment1Part4 extends superclass {

    public void run() throws Exception {
        putBeeper();
        while (frontIsClear()) {
            turnCorrectDirection();
            putBeepsUntilTheEdge();
            getOnTheNextRow();
        }
        createTheLastRow(); // on the last row cycle ends so it is done separately
    }


    private void turnCorrectDirection() throws Exception {
        if (leftIsBlocked()) {    // true on the North edge of the level
            turnRight();          // now facing South
        }
        if (rightIsBlocked()) {   // true on the South
            turnLeft();           // now facing North
        }
    }


    private void putBeepsUntilTheEdge() throws Exception {
        while (frontIsClear()) {
            if (noBeepersPresent()) {
                move();
                putBeeper();
            } else move();
        }
    }

    /**
     * this method moves Karel to the next row, BUT also decides
     * if next row starts with beeper or with empty tile
     */
    private void getOnTheNextRow() throws Exception {
        while (!facingEast()) { // rows are built from west to east so before
            turnLeft();           // moving to a new one must always look east
        }
        if (frontIsClear()) {             //check if this is the end of the world
            if (noBeepersPresent()) {      //if there are no beeps under Karel
                move();                    //he moves to next row, he puts beeper, so
                putBeeper();               //next PutBeepsUntilTheEdge starts with "else"
            } else move();                   //move, so PutBeepsUntilTheEdge starts with "if"
        }
    }


    private void createTheLastRow() throws Exception {
        turnCorrectDirection(); //South or North
        putBeepsUntilTheEdge(); //build a row

    }
}

