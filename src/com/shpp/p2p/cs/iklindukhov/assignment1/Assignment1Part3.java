package com.shpp.p2p.cs.iklindukhov.assignment1;

public class Assignment1Part3 extends superclass {

    
    public void run() throws Exception {
        MoveAndPutBeeps();                //put beeps along all the line
        turnAround();                     //well u know what it does
        if (frontIsClear()) {             //it is a check for a 1 width world
            moveUntilEdgesAreClear ();    //cleans the edges and serves as a check for 2 width world
            moveOnATileBehind();          //has beeper so cycle below starts (or doesnt start in 2 width)

            while (beepersPresent()) {
                moveUntilMeetsClearTile();
                moveOnATileBehind();     //go back on a tile with beeper
                pickBeeper();
                CheckIfProgramEnds();     //ends if after this method Karel appears on empty tile
            }
        }
    }

//
// components of "run"
//

private void moveUntilEdgesAreClear() throws Exception {
    while (frontIsClear()) {
        move ();
        if (frontIsBlocked() && beepersPresent()) { //a check for 2 width world
            pickBeeper();
            moveOnATileBehind();
        }
    }
}
//
//
    private void MoveAndPutBeeps() throws Exception { //put beeps along all the line
        while (frontIsClear()){
            putBeeper();
            move ();
        }
        putBeeper();
    }
//
//
    private void moveUntilMeetsClearTile() throws Exception{ //Karel moves until appears on
        while (beepersPresent()) {                           //a tile without beeps
            move();
        }
    }
//
//
    private void moveOnATileBehind() throws Exception {
            turnAround();
            move();
    }
//
//
    private void CheckIfProgramEnds() throws Exception {
       move();                    //first move (appears on a LAST TILE WITH BEEP)
        //this is a check for 3 width world
        if (frontIsBlocked()) {   //after first move we face a wall which is possible only in 3 width.
            moveOnATileBehind(); //if so, the last beeper is already gone, and we should turn around, move
            putBeeper();          //and put it back
            move();               //and move second time to end the program (cause beepers arent present)
        }
        else move();              //second move (appears on EMPTY TILE BEHIND LAST BEEP-TILE)
    }                             //which ends a program
}
//died while commenting this