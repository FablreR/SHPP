package com.shpp.p2p.cs.iklindukhov.assignment10;

/**
 * Node object for building a tree
 */
public class Node {
    /**
     * Value stored in the node
     */
    private final String DATA;
    /**
     * left child node
     */
    private Node left;
    /**
     * right child node
     */
    private Node right;

    /**
     * Creates a leaf
     *
     * @param data number or parameter stored in the node
     */
    public Node(String data) {
        this.DATA = data;
    }

    /**
     * Creates a node valid for binary operator
     *
     * @param data  binary operator stored in the node
     * @param left  left child node
     * @param right right child node
     */
    public Node(String data, Node right, Node left) {
        this.DATA = data;
        this.right = right;
        this.left = left;
    }

    /**
     * Creates a node valid for unary operator
     *
     * @param data      unary operator stored in the node
     * @param childNode child node
     */
    public Node(String data, Node childNode) {
        this.DATA = data;
        this.right = childNode;
        this.left = null;
    }

    /**
     *
     * @return left child node, or null if there is no left child
     */
    public Node getLeft() {
        return left;
    }

    /**
     *
     * @return right child node, or null if there is no left child
     */
    public Node getRight() {
        return right;
    }

    /**
     *
     * @return node value
     */
    public String getData() {
        return DATA;
    }
}
