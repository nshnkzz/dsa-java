import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.*;

public class Runner {
    public static void main(String[] args) {
        //call the problems here
        String a = "T^F|F";
        String b = "lrbbmqbabowkkab";
        List<String> lsr = Arrays.asList(
            "lrbbmqb", "cd", "r", "owkk");

        int[][] dp = new int[2][2];
        int [][] arr =  {{9, 7, 16, 5}, {1,-6,-7, 3}, {1, 8, 7, 9}, {7, -2, 0, 10}};
        
        // int isSubsetSum = DP.findCatalan(7);
        // System.out.println("LCSubsequnce is: " + sumZeroMatrix(arr));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        try {
        t = Integer.parseInt(br.readLine());
            while(t-- > 0){
                
                
                int[] nm = iinput(br, 2);
                
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public static int[] iinput(BufferedReader br, int n) throws IOException
        {
            String[] s = br.readLine().trim().split(" ");
            int[] a = new int[n];
            for(int i = 0; i < n; i++)
                a[i] = Integer.parseInt(s[0]);
            
            return a;
        }

   
    
}
