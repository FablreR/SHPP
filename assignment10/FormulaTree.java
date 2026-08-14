package com.shpp.p2p.cs.iklindukhov.assignment10;

import acm.util.ErrorException;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Tree consisting of nodes. Contains postfix formula
 */
public class FormulaTree {

    /**
     * The first node in the tree
     */
    private final Node ROOT;
    /**
     * Database with function parameters and their values
     */
    private final ParametersDataBase BASE;

    /**
     * Constructs tree-structure from postfix formula
     *
     * @param formula postfix formula
     * @param base    database with function parameters and their values
     */
    public FormulaTree(ArrayList<String> formula, ParametersDataBase base) {
        this.BASE = base;
        LinkedList<Node> list = new LinkedList<>();
        for (String token : formula) {
            if (Operator.isOperator(token)) {
                Operator operator = Operator.getOperator(token);
                Node right = list.pop();
                if (isOperatorUnary(operator)) {
                    list.push(new Node(token, right));
                } else {
                    Node left = list.pop();
                    list.push(new Node(token, right, left));
                }
            } else {
                list.push(new Node(token));
            }
        }
        this.ROOT = list.pop();
    }

    /**
     * Checks if operator is unary i.e. operator has priority 4
     *
     * @param operator operator that pretends to be unary
     * @return true if operator is unary;
     * false otherwise
     */
    private boolean isOperatorUnary(Operator operator) {
        return operator.getPriority() == 4;
    }

    /**
     * Calls tree parsing and result evaluation
     *
     * @return result of the formula
     */
    public String parseTheTree() {
        String result = treeParsing(ROOT);
        return evaluateResult(result);
    }

    /**
     * Parses the tree and calculates the value of each operator.
     * For unary operators, only the right subtree is evaluated.
     * For binary operators, both left and right subtrees are evaluated.
     *
     * @param node current node of the tree
     * @return calculated value of the subtree
     */
    private String treeParsing(Node node) {
        String nodeData = node.getData();
        if (!Operator.isOperator(nodeData)) {
            return nodeData;
        }
        String left = null;
        if (node.getLeft() != null) {
            left = treeParsing(node.getLeft());
        }
        String right = treeParsing(node.getRight());

        if (left == null) {
            return Calculation.calculation(nodeData, right, BASE);
        }
        return Calculation.calculation(nodeData, left, right, BASE);
    }

    /**
     * Evaluates result, so it is always a number, and if not, throws an error
     * Checks if result is a parameter, if so, replaces it with its value.
     * Otherwise, checks if result is a number, if so returns this number.
     * Otherwise, throws missingParameter ErrorException from getValueByKey.
     *
     * @param result result from treeParsing
     * @return evaluated result or missingParameter error
     */
    private String evaluateResult(String result) {
        try {
            return String.valueOf(BASE.getValueByKey(result));
        } catch (ErrorException missingParameter) {
            try {
                Double.parseDouble(result);
            } catch (NumberFormatException resultNotNumber) {
                BASE.getValueByKey(result);
            }
            return result;
        }
    }

}
