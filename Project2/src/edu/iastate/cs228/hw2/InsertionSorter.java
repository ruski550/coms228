package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;


/**
 * @author Alexander Stevenson
 */

/**
 *
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter {
    // Other private instance variables if you need ...

    /**
     * The two constructors below invoke their corresponding superclass constructors. They
     * also set the instance variables algorithm and outputFileName in the superclass.
     */

    /**
     * Constructor takes an array of points.
     *
     * @param pts
     */
    public InsertionSorter(Point[] pts) {
        super(pts);
        outputFileName = "insert.txt";
        algorithm = "insertion sort";
    }


    /**
     * Constructor reads points from a file.
     *
     * @param inputFileName compare name of the input file
     */
    public InsertionSorter(String inputFileName) throws FileNotFoundException {
        super(inputFileName);
        outputFileName = "insert.txt";
        algorithm = "insertion sort";
    }


    /**
     * Perform insertion sort on the array points[] of the parent class AbstractSorter.
     *
     * @param order  1   by x-coordinate
     * 			     2   by polar angle
     */
    @Override
    public void sort(int order) {
       
    	long time = System.nanoTime();

        if (order == 1) {
            sortByAngle = false;
            setComparator();
        }
        if (order == 2) {
            sortByAngle = true;
            setComparator();
        }
        if (order != 1 && order != 2) {
            throw new IllegalArgumentException("Invalid range for order");
        }

    	for (int i = 1; i < points.length; i++) {
            for(int j = i ; j > 0 ; j--){
            	if (pointComparator.compare(points[j], points[j-1]) < 0) {                 
                   swap(j, j-1);
                }
            }
        }
        sortingTime = System.nanoTime() - time;
    }
}

