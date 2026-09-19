package com.shpp.p2p.cs.iklindukhov.assignment11;

import java.util.ArrayList;

/**
 * Prints information about silhouettes found in the image.
 * The class outputs the number of silhouettes and their
 * colors and sizes.
 */
public class InfoPrinter {
    /**
     * List of silhouettes to be printed.
     */
    private final ArrayList<Silhouette> silhouettes;

    /**
     * Creates an information printer for the specified silhouettes.
     *
     * @param silhouettes list of silhouettes to print
     */
    InfoPrinter(ArrayList<Silhouette> silhouettes) {
        this.silhouettes = silhouettes;
    }

    /**
     * Prints information about all found silhouettes.
     * If no silhouettes are found, a corresponding message is printed.
     */
    public void printSilhouettesInfo() {
        if (silhouettes.isEmpty()) {
            System.out.println("No silhouettes were found");
        } else {
            printSilhouettesAmount();
            for (int i = 0; i < silhouettes.size(); i++) {
                Silhouette silhouette = silhouettes.get(i);
                System.out.println("Silhouette number " + (i + 1) + ":");
                printSilhouettesColors(silhouette);
                printSilhouettesSize(silhouette);
            }
        }
    }

    /**
     * Prints the size of the specified silhouette.
     *
     * @param silhouette silhouette whose size is printed
     */
    private void printSilhouettesSize(Silhouette silhouette) {
        System.out.println("Size: " + silhouette.getSize() + "\n");
    }

    /**
     * Prints the color of the specified silhouette.
     *
     * @param silhouette silhouette whose color is printed
     */
    private void printSilhouettesColors(Silhouette silhouette) {
        System.out.println("Color: " + silhouette.getColor());
    }

    /**
     * Prints the total number of silhouettes.
     */
    private void printSilhouettesAmount() {
        System.out.println("Silhouettes amount: " + silhouettes.size() + "\n");
    }
}

