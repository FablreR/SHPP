package com.shpp.p2p.cs.iklindukhov.assignment10;

import com.shpp.p2p.cs.iklindukhov.assignment10.tokens.Operator;
import com.shpp.p2p.cs.iklindukhov.assignment10.tokens.Parenthesis;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Tokenises formula and parses it to reverse polish notation
 */
public class FormulaParser {
    /**
     * Formula, parsed to reverse polish notation
     */
   private final ArrayList<String> parsedFormula = new ArrayList<>();


    /**
     * Tokenises formula and parses it to reverse polish notation
     *
     * @param formula received formula
     */
    public FormulaParser(String formula) {

        String result = formula.replaceAll("\\s+", "");
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Formula is missing");
        }
        parseToPostfix(tokenise(result));
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
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < formula.length(); i++) {
            String token = String.valueOf(formula.charAt(i));
            if (Operator.isOperator(token) || Parenthesis.isParenthesis(token)) {
                if (isUnaryMinus(i, formula, token)) {
                    buffer.append("-1");
                    addBufferToResult(buffer, result);
                    result.add(Operator.MULTIPLY.getToken());
                    continue;
                }
                addBufferToResult(buffer, result);
                result.add(token);
            } else {
                buffer.append(token);
            }

            if (isCharacterLastInArray(i, formula.length())) {
                addBufferToResult(buffer, result);
            }
        }
        return result;
    }

    /**
     * Checks if this minus is unary
     *
     * @param i       index of the character in the untokenised formula
     *                0 means first character and i-1 previous character
     * @param formula untokenised formula
     * @param token   potential unary minus
     * @return true if unary and false otherwise
     */
    private boolean isUnaryMinus(int i, String formula, String token) {
        /* Potential null-pointer, cause token can be parenthesis */
        Operator operator = Operator.getOperator(token);
        if (operator == Operator.MINUS) {
            if (i == 0) {
                return true;
            } else {
                String previousOperator = String.valueOf(formula.charAt(i - 1));
                return Operator.isOperator(previousOperator) ||
                        Parenthesis.isParenthesis(previousOperator);
            }

        }
        return false;

    }

    /**
     * Adds buffer to result if buffer is not empty.
     * Prevents bug when empty space is added to result.
     *
     * @param buffer   a number being constructed
     * @param result tokenised formula
     */
    private void addBufferToResult(StringBuilder buffer, ArrayList<String> result) {
        if (!buffer.isEmpty()) {
            result.add(buffer.toString());
            buffer.setLength(0);
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
        Stack<String> stack = new Stack<>();
        for (String token : formula) {
            if (Parenthesis.isParenthesis(token)) {
                Parenthesis parenthesis = Parenthesis.getParenthesis(token);
                if (parenthesis == Parenthesis.PARENTHESIS_CLOSE) {
                    while (!stack.empty() && stack.peek() != Parenthesis.PARENTHESIS_OPEN.getToken()) {
                        parsedFormula.add(stack.pop());
                    }
                    if (stack.empty()) {
                        throw new IllegalArgumentException("Missing required parenthesis open");
                    }
                    stack.pop();
                } else {
                    stack.push(parenthesis.getToken());
                }
            } else if (Operator.isOperator(token)) {
                Operator operator = Operator.getOperator(token);
                while (!stack.empty() && stackShouldBeCleaned(operator, stack)) {
                    parsedFormula.add(stack.pop());
                }
                stack.push(operator.getToken());
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

    private boolean stackShouldBeCleaned(Operator token, Stack<String> stack) {
        String previousOperator = stack.peek();
        if (Operator.isOperator(previousOperator)) {
            int previousOperatorPriority = Operator.getOperator(previousOperator).getPriority();
            int currentOperatorPriority = token.getPriority();
            return token == Operator.POWER ?
                    previousOperatorPriority > currentOperatorPriority :
                    previousOperatorPriority >= currentOperatorPriority;
        }
        return false;
    }

    /**
     * Adds all remaining operators to parsed formula
     *
     * @param stack stack with operators
     */
    private void addRemainingOperators(Stack<String> stack) {
        while (!stack.empty()) {
            String stackToken = stack.pop ();
            if (stackToken == Parenthesis.PARENTHESIS_OPEN.getToken()) {
                throw new IllegalArgumentException("Missing required parenthesis close");
            }
            parsedFormula.add(stackToken);
        }
    }

}
