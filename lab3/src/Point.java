/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if(this.x == that.x && this.y == that.y)
            return Double.NEGATIVE_INFINITY;
        if(this.x == that.x && this.y != that.y)
            return Double.POSITIVE_INFINITY;
        return (double)(that.y-this.y) / (double)(that.x - this.x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if(this.y < that.y)
            return -1;
        if(this.y == that.y && this.x < that.x)
            return -1;
        if(this.y == that.y && this.x == that.x)
            return 0;
        else
            return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
    	Comparator<Point> endian = new BySlope(); 
        return endian;
    }
    
    private class BySlope implements Comparator<Point>
    {
        public int compare(Point A, Point B)
        {
            if(slopeTo(A) < slopeTo(B))
            	return -1;
            if(slopeTo(A) == slopeTo(B))
            	return 0;
            else
            	return 1;
        }
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	FastCollinearPoints fastLines;
    	final int haha = 6;
        while(true){
	    	Point[] p = new Point[haha];
	    //	BruteCollinearPoints lines;
	    	for(int i = 0; i<haha; i++)
	    	{
	    		int x = StdIn.readInt();
	    		int y = StdIn.readInt();
	    		p[i] = new Point(x,y);
	    		
	    	}
	//        Comparator<Point> basep0 = p[0].slopeOrder();
	//        
	//        int result = basep0.compare(p[1],p[2]);
	//        StdDraw.enableDoubleBuffering();
	//        StdDraw.setXscale(0, 32768);
	//        StdDraw.setYscale(0, 32768);
	// 
	//        p[0].draw();
	//        p[1].draw();
	//        p[2].draw();
	//        p[0].drawTo(p[1]);
	//        p[0].drawTo(p[2]);
	//        StdDraw.show();
	//        if(result < 0) StdOut.println("p0-p1 < p0-p2");
	//        else StdOut.println("p0-p1 > p0-p2");
//	    	lines = new BruteCollinearPoints(p);
//			for(LineSegment test : lines.segments())
//			{
//				StdOut.println(test);
//			}
	    	
	    	fastLines = new FastCollinearPoints(p);
	    	StdOut.print("line segments:");
			for(LineSegment test : fastLines.segments())
			{
				StdOut.println(test);
			}
        }  
        
    }
}