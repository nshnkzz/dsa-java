

public class Runner {
    public static void main(String[] args) {
        //call the problems here
        int[] array = {3,9,7,3};
        int sum = 0;
        int isSubsetSum = DP.minimumDifference(array);
        System.out.println("No. of possible subset with sum: " + sum + " is: " + isSubsetSum);
    }
}
