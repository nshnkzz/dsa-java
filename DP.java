import java.util.Arrays;
import java.util.HashMap;

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

    /**
     * https://www.geeksforgeeks.org/problems/rod-cutting0840/1
     * @problem statement: Given a rod of length N inches and an array of prices, price[]. price[i] denotes
     *  the value of a piece of length i. Determine the maximum value obtainable by cutting up the rod and selling the pieces.
     * 
     * @param price
     * @param n
     * @return maximum value obtainable by cutting up the rod and selling the pieces
     * 
     * @Explaination: Dynamic Programming Array (dp[]): The dp array is used to store the optimal values for different lengths of rods. dp[i] represents the maximum profit that can be obtained by cutting a rod of length i.
     * 
     * Outer Loop (i): This loop iterates over each possible length of rod from 1 to n. For each length i, we compute the maximum profit that can be obtained.
     * 
     * Inner Loop (j): For each length i, this loop iterates over each possible way to cut the rod of length i. It starts from 1 and goes up to i. For each cut length j, we calculate the profit that can be obtained by cutting the rod at length j, adding the price of the cut piece (price[j - 1]) to the optimal profit of the remaining rod (dp[i - j]).
     * 
     * Maximum Profit (maxp): For each rod length i, we keep track of the maximum profit (maxp) that can be obtained by considering all possible ways to cut the rod.
     * 
     * Storing the  Optimal Value: After computing the maximum profit for the current length of rod i, we store this value in the dp array at index i.
     *
     * Returning the Result: Finally, we return dp[n], which represents the maximum profit that can be obtained by cutting a rod of length n.
     */
    public int cutRodBottomUp(int price[], int n) {
        //code here
        int dp[] = new int[n+1];
        for(int i = 1; i<=n; i++)
        {
            int maxp = 0;
            for(int j =1; j<=i; j++)
            {
                maxp= Math.max(maxp, price[j-1] + dp[i-j]);
            }
            dp[i] = maxp;
        }
        return dp[n];
    }

    /**
     * https://www.geeksforgeeks.org/problems/rod-cutting0840/1
     * @problem statement: Given a rod of length N inches and an array of prices, price[]. price[i] denotes
     *  the value of a piece of length i. Determine the maximum value obtainable by cutting up the rod and selling the pieces.
     * 
     * @param price
     * @param n
     * @return
     */
    public int cutRodTopDown(int price[], int n) {
        //code here
        int[][] dp = new int[price.length + 1][n+1];
        for(int i=1; i<=price.length; i++)
        {
            for(int j =1; j<=n; j++)
            {
                if(i <= j)
                {
                    dp[i][j] = Math.max(dp[i-1][j], price[i-1] + dp[i][j-i]);
                }
                else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[price.length][n];
    }

    /**
     * https://www.geeksforgeeks.org/problems/coin-change2448/1
     * @param coins
     * @param N
     * @param sum
     * @return
     */
    public long coinChangeWaysTopDown(int coins[], int N, int sum) {
        // code here.
        long[][] dp = new long[N + 1][sum + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        // Fill the dp table
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= sum; j++) {
                // Don't include coin i in the solution
                dp[i][j] = dp[i - 1][j];

                // Include coin i if it doesn't exceed the sum
                if (coins[i - 1] <= j) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[N][sum];
    }

    /**
     * https://www.geeksforgeeks.org/problems/coin-change2448/1
     * @param coins
     * @param N
     * @param sum
     * @return
     */
    public long coinChangeWaysBottomUp(int coins[], int N, int sum) {
        // code here.
        long[] dp = new long[sum+1];
        dp[0] = 1;
        
        for(int i=0;i<N;i++){
            for(int j=1;j<sum+1;j++){
                int coinValue = coins[i];
                
                if(j-coinValue>=0){
                    dp[j] = dp[j] + dp[j-coinValue];
                }
            }
        }   
        return dp[sum];
    }
    /**
     * https://leetcode.com/problems/coin-change/
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeMinimiseTopDown(int[] coins, int amount) {
        int coin = coins.length;
        int[][] dp = new int [coin+1][amount+1];
        int maxval = Integer.MAX_VALUE-1;
        for(int i = 0; i<=amount; i++)
        {
            dp[0][i] = maxval;
        }
        for(int j = 1; j<= amount; j++)
        {
            if(j%coins[0] == 0)
            {
                dp[1][j] = j/coins[0];
            }
            else dp[1][j] = maxval;
        }
        if(coin == 1) 
        return (dp[coin][amount] == maxval)? -1: dp[coin][amount];
        for(int i=2; i<=coin; i++)
        {
            for(int j =1; j<=amount; j++)
            {
                if(coins[i-1] <= j)
                {
                    dp[i][j] = Math.min(dp[i][j-coins[i-1]]+1, dp[i-1][j]);
                }
                else dp[i][j] = dp[i-1][j];
            }
        }
        return (dp[coin][amount] == maxval)? -1: dp[coin][amount];
    }

    /**
     * https://leetcode.com/problems/coin-change/
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeMinimiseBottomUp(int[] coins, int amount) {
        if (amount == 0) {
            return 0; 
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE-1); 

        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE-1 ? -1 : dp[amount];
    }

    public static int longestCommonSubsequence(String A, String B) {
        int[][] dp = new int[A.length() + 1][B.length() + 1];
        // No need to fill the array with -1 as the default value is already 0
        
        return longestCommonSubsequence(A, B, A.length(), B.length(), dp);
    }
    
    private static int longestCommonSubsequence(String A, String B, int alen, int blen, int[][] dp) {
        if (alen == 0 || blen == 0) 
        {
            return 0;
        }
        if (dp[alen][blen] != 0) 
        {
            return dp[alen][blen];
        }
        if (A.charAt(alen - 1) == B.charAt(blen - 1)) 
        {
            dp[alen][blen] = 1 + longestCommonSubsequence(A, B, alen - 1, blen - 1, dp);
            return dp[alen][blen];
        } 
        else 
        {
            dp[alen][blen] = Math.max(longestCommonSubsequence(A, B, alen - 1, blen, dp),
                                      longestCommonSubsequence(A, B, alen, blen - 1, dp));
            return dp[alen][blen];
        }
    }

    public static int utility(String s, int i, int j, boolean isTrue, HashMap<String, Integer> dp)
    {
        if(i>j)
        {
            return 0;
        }
        
        if(i == j)
        {
            if(isTrue)
            {
                return s.charAt(i) == 'T' ? 1 : 0;
            }
            
                return s.charAt(i) == 'F' ? 1 : 0;
        }
        if(dp.containsKey(dpStringBuilder(i, j, isTrue)))
        {
            return dp.get(dpStringBuilder(i, j, isTrue));
        }
        
        int ans = 0;
        
        for(int k = i+1; k<=j; k+=2)
        {
            if(k>=s.length())
            break;
            int lt = dp.containsKey(dpStringBuilder(i, k-1, true)) ? dp.get(dpStringBuilder(i, k-1, true)) : utility(s, i, k-1, true, dp);
            int lf = dp.containsKey(dpStringBuilder(i, k-1, false)) ? dp.get(dpStringBuilder(i, k-1, false)) : utility(s, i, k-1, false, dp);
            int rt = dp.containsKey(dpStringBuilder(k+1, j, true)) ? dp.get(dpStringBuilder(k+1, j, true)) : utility(s, k+1, j, true, dp);
            int rf = dp.containsKey(dpStringBuilder(k+1, j, false)) ? dp.get(dpStringBuilder(k+1, j, false)) : utility(s, k+1, j, false, dp);
            
            if(s.charAt(k) == '&')
            {
                if(isTrue)
                {
                    ans = ans + lt*rt;
                }
                else ans = ans + lf*rt + lf*rf + lt*rf;
                
            }
            if(s.charAt(k) == '|')
            {
                if(isTrue)
                {
                    ans = ans + lt*rt + lt*rf + lf*rt;
                }
                else ans = ans + lf*rf;
                
            }
            if(s.charAt(k) == '^')
            {
                if(isTrue)
                {
                    ans = ans + lf*rt + lt*rf;
                }
                else ans = ans + lf*rf + lt*rt;
                
            }
            dp.put(dpStringBuilder(i, j, isTrue), ans);
        }
        return ans;   
    }
    private static String dpStringBuilder(int i, int j, boolean isTuru)
    {
        String separator = "*_*";
        return String.valueOf(i) + separator + String.valueOf(j) + String.valueOf(isTuru);
    }
    public static int countWays(int N, String S)
    {
        HashMap<String, Integer> dp = new HashMap<>();
        return utility(S, 0, N - 1, true, dp);
    }
}
