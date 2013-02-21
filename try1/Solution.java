import java.util.Collections;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {
    public static int[][] basinIds;
    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);

        int landSize = Integer.parseInt(input.nextLine());
        
        int[][] landArray = new int[landSize][landSize];
        for(int i = 0; i < landSize; i++) {
            String landRow = input.nextLine().trim();
            String[] landElements = landRow.split(" ");            
            for(int j = 0; j < landElements.length; j++) {
	            landArray[i][j] = Integer.parseInt(landElements[j]);
            }   
        }

        calculateBasins(landArray);
    }

    public static void calculateBasins(int area[][]) {
        int size = area.length;
        Point basins[][] = new Point[size][size];
        HashMap map = new HashMap();
        int basinId = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                basins[x][y] = lowestPlot(area, x, y);
                if(map.containsKey(basins[x][y])) {
                    String landElementString = (String) map.get(basins[x][y]);
                    String[] landElement = landElementString.split(" ");
                    int thisBasinId = Integer.parseInt(landElement[0]);
                    int basinSize = Integer.parseInt(landElement[1]);
                    basinSize++;//increase basin size
                    map.put(basins[x][y], thisBasinId+" "+basinSize);
                } else {
                    map.put(basins[x][y], basinId+" "+1);
                    basinId++;
                }
            }
        }

        ArrayList<Integer> uniqueBasins = new ArrayList<Integer>();
        int currentId = -1;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                String landElementString = (String) map.get(basins[x][y]);
                String[] landElement = landElementString.split(" ");
                int thisBasinId = Integer.parseInt(landElement[0]);
                int basinSize = Integer.parseInt(landElement[1]);
                if (thisBasinId > currentId) {
                    currentId = thisBasinId;
                    uniqueBasins.add(basins);
                }
            }
        }
        Collections.sort(uniqueBasins);

        for (int i : uniqueBasins) {
            System.out.print(i+" ");
        }
    }
    
    public static Point lowestPlot(int area[][], int x, int y) {
        int size = area.length;
        int leftX, leftY, rightX, rightY, topX, topY, bottomX, bottomY;
        int minX, minY, minValue;
        
        leftX = x;
        leftY = y - 1;
        
        rightX = x;
        rightY= y + 1;
        
        topX = x - 1;
        topY = y;
        
        bottomX = x + 1;
        bottomY = y;
        
        minX = x;
        minY = y;
        minValue = area[x][y];
        
        if (!isOutOfBounds(leftX, leftY, size)) {
            if (area[leftX][leftY] < minValue) {
                minX = leftX;
                minY = leftY;
                minValue = area[leftX][leftY];
            }
        }
        if (!isOutOfBounds(rightX, rightY, size)) {
            if (area[rightX][rightY] < minValue) {
                minX = rightX;
                minY = rightY;
                minValue = area[rightX][rightY];
            }
        }
        if (!isOutOfBounds(topX, topY, size)) {
            if (area[topX][topY] < minValue) {
                minX = topX;
                minY = topY;
                minValue = area[topX][topY];
            }
        }
        if (!isOutOfBounds(bottomX, bottomY, size)) {
            if (area[bottomX][bottomY] < minValue) {
                minX = bottomX;
                minY = bottomY;
                minValue = area[bottomX][bottomY];
            }
        }

        //return self if lowest point else recurse
        return (minX == x && minY == y) ? new Point(x, y) : lowestPlot(area, minX, minY);
    }
    
    public static boolean isOutOfBounds(int x, int y, int size) {
        return (x < 0 || x >= size || y < 0 || y >= size);
    }
}