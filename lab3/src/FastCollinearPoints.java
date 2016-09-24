import java.util.Arrays;
import java.util.Comparator;
class FastCollinearPoints
{
	private int lineNum = 0;
	private LineSegment[] segSet;
	private int pointNum;
	private Point[] points;
	private boolean isFind = false;
	private double[] slopes;
	public FastCollinearPoints(Point[] points)
	{
		segSet = new LineSegment[points.length/4];
		Arrays.sort(points);
		this.points = points;
		pointNum = points.length;
		slopes = new double[pointNum-1];
		findSegments();
	}
	
	private void findSegments()
	{
		for(int i = 0; i<pointNum; i++){
			int temp = i;
			/*calculate the each point slopes with point i*/
			for(int j = 0; j<pointNum-i && temp<pointNum ; j++){
				slopes[j] = points[i].slopeTo(points[++temp]);
			}
			
			Arrays.sort(slopes);
			/*find ajacent 3 points or more */
			for(int j = 0; j<pointNum-2;j++){
				int ajacentNum = 0;
				boolean startFlg = false;
				if(slopes[j] == slopes[j+1]){
					if(ajacentNum++ == 0) startFlg = true;
				}
				else if(startFlg == true && ajacentNum <3)
						startFlg = false;
				else{
					if(startFlg == )
				}

			}
				
		}
	}
	
	public int numberOfSegments() {return lineNum;}
	
	public LineSegment[] segments() {
		return segSet;
	}
}
	