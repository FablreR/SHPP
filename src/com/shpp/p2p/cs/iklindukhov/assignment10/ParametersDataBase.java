package com.shpp.p2p.cs.iklindukhov.assignment10;

import java.util.HashMap;
import static java.lang.Character.isLetter;
import static java.lang.Double.parseDouble;

/**
 * Receive and parse the parameters, then create a HashMap
 */
public class ParametersDataBase {
    /**
     * HashMap database with name of the parameters and their values
     */
    private final HashMap<String, Double> parameters = new HashMap<>();
    /**
     *
     */
    private final static String PARAMETER_SPLITTER = "=";
    /**
     * Receive and parse the parameters, then create a HashMap
     * i = 1, because i = 0 is reserved for the formula, not for the parameters
     * @param args array with parameters
     */
    public ParametersDataBase(String[] args) {
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
        if (parameters.containsKey(key)) {
            return parameters.get(key);
        }
        throw new IllegalArgumentException("Missing " + "\"" + key + "\"" + " parameter");
    }

    /**
     * Parses and then adds the parameter to HashMap
     *
     * @param parameter string containing name of the parameter and its value
     */
    private void parseAndAddParameter(String parameter) {
        String [] split =  parameter.split(PARAMETER_SPLITTER);
        if (split.length != 2) {
            throw new IllegalArgumentException("Parameter " + parameter + " is not valid");
        }
        String parameterName = leftPartParsing(split [0]);
        Double parameterValue = rightPartParsing(split [1]);
        parameters.put(parameterName, parameterValue);
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
        try {
           return parseDouble(rightPart);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("something except number was found in parameter`s value");
        }
    }

}
