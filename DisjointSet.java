import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int n){
        for(int i=0; i<=n; i++)
        {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findParent(int node)
    {
        if(node == parent.get(node))
        {
            return node;
        }
        int ulp = findParent(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionByRank(int u, int v)
    {
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);
        if(ulp_v==ulp_u) return;
        if(Objects.equals(rank.get(ulp_u), rank.get(ulp_v)))
        {
            parent.set(ulp_u, ulp_v);
        }
        else if(rank.get(ulp_u) < rank.get(ulp_v))
        {
            parent.set(ulp_v, ulp_u);
        }
        else {
            parent.set(ulp_v, ulp_u);
            int rankU = rank.get(ulp_u);
            rank.set(ulp_u, rankU+1);
        }
    }

    public void unionBySize(int u, int v)
    {
        int ultimateParentU = findParent(u);
        int ultimateParentV = findParent(v);
        if(ultimateParentU==ultimateParentV) return;
        if(size.get(ultimateParentU) == size.get(ultimateParentV))
        {
            parent.set(ultimateParentU, ultimateParentV);
            size.set(ultimateParentV, size.get(ultimateParentU) + size.get(ultimateParentV));
        }
        else {
            parent.set(ultimateParentV, ultimateParentU);
            size.set(ultimateParentU, size.get(ultimateParentU) + size.get(ultimateParentV));
        }
    }

    public String checkDisJoint(int node1, int node2) {
        return findParent(node1) == findParent(node2) ? "Same" : "Not Same";
    }

    public static void main(String[] args) {
        DisjointSet dj = new DisjointSet(7);
        dj.unionBySize(1,2);
        dj.unionBySize(2,3);
        dj.unionBySize(4,5);
        dj.unionBySize(6,7);
        dj.unionBySize(5,6);
        System.out.println( dj.checkDisJoint(3, 7));
        dj.unionBySize(3,7);
        System.out.println( dj.checkDisJoint(3, 7));
    }

}