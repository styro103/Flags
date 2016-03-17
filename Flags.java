/*
	Codility's Task "Flags" Fastest Solution
	Run Time O(N)
*/

import java.lang.Math;
import java.util.ArrayList;

class Solution 
{
    public int solution(int [] A) 
    {
        int N = A.length; //Number of Elements
        
        if (N<3) return 0; //Need At Least Three Elements for Peak
        else if (N==3) return (A[1]>Math.max(A[0], A[2])) ? 1 : 0; //If Exactly Three, Return Whether Second Element is Peak
        
        ArrayList <Integer> peaks = getPeaks(A); //Array List of Peak Indicies
        int cp = peaks.size(); //Number of Peaks
        
        if (cp<3) return cp; //If Less Than 3 Peaks, Return Number of Peaks

		int [] nextPeak = getNextPeak(peaks, N); //Array of Next Peak Position

        for (int flags=cp; flags>2; flags--) //Can't Be More Flags Than Peaks
        {
            int f = 0; //Flag to Plant
            for (int pos=0; pos<N && f<flags;) //Check Positions
            {
                pos = nextPeak[pos]; //Set Position to Index of Next Peak
                if (pos==-1) break; //No Following Peak
                f++; //Set Flag
                pos += flags; //Move Position Up Number of Flags
            }
            if (f==flags) return flags; //All Flags Set, Reached Max Number
        }
        
        return 2; //Minimum Possible Distance Between Peaks is Two
    }
    
    ArrayList <Integer> getPeaks (int [] A) //Get List of Peaks
    {
        ArrayList <Integer> pks = new ArrayList<Integer>(); //Array List of Peaks
        
        for (int i=1; i<A.length-1; i++) //Loop Through Array
            if (A[i]>Math.max(A[i-1], A[i+1])) pks.add(i); //If Peak, Add to List
        
        return pks; //Return List
    }
    
    int [] getNextPeak (ArrayList <Integer> p, int N) //Get Array of Next Peak Indicies
    {
        int [] np = new int [N]; //Array of Next Peak Indicies
        int t = p.size()-1; //For Traversing ArrayList
        
        np[N-1] = -1; //No Peaks After Last Element
        
        for (int i=N-2; i>-1; i--) //Loop in Reverse Order
        {
            if (i!=p.get(t)) np[i] = np[i+1]; //If Not Peak at This Index, Next Peak is Succeding Index's Next Peak
            else //Index Contains Peak
            {
                np[i] = i; //Set Index as Next Peak
                if (t>0) t--; //Decrement Array List Traverser
            }
        }
        
        return np; //Return Array of Next Peak Indicies
    }
}