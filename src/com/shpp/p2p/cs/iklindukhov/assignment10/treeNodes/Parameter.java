package com.shpp.p2p.cs.iklindukhov.assignment10.treeNodes;

import com.shpp.p2p.cs.iklindukhov.assignment10.ParametersDataBase;

/**
 * Represents a parameter used in a formula.
 */
public class Parameter implements ICalc {
    /**
     * Name of the parameter, used to get its value from database
     */
    private final String parameter;
    /**
     * Database with all specified parameters and their values
     */
    private final ParametersDataBase parameters;

    /**
     * Represents a parameter used in a formula, so it can be added to a tree as a node
     *
     * @param parameter  name of the parameter, used to get its value from database
     * @param parameters database with all specified parameters and their values
     */
    public Parameter(String parameter, ParametersDataBase parameters) {
        this.parameter = parameter;
        this.parameters = parameters;
    }

    /**
     * Gets the value of specified parameter from database
     *
     * @return value of the parameter
     */
    @Override
    public double calculate() {
        return parameters.getValueByKey(parameter);
    }
}
