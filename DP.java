/**
 * Solutions to Dynamic Problem questions.
 * Visit my Github repo: https://github.com/nshnkzz/dsa-java
 */
public class DP {
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

        // initialize elements for sum = 0
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1; // Subset sum of 0 is always possible with an empty set
        }

        // initialize elements for N = 0, sum > 0
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = 0; // Subset sum > 0 with an empty set is not possible
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

    /**
     * https://leetcode.com/problems/partition-equal-subset-sum/description/
     * Given an integer array nums, return true if you can partition the
     * array into two subsets such that the sum of the elements in both 
     * subsets is equal or false otherwise.
     * @param nums : the array
     * @return Boolean which indicates the p0ossibility of equally partitioning the 
     * given array into two equal subarrays.
     */
    public static boolean canEquallyPartition(int[] nums) {
        // this problem is basically the same as target sum problem. 
        // we just need to find out the sum 
        // the sum will be the half of the total sum of the array. 
        int sum = 0;
        for(int i : nums)
        {           
            sum += i;
        }

        // if sum is odd, there is no point of checking for partition otherwise 
        // we call is subset sum 
        return sum%2 == 0? isSubsetSum(nums.length, nums, sum/2) : false;
    }
}
