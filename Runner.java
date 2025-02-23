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
        ArrayList<ArrayList<iPair>> adj = new ArrayList<>();
        ArrayList<iPair> a= new ArrayList<>();
        a.add(new iPair(1,9));
        ArrayList<iPair> b= new ArrayList<>();
        b.add(new iPair(0,9));
        adj.add(a);
        adj.add(b);
        dijkstra(adj, 0);
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
