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
     * https://leetcode.com/problems/subarray-sum-equals-k/description/
     * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
     * A subarray is a contiguous non-empty sequence of elements within an array.
     * 
     * this solution also considers non-contiguous elements. for contiguous case, use SlidingWindow or hashmap.
     * @param arr
     * @param sum
     * @param isToUseDP
     * @return number os subsets of array with given sum
     */
    public static int countSubarraySum(int[] arr, int sum, boolean isToUseSlidingWindow) {
        if(isToUseSlidingWindow)
        {
            return SlidingWindow.countSubarraySum(arr, sum, !isToUseSlidingWindow);
        }
        int N = arr.length;
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
                dp[i-1][j] + dp[i-1][j-arr[i-1]] :
                dp[i-1][j];
            }
        }

        //return
        return dp[N][sum];
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

    /**
     * https://www.geeksforgeeks.org/problems/minimum-sum-partition3317/1?utm_source=geeksforgeeks&utm_medium=article_practice_tab&utm_campaign=article_practice_tab
     * @param arr
     * @return the miniMUM difference between two subsets of an array of non negative integers.
     */
    public static int minimumDifference(int[] arr) {
        int N = arr.length;
        int sum = 0;
        for(int i: arr)
        sum += i;
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
        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;
    
        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--) {
            if (dp[N][j] == 1) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }

    /**
     * finds the number of subsets from the given array such that the difference between two equals the difference given.
     * @param n : size of array
     * @param d : given difference
     * @param arr ; given array
     * @return count of subsets.
     */
    public static int countPartitions(int n, int d, int arr[]){
        
        // Code here
        // just find target sum. viola!!
        int total = 0;
        for(int i : arr)
        total += i;
        int target = (d+total)/2;
        return countSubarraySum(arr, target, false);
    }

    /**
     * https://leetcode.com/problems/target-sum/
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        // just find target sum. viola!!
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        S = Math.abs(S);
        
        if (S > sum || (S + sum) % 2 != 0) {
            return 0;
        }
        
        int target = (S + sum) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        
        return dp[target];
    }
}
