#ifndef DISJOINTSET_H
#define DISJOINTSET_H

#include<bits/stdc++.h>

using namespace std;

class DisjointSet{
    public:

    DisjointSet(int n){
        parent.resize(n);
        size.resize(n);
        for(int i=0; i<n; i++)
        {
            parent[i]=i;
            size[i]=1;
        }
    }
    int findParent(int n){
        if(n == parent[n])
        {
            return n;
        }
        // relax
        int ulp = findParent(parent[n]);
        parent[n] = ulp;
        return parent[n];
    }
    void unionBySize(int u, int v)
    {
        int ultimateParentU = findParent(u);
        int ultimateParentV = findParent(v);
        if(ultimateParentU==ultimateParentV) return;
        if(size[ultimateParentU] == size[ultimateParentV])
        {
            parent[ultimateParentU] = ultimateParentV;
            size[ultimateParentV] = size[ultimateParentU] + size[ultimateParentV];
        }
        else {
            parent[ultimateParentV] = ultimateParentU;
            size[ultimateParentU] = size[ultimateParentU] + size[ultimateParentV];
        }
    }

    private:

    vector<int> parent;
    vector<int> size;
};

#endif