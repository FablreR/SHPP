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
        System.out.println("Here is formula: " + args [0]);
        ParametersDataBase parameters = new ParametersDataBase(args);
        FormulaParsing parsedFormula = new FormulaParsing(args[0]);
        FormulaTree tree = new FormulaTree(parsedFormula.getParsedFormula(), parameters);
        System.out.println("Result is: " + tree.parseTheTree());
    }
}
