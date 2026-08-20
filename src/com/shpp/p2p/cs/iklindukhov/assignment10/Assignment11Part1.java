package com.shpp.p2p.cs.iklindukhov.assignment10;

/**
 * A calculator that receives a formula and its parameters from the console
 */
public class Assignment11Part1 {
    /**
     * Entry point
     *
     * @param args arguments from the console.
     *             "0" is responsible for formula
     *             "1+" for parameters
     */
    public static void main(String[] args) {
        System.out.println("Keep in mind that the functions are calculated in radians");
        System.out.println("Here is formula: " + args [0]);
        try {
            ParametersDataBase parameters = new ParametersDataBase(args);
            FormulaParser parsedFormula = new FormulaParser(args[0]);
            FormulaTree tree = new FormulaTree(parsedFormula.getParsedFormula(), parameters);
            System.out.println("Result is: " + tree.getRoot().calculate());
        }
        catch (IllegalArgumentException e) {
            System.out.println("Well, something went wrong: " + e);
        }

    }
}
