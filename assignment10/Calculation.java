package com.shpp.p2p.cs.iklindukhov.assignment10;


/**
 * Provides mathematical operations for binary and unary operators
 */
public class Calculation {

    /**
     * Binary operations
     * Performs binary operations with received parameters
     *
     * @param operator binary operator, that determines which operation must be performed
     * @param left     left operand, which is value or function parameter
     * @param right    right operand, which is value or function parameter
     * @param base     database with parameters and their values
     * @return string with result of math operation
     */
    public static String calculation(String operator, String left, String right, ParametersDataBase base) {
        double leftNum = evaluateOperand(left, base);
        double rightNum = evaluateOperand(right, base);
        double result;
        switch (operator) {
            case "*" -> result = leftNum * rightNum;
            case "/" -> {
                if (rightNum == 0) {
                    throw new ArithmeticException("you are dividing on 0");
                } else result = leftNum / rightNum;
            }
            case "+" -> result = leftNum + rightNum;
            case "-" -> result = leftNum - rightNum;
            case "^" -> result = Math.pow(leftNum, rightNum);
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        }
        return String.valueOf(result);
    }


    /**
     * Unary operations
     * Performs unary operations with received parameters
     *
     * @param operator unary operator, that determines which operation must be performed
     * @param operand  number or parameter of the function
     * @param base     database with parameters and their values
     * @return string with result of math operation
     */
    public static String calculation(String operator, String operand, ParametersDataBase base) {
        double value = evaluateOperand(operand, base);
        double result;
        switch (operator) {
            case "tan" -> result = Math.tan(Math.toRadians(value));
            case "atan" -> result = Math.toDegrees(Math.atan(value));
            case "sin" -> result = Math.sin(Math.toRadians(value));
            case "cos" -> result = Math.cos(Math.toRadians(value));
            case "log10" -> result = Math.log10(value);
            /* gpt */
            case "log2" -> result = Math.log(value) / Math.log(2);
            case "sqrt" -> {
                if (value < 0) {
                    throw new IllegalArgumentException("Square root of the negative value: " + operator + " " + value);
                } else result = Math.sqrt(value);
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + operator);
        }
        return String.valueOf(result);
    }

    /**
     * Receives string with number or with parameter.
     * Number is converted to double and parameter
     * is used to get its value from database
     *
     * @param operand number or parameter we evaluate
     * @param base    database containing values of named parameters
     * @return the numeric value of the operand
     */
    private static double evaluateOperand(String operand, ParametersDataBase base) {
        if (isNumber(operand)) {
            return Double.parseDouble(operand);
        } else {
            return base.getValueByKey(operand);
        }
    }

    /**
     * Checks whether the specified string represents a valid numeric value.
     *
     * @param value the string to check
     * @return true if the value can be parsed as a double;
     * false otherwise
     */
    private static boolean isNumber(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
