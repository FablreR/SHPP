package com.shpp.p2p.cs.iklindukhov.assignment10;

import acm.util.ErrorException;
import com.sun.nio.sctp.IllegalReceiveException;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Tokenises formula and parses it to reverse polish notation
 */
public class FormulaParsing {
    /**
     * Formula, parsed to reverse polish notation
     */
    ArrayList<String> parsedFormula = new ArrayList<>();


    /**
     * Tokenises formula and parses it to reverse polish notation
     *
     * @param formula received formula
     */
    public FormulaParsing(String formula) {
        formula = formula.replaceAll("\\s+", "");
        if (formula.isEmpty()) {
            throw new ErrorException("Formula is missing");
        } else {
            parseToPostfix(tokenise(formula));
        }
    }

    /**
     *
     * @return Formula, parsed to reverse polish notation
     */
    public ArrayList<String> getParsedFormula() {
        return parsedFormula;
    }

    /**
     * Tokenises formula
     *
     * @param formula untokenised formula
     * @return tokenised formula
     */
    private ArrayList<String> tokenise(String formula) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder part = new StringBuilder();

        for (int i = 0; i < formula.length(); i++) {
            String token = String.valueOf(formula.charAt(i));
            if (Operator.isOperator(token)) {
                Operator operator = Operator.getOperator(token);
                if (operator == Operator.MINUS) {
                    if (isUnaryMinus (i, formula)) {
                        part.append(token);
                        continue;
                    }
                }
                addPartToResult(part, result);
                result.add(token);
                part.setLength(0);
            } else part.append(token);

            if (isCharacterLastInArray(i, formula.length())) {
                addPartToResult(part, result);
            }
        }
        return result;
    }

    /**
     * Checks if this minus is unary
     *
     * @param i       index of the character in the untokenised formula
     * @param formula untokenised formula
     * @return true if unary and false otherwise
     */
    private boolean isUnaryMinus(int i, String formula) {
        return i == 0 || Operator.isOperator(String.valueOf(formula.charAt(i - 1)));
    }

    /**
     * Adds part to result if part is not empty.
     * Prevents bug when empty space is added to result.
     *
     * @param part   a number being constructed
     * @param result tokenised formula
     */
    private void addPartToResult(StringBuilder part, ArrayList<String> result) {
        if (!part.isEmpty()) {
            result.add(part.toString());
        }
    }

    /**
     * Checks whether current character is the last one
     *
     * @param i           current index
     * @param arrayLength formula length
     * @return true if last part should be added
     */
    private static boolean isCharacterLastInArray(int i, int arrayLength) {
        return i == arrayLength - 1;
    }

    /**
     * Parses tokenised formula to reverse polish notation
     * and saves it as the class field
     *
     * @param formula tokenised formula
     */
    private void parseToPostfix(ArrayList<String> formula) {
        Stack<Operator> stack = new Stack<>();
        for (String token : formula) {
            if (Operator.isOperator(token)) {
                Operator operator = Operator.getOperator(token);
                if (operator == Operator.PARENTHESIS_CLOSE) {
                    while (!stack.empty() && stack.peek() != Operator.PARENTHESIS_OPEN) {
                        parsedFormula.add(stack.pop().getToken());
                    }
                    if (stack.empty()) {
                        throw new IllegalReceiveException("Missing required parenthesis open");
                    }
                    stack.pop();
                } else if (operator == Operator.PARENTHESIS_OPEN) {
                    stack.push(operator);
                } else {
                    while (!stack.empty() && stackShouldBeCleaned(operator, stack)) {
                        parsedFormula.add(stack.pop().getToken());
                    }
                    stack.push(operator);
                }
            } else {
                parsedFormula.add(token);
            }
        }
        addRemainingOperators(stack);

    }

    /**
     * Checks whether operators from the stack should be added to formula
     *
     * @param token current operator
     * @param stack stack with operators
     * @return true if stack should be cleaned and otherwise false
     */

    private boolean stackShouldBeCleaned(Operator token, Stack<Operator> stack) {
        if (token == Operator.POWER) {
            return stack.peek().getPriority() > token.getPriority();
        } else {
            return stack.peek().getPriority() >= token.getPriority();
        }
    }

    /**
     * Adds all remaining operators to parsed formula
     *
     * @param stack stack with operators
     */
    private void addRemainingOperators(Stack<Operator> stack) {
        while (!stack.empty()) {
            Operator operator = stack.pop();
            if (operator == Operator.PARENTHESIS_OPEN) {
                throw new IllegalReceiveException("Missing required parenthesis close");
            }
            parsedFormula.add(operator.getToken());
        }
    }

}
