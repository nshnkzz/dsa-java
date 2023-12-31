

public class Runner {
    public static void main(String[] args) {
        //call the problems here
        int[] array = {6, 34,  4, 12, 5, 2};
        int sum = 9;
        Boolean isSubsetSum = DPProblems.isSubsetSum(array.length, array, sum);
        System.out.println("Is subset sum: " + isSubsetSum);
    }
}
