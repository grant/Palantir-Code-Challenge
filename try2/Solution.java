import java.io.File;
import java.awt.Point;

import java.util.*;

public class Solution {
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

	private static boolean isGood(Scanner input) {
        int numStars = Integer.parseInt(input.nextLine());
        List<Point> points = new ArrayList<Point>();

        for (int i = 0; i < numStars; i++) {
        	String[] splitString = input.nextLine().split(" ");
        	points.add(new Point(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1])));
        }

        // Test if points array works
        // for (int i = 0; i < points.size(); i++) {
        // 	System.out.println(points.get(i));
        // }

        //list of all points added to arraylist

        Map<Double, ArrayList<Point[]>> mp = new HashMap<Double, ArrayList<Point[]>>();

        for (int i = 0; i < points.size() - 1; i++) {//all starter points
        	for (int j = i + 1; j < points.size(); j++) {
        		// System.out.println(i + " " + j);
        		Point p1 = points.get(i);
	        	Point p2 = points.get(j);

	        	if (p2.x != p1.x) {//if valid slope
		        	double slope = (p2.y - p1.y) / (p2.x - p1.x);
		        	slope = (double)Math.round(slope * 100000) / 100000;//round to 5 decimals

		        	if (!mp.containsKey(slope)) {//add new arraylist
		        		mp.put(slope, new ArrayList<Point[]>());
		        	}

		        	Point[] pts = new Point[2];
		        	pts[0] = p1;
		        	pts[1] = p2;

	        		mp.get(slope).add(pts);
	        	}
        	}
        }

        //all starter point indexes are put in an array

        for (ArrayList<Point[]> pointsPairsArray : mp.values()) {
        	//pointStartIndex is the array of indexes of starting points with same slopes
        	Set uniquePoints = new HashSet();
       		if (pointsPairsArray.size() >= 4) {//if a possible line
			    for (int i = 0; i < pointsPairsArray.size(); i++) {
	        		Point[] pts = pointsPairsArray.get(i);
	        		Point pt1 = pts[0];
	        		Point pt2 = pts[1];
	        		// System.out.println(pt1);
	        		// System.out.println(pt2);
	        		uniquePoints.add(pt1);
	        		uniquePoints.add(pt2);
        		}
        	}

        	ArrayList<Point> uniquePointsArray = new ArrayList<Point>();

        	Iterator it = uniquePoints.iterator();
			while(it.hasNext()) {
				uniquePointsArray.add((Point)it.next());
			}

			if (uniquePointsArray.size() >= 4) {
				for (int i = 0; i < uniquePointsArray.size()-3; i++) {
					for (int j = i+1; j < uniquePointsArray.size()-2; j++) {
						for (int k = j+1; k < uniquePointsArray.size()-1; k++) {
							for (int l = k+1; l < uniquePointsArray.size()-0; l++) {
								if (isCollinear(uniquePointsArray.get(i), uniquePointsArray.get(j), uniquePointsArray.get(k), uniquePointsArray.get(l))) {
									return true;
								}
							}
						}
					}						
				}	
			}
		}
		return false;
	}

	public static boolean isCollinear(Point p1, Point p2, Point p3) {
		return (((p1.y - p2.y) * p3.x + (p2.x - p1.x) * p3.y + (p1.x * p2.y - p2.x * p1.y)) == 0);
	}

	public static boolean isCollinear(Point p1, Point p2, Point p3, Point p4) {
		return isCollinear(p1, p2, p3) && isCollinear(p1, p2, p4) && isCollinear(p1, p3, p4) && isCollinear(p2, p3, p4);
	}
}