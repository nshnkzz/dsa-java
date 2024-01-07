

public class Runner {
    public static void main(String[] args) {
        //call the problems here
        int[] array = {1,1,1,1,1};
        int sum = 3;
        int isSubsetSum = DP.countPartitions(array.length, sum, array);
        System.out.println("No. of possible subset with sum: " + sum + " is: " + isSubsetSum);
    }
}
