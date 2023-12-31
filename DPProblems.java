public class DPProblems {
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
