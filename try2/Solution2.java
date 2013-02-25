import java.io.File;

import java.util.*;



import java.util.Arrays;

public class Solution2 {

	private static final String yes = "YES";
	private static final String no = "NO";

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		try {
			input = new Scanner(new File("test.txt"));
		} catch (Exception e) {
			
		}
		boolean good = isGood(input);
		if (good) {
			System.out.println(yes);
		} else {
			System.out.println(no);
		}
	}

	public static boolean isGood(Scanner input) {
		int numStars = Integer.parseInt(input.nextLine());
		Point[] p = new Point[numStars];
		Angle[] angle = null;
        for (int i = 0; i < numStars; i++) {
        	String[] splitString = input.nextLine().split(" ");
        	p[i] = new Point(Double.parseDouble(splitString[0]), Double.parseDouble(splitString[1]));
        }
		
		for(int i=0;i<p.length-1;i++) {
			int k = p.length-i-1;
			angle = new Angle[k];
			
			for(int j=i+1;j<p.length;j++) {
				angle[j-i-1] = new Angle();
				double temp = (p[j].y-p[i].y)/(p[j].x-p[i].x);
				angle[j-i-1].angle = Math.atan(temp);
				angle[j-i-1].p = p[j];
			}
			Arrays.sort(angle);
			Angle previous = angle[0];
			// int c = 0;
			for(int n=1;n<k;n++) {
				if(previous.angle==angle[n].angle) {
				} else {
				}
				previous=angle[n];
			}
	
		
		}

		for (Angle a : angle) {
			System.out.println(a.angle);
		}

		double prevAngle = angle[0].angle;
		int c = 1;
		for (int i = 1;i<numStars-1;i++) {
			if (prevAngle == angle[i].angle) {
				c++;
			}
			if (c==4) {
				return true;
			}
			prevAngle = angle[i].angle;
		}

		return false;
	}
}

class Point{
	double x;
	double y;
	Point(double x, double y)
	{
		this.x =x;
		this.y =y;
	}
}

class Angle implements Comparable<Object>{
	Point p;
	double angle;
	public int compareTo(Object o) {
		Angle in = (Angle)o;
		if(this.angle<in.angle)
			return -1;
		else if(this.angle==in.angle)
			return 0;
		else
			return 1;
	}
}