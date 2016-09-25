import java.util.Arrays;
import java.util.Comparator;
class FastCollinearPoints
{
	private int lineNum = 0;
	private LineSegment[] segSet;
	private int pointNum;
	private Point[] points;
	private Myline[] mylines;
//	private double[] slopes;
	public FastCollinearPoints(Point[] points)
	{
		segSet = new LineSegment[points.length/4];
		mylines = new Myline[points.length/4];
		Arrays.sort(points);
		this.points = points;
		pointNum = points.length;
	//	slopes = new double[pointNum-1];
		findSegments();
	}
	
	private class Myline
	{
		Point endian;
		double slope;
	}
	
	private class SlopeWithIndex implements Comparable<SlopeWithIndex>
	{
		double 	slope;
		Point	p;
		
		public int compareTo(SlopeWithIndex that)
		{
			if(this.slope < that.slope) return -1;
			if(this.slope > that.slope) return 1;
			else return 0;
		}
	}
	
	
	private void findSegments()
	{
		SlopeWithIndex[] slopes = new SlopeWithIndex[pointNum];
		for(int i = 0; i<pointNum; i++)
			slopes[i] = new SlopeWithIndex();
		
		/* last arry value as the ajacent detection*/
 		//slopes[pointNum-1].slope = Double.POSITIVE_INFINITY; 
		
		for(int i = 0; i<pointNum; i++){	
			/*set the reduant NA*/
			for(int j = 0; j<=i; j++) slopes[j].slope = Double.POSITIVE_INFINITY; 
			
			/*calculate the each point slopes with point i*/
			for(int j = i+1; j<pointNum; j++){
				slopes[j].slope = points[i].slopeTo(points[j]);
				slopes[j].p = points[j];
			}
			
			Arrays.sort(slopes);
			/*find ajacent 3 points or more */
			int ajacentNum = 0;
			boolean startFlg = false;
			for(int j = 0; j<pointNum-i-1;j++){
				if(slopes[j].slope == slopes[j+1].slope){
					if(ajacentNum++ == 0) startFlg = true;
				}
				else if(startFlg == true && ajacentNum <2){
						startFlg = false;
						ajacentNum = 0;
				}
				else if(startFlg == true){
					startFlg = false;
					Point endP  = slopes[j].p;
					for(int k = j-ajacentNum; k<j+1; k++){
						if(slopes[k].p.compareTo(endP) > 0) endP = slopes[k].p;
					}
					
					boolean isDuplicate = false;
					for(int l = 0; l<lineNum; l++)
						if(endP == mylines[l].endian && slopes[j].slope == mylines[l].slope )
							isDuplicate = true;
					if(isDuplicate == false ){
						segSet[lineNum] = new LineSegment(points[i],endP);
						mylines[lineNum] =  new Myline();
						mylines[lineNum].endian = endP;
						mylines[lineNum++].slope = slopes[j].slope;
					}
					ajacentNum = 0;
				}
			}	
		}
	}
	
	public int numberOfSegments() {return lineNum;}
	
	public LineSegment[] segments() {
		return segSet;
	}
}
	