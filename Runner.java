

public class Runner {
    public static void main(String[] args) {
        //call the problems here
        int[] array = {1,2,5};
        int sum = 9;
        Boolean isSubsetSum = DP.canEquallyPartition(array);
        System.out.println("Is subset sum: " + isSubsetSum);
    }
}
