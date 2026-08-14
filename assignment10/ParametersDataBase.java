package com.shpp.p2p.cs.iklindukhov.assignment10;

import acm.util.ErrorException;

import java.util.HashMap;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import static java.lang.Double.parseDouble;

/**
 * Receive and parse the parameters, then create a HashMap
 */
public class ParametersDataBase {
    /**
     * HashMap database with name of the parameters and their values
     */
    private final HashMap<String, Double> PARAMETERS;

    /**
     * Receive and parse the parameters, then create a HashMap
     *
     * @param args array with parameters
     */
    public ParametersDataBase(String[] args) {
        PARAMETERS = new HashMap<>();
        for (int i = 1; i < args.length; i++) {
            String parameter = args[i].replaceAll("\\s+", "");
            parseAndAddParameter(parameter);
        }
    }

    /**
     * Check if the parameter exists in the database, and if so, return its value
     *
     * @param key parameters name
     * @return parameters value
     */
    public double getValueByKey(String key) {
        if (PARAMETERS.containsKey(key)) {
            return PARAMETERS.get(key);
        }
        throw new ErrorException("Missing " + "\"" + key + "\"" + " parameter");
    }


    /**
     * Parses and then adds the parameter to HashMap
     *
     * @param parameter string containing name of the parameter and its value
     */
    private void parseAndAddParameter(String parameter) {
        String leftPart = "";
        String rightPart = "";
        boolean isEqualityPresent = false;
        for (int i = 0; i < parameter.length(); i++) {
            char currentChar = parameter.charAt(i);
            if (currentChar == '=') {
                if (!isEqualityPresent) {
                    leftPart = parameter.substring(0, i);
                    rightPart = parameter.substring(i + 1);
                    isEqualityPresent = true;
                } else throw new IllegalArgumentException("two or more \"=\" operators are present");
            }
        }
        if (!isEqualityPresent) {
            throw new IllegalArgumentException("\"=\" operator is missing in the parameter");
        }
        String parameterName = leftPartParsing(leftPart);
        Double parameterValue = rightPartParsing(rightPart);
        PARAMETERS.put(parameterName, parameterValue);
    }

    /**
     * Parses parameter name
     *
     * @param leftPart part containing name of the parameter
     * @return parsed name of the parameter
     */
    private String leftPartParsing(String leftPart) {
        if (leftPart.isEmpty()) {
            throw new IllegalArgumentException("parameter name wasnt found");
        }
        StringBuilder parameterName = new StringBuilder();
        for (int i = 0; i < leftPart.length(); i++) {
            char currentChar = leftPart.charAt(i);
            if (isLetter(currentChar)) {
                parameterName.append(currentChar);
            } else throw new IllegalArgumentException("numbers are present in the parameter name");
        }
        return parameterName.toString();
    }

    /**
     * Parses parameter value
     *
     * @param rightPart part containing value of the parameter
     * @return parsed value of the parameter
     */
    private Double rightPartParsing(String rightPart) {
        if (rightPart.isEmpty()) {
            throw new IllegalArgumentException("parameter value wasnt found");
        }
        StringBuilder parameterValue = new StringBuilder();
        for (int i = 0; i < rightPart.length(); i++) {
            char currentChar = rightPart.charAt(i);
            if (i == 0 && currentChar == '-') {
                parameterValue.append(currentChar);
                continue;
            }
            if (isDigit(currentChar)) {
                parameterValue.append(currentChar);
            } else throw new IllegalArgumentException("something except digits was found in parameter`s value");
        }
        return parseDouble(String.valueOf(parameterValue));
    }

}
