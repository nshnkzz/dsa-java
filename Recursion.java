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

    static int LCS(String a, String b)
    {
        return LCSWrapper(a, b, a.length()-1, b.length()-1);
    }

    static int LCSWrapper(String a, String b, int aLen, int bLen)
    {
        if(aLen == 0 || bLen == 0)
        {
            return 0;
        }
        
        if(a.charAt(aLen) == b.charAt(bLen))
        {
            System.out.println(a.charAt(aLen));
            return 1 + LCSWrapper(a, b, aLen-1, bLen-1);
        }
        else 
        {
            return Math.max(LCSWrapper(a, b, aLen, bLen-1), LCSWrapper(a, b, aLen-1, bLen));
        }
    }

    public static int booleanParenthesization(String s, int i, int j, boolean isTrue)
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
        
        int ans = 0;
        
        for(int k = i+1; k<=j; k+=2)
        {
            if(k>=s.length())
            break;
            int lt = booleanParenthesization(s, i, k-1, true);
            int lf = booleanParenthesization(s, i, k-1, false);
            int rt = booleanParenthesization(s, k+1, j, true);
            int rf = booleanParenthesization(s, k+1, j, false);
            
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
        }
        return ans;   
    }
    
}
