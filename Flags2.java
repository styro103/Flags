/*
	Codility's Task "Flags" Faster Solution
	Run Time O(N*log(N))
*/

import java.lang.Math;
//import java.util.Arrays; //Optional

class Solution
{
    public int solution(int[] A)
    {
        int N = A.length; //Number of Elements
        
        if (N<3) return 0; //Need At Least Three Elements for Peak
        else if (N==3) return (A[1]<=A[0] || A[1]<=A[2]) ? 0 : 1; //If Exactly Three, Return Whether Second Element is Peak
        
        boolean [] peaks = getPeaks(A); //Boolean Stating if Peak
		int cp = countPeaks(peaks); //Count of Peaks
        
		if (cp<3) return cp; //If Less Than 3 Peaks, Return Number of Peaks
        for (int flags=cp; flags>2; flags--) //Can't Be More Flags Than Peaks
        {
            int f = flags; //Flags to Plant
			
			for (int pos=0; pos<N && f>0;) //Check Positions
			{
				if (peaks[pos]) //If Peak
				{
					f--; //Plant Flag
					pos += flags; //Move Position Up Number of Flags
				}
				else pos++; //No Peak, Increment Position
			}
			if (f==0) return flags; //All Flags Set, Reached Max Number
        }
        
        return 2; //Minimum Possible Distance Between Peaks is Two
    }
    
    boolean [] getPeaks(int [] A) //Find Peaks
	{
		boolean [] peaks = new boolean [A.length]; //Array of Booleans Stating If Peak
		//Arrays.fill(peaks, false); //Fill Array With False
		for (int i=1; i<A.length-1; i++) //Loop Through Array
            if (A[i]>Math.max(A[i-1], A[i+1])) peaks[i] = true; //If Peak, Set to True
		
		return peaks; //Return Array
	}
	
	int countPeaks(boolean [] peaks) //Count Peaks in Array
	{
		int cp = 0; //Count of Peaks
		
		for (int i=0; i<peaks.length; i++) //Loop Through Array
			if (peaks[i]) cp++; //If Peak, Increment Count
		
		return cp //Return Count
	}
}