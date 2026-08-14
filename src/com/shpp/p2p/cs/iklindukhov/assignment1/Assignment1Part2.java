package com.shpp.p2p.cs.iklindukhov.assignment1;

public class Assignment1Part2 extends superclass {
    public void run() throws Exception {
        while (!(facingSouth() && frontIsBlocked())) { //true only in last cycle after turnAroundAndGo
            buildingColumn();
            turnAroundAndGoBack();
            movingToNextColumn();
        }
    }
//
//components of "run"
//
    private void buildingColumn() throws Exception {
        turnLeft();                       //turns North
        while (frontIsClear()) {          //putting beep if needed
            while (noBeepersPresent()) {
                putBeeper();
            }
            move();                       //than moves
        }
        if (noBeepersPresent()) {        //every second column last tile hasnt a beep
            putBeeper();                 //and this "if-crutch" fixes this
        }
    }
//
//
    private void turnAroundAndGoBack() throws Exception {
        turnAround();             // turn around
        while (frontIsClear()) {  // go back until hit wall
            move();
        }
    }
//
//
    private void movingToNextColumn() throws Exception {
        if (leftIsClear()) {              //checks if there is a need to move on
            turnLeft();                   //Karel turns East to move to next column
            for (int i = 0; i < 4; i++) { //how far Karel moves (4 tiles)
                move();
            }
        }

    }
}
