import java.util.*;
class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
public class Runner {
    public static void main(String[] args) {
        System.out.println(countWays("123"));
    }

    public static int countWays(String digits) {
        // s code here
        List<Integer> dig = new ArrayList<>();
        for(char c: digits.toCharArray())
        {
            if(c == '0')
                return 0;

            dig.add(c-'0');
        }

        return ways(dig, 0, false);
    }

    static int ways(List<Integer> dig, int idx, boolean isIgnored)
    {
        // ignore means we try for 2 digit element,
        if(!isIgnored)
        {
            if(idx==dig.size())
                return 1;
            int take = ways(dig, idx+1, false);
            int ignore = 0;
            if((dig.get(idx)<3) && (idx!=dig.size()-1))
            // cant accept double digit ele when first digit is 3 or more.
            // 0 will never be the case here.
            // we cant ignore last ele
            {
                ignore = ways(dig, idx+1, true);
            }
            return take+ignore;
        }
        else
        {
            if(idx==dig.size()-1)
            {
                if(((dig.get(idx-1)*10)+dig.get(idx)) < 27)
                {
                    return 1;
                }
                return 0;
            }

            // we cant ignore current element.
            if(((dig.get(idx-1)*10)+dig.get(idx)) < 27)
            {
                return ways(dig, idx+1, false);
            }
            return 0;
        }
    }
}
