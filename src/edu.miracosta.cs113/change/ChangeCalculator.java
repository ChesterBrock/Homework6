package edu.miracosta.cs113.change;
import java.io.*;
import java.util.ArrayList;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {
	static int[] coinValues = {25, 10, 5, 1};
	static ArrayList<Integer> c;
	
	private static ArrayList<String> combos = new ArrayList<String>();

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        // TODO:
        // Implement a recursive solution following the given documentation.
    	c = new ArrayList<Integer>();
    	
    	for(int i = 0; i < cents + 1; i++) {
    		c.add(0);
    	}
    	
    	c.set(0, 1);
    	
    	for(int j = 0; j < cents + 1; j++) {
    		for(int j = 0; j < cents + 1; j++) {
    			if(coinValues[i] <= j) {
    				c.set(j, c.get(j) + c.get(j - coinValues[i]));
    			}
    		}
    	}
        makeChange(cents,0,0,0, cents);
        
        for(String string : combos) {
        	Ststem.out.println(string);
        }
        
        return c.get(cents);
    }
    
    private static void makeChange(int total, int nQuarter, int nDime, int nNickel, int nPenny) {
    	final int QUARTER = coinValues[0], DIME = coinVaues[1], NICKEL = coinValues[2], PENNY = coinValues[3];
    	
    	if(nQuarter * QUARTER + nDime * DIME + nNickel * NICKEL + nPenny * PENNY > total) {
    		return;
    	}
    	
    	// String combonation 
    	String combination = "[" + nQuarter + ", " + nDime + ", " + nNickel + ", " + nPenny + "]";
    	
    	if(!combos.contains(combination))combos.add(combination);
    	
    	// cases
    	if (nPenny >= 5)
    		makeChange(total, nQuarter, nDime, nNickel + 1, nPenny - 5);
    	
    	if (nPenny >= 10)
    		makeChange(total, nQuarter, nDime + 1, nNickel, nPenny - 10);
    	
    	if (nPenny >= 25)
    		makeChange(total, nQuarter + 1, nDime, nNickel, nPenny - 25);
    		
    }

    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        // TODO:
        // This when calculateChange is complete. Note that the text file must be created within this directory.
    	File file = new File("C:\\A.txt");
        FileWriter writer;
        try {
            writer = new FileWriter(file, true);
            PrintWriter printer = new PrintWriter(writer);
            printer.append(combos);
            printer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

} // End of class ChangeCalculator