import java.util.*;
class BruteCollinearPoints
{
	private int lineNum = 0;
	private LineSegment[] segSet;
	private int pointNum;
	private Point[] points;
	private boolean isFind = false;
	public BruteCollinearPoints(Point[] points)
	{
		segSet = new LineSegment[points.length/4];
		Arrays.sort(points);
		this.points = points;
		pointNum = points.length;
		findSegments();
	}
	
	private void findSegments()
	{
		for(int i = 0; i<pointNum; i++){
			Comparator<Point> baseP = points[i].slopeOrder();
			for(int j = i+1; j<pointNum; j++){
				isFind = false;
				for(int k = j+1; k<pointNum && isFind == false; k++)
					for(int l = k+1; l<pointNum && isFind == false; l++){
						if(baseP.compare(points[j],points[k]) == 0 &&
								baseP.compare(points[j],points[l])==0){
							LineSegment tmpLine = new LineSegment(points[i],points[l]);
							segSet[lineNum++] = tmpLine; 
							isFind = true;
						}
							
					}
			}
		}
	}
	
	public int numberOfSegments() {return lineNum;}
	
	public LineSegment[] segments() {
		return segSet;
	}
}
	