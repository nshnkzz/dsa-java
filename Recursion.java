public class Recursion {

    static int cutRod(int price[], int n) {
        if (n <= 0) {
            return 0;
        }
        int maxProfit = 0;
        for (int i = 1; i <= n; i++) {
            maxProfit = Math.max(maxProfit, price[i - 1] + cutRod(price, n - i));
        }
        return maxProfit;
    }
    
}
