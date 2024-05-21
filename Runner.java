

public class Runner {
    public static void main(String[] args) {
        //call the problems here
        String a = "T^F|F";
        String b = "abedfhr";
        int isSubsetSum = DP.utility(a, 0, 4, true);
        System.out.println("LCSubsequnce is: " + isSubsetSum);
    }
}
