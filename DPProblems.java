/**
 * Solutions to Dynamic Problem questions.
 * Visit my Github repo: https://github.com/nshnkzz/dsa-java
 */
public class DPProblems {
    /**
     * https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
     * Given an array of non-negative integers, and a value sum, determine if there 
     * is a subset of the given set with sum equal to given sum. 
     * @param N : Number of elements
     * @param arr : Array of elements
     * @param sum : the target sum
     * @return boolean which indicates if subset with given sum is present
     */
    public static Boolean isSubsetSum(int N, int arr[], int sum){
        // initialize dp array
        int[][] dp = new int[N+1][sum+1];

        //initialize elements for N = 0
        for(int i=0; i<=N; i++)
        {
            dp[i][0] = 1;
        
        }

        // iterate through given array and fill the dp array.
        for(int i=1; i<=N; i++)
        {
            for(int j=1; j<=sum; j++)
            {
                dp[i][j] = (arr[i-1]<=j )?
                dp[i-1][j] | dp[i-1][j-arr[i-1]] :
                dp[i-1][j];
            }
        }

        //return
        return dp[N][sum] == 1;
    }
}
