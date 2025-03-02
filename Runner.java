import java.util.*;
import java.io.*;
class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
public class Runner {
    public static void main(String[] args) {
        int arr[] = {60, 20, 50, 40, 10, 50, 60};
        System.out.println(getMaxArea(arr));
    }


    public static int getMaxArea(int arr[]) {
        int n = arr.length;

        // Left span: Count of consecutive bars on left (including self)
        ArrayList<Integer> left = new ArrayList<>(Collections.nCopies(n, 0));
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
                stk.pop();
            }
            if (stk.isEmpty()) {
                left.set(i, i + 1);
            } else {
                left.set(i, i - stk.peek());
            }
            stk.push(i);
        }
        System.out.println(left);

        // Clear the stack for re-use
        stk.clear();

        // Right span: Count of consecutive bars on right (including self)
        ArrayList<Integer> right = new ArrayList<>(Collections.nCopies(n, 0));

        for (int i = n - 1; i >= 0; i--) {
            while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
                stk.pop();
            }
            if (stk.isEmpty()) {
                right.set(i, n - i);
            } else {
                right.set(i, stk.peek() - i);
            }
            stk.push(i);
        }
        System.out.println(right);

        // Calculate maximum area
        int mArea = 0;
        for (int i = 0; i < n; i++) {
            int width = left.get(i) + right.get(i) - 1; // Total width for bar i
            mArea = Math.max(mArea, arr[i] * width);

            System.out.println("calc:: i:" + i + " width: " + width + " area: " + mArea);
        }

        return mArea;
    }

    static ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        // Write your code here
        PriorityQueue<iPair> pq = new PriorityQueue<iPair>((x, y) ->Integer.compare(x.first, y.first));
        List<Integer> dist = new ArrayList<>();
        int mx = Integer.MAX_VALUE;
        for(int i=0; i<adj.size(); i++)
        {
            dist.add(mx);
        }
        dist.set(src, 0);
        pq.add(new iPair(src,0));
        while(!pq.isEmpty())
        {
            iPair pair = pq.poll();
            int weight = pair.second;
            int node = pair.first;
            for(iPair p:adj.get(node))
            {
                int wt = p.second;
                int adjNode = p.first;
                if(weight + wt < dist.get(adjNode))
                {
                    dist.set(adjNode, weight + wt);
                    pq.add(new iPair( adjNode, dist.get(adjNode)));
                }
            }
        }
        return new ArrayList<>(dist);
    }
    
}
