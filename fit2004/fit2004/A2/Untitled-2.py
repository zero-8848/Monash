from imp import find_module
from operator import length_hint
from typing import List
import random
from queue import PriorityQueue

def ideal_place(relevant):
    """
    1.High level description about the function:
    given a list 
    
    2. the approach you
    have undertaken.
    :Input:
    argv1:relevant, a list which contains the coordinates of the n relevant points.
    :Output, return or postcondition:
    :Time complexity: For a list of n relevant points,this code have a worst-case time complexity of O(n) .
    :Aux space complexity:
    """
    a=None
    b=None
    lstx=[]
    lsty=[]
    for i in range(len(relevant)):
        lstx.append(relevant[i][0])
    for i in range(len(relevant)):
        lsty.append(relevant[i][1])
    return [findMedian(lstx,len(lstx)),findMedian(lsty,len(lsty))]



 
# Returns the correct position of
# pivot element
def Partition(arr, l, r) :
 
    lst = arr[r]; i = l; j = l;
    while (j < r) :
        if (arr[j] < lst) :
            arr[i], arr[j] = arr[j],arr[i];
            i += 1;
         
        j += 1;
 
    arr[i], arr[r] = arr[r],arr[i];
    return i;
 
# Picks a random pivot element between
# l and r and partitions arr[l..r]
# around the randomly picked element
# using partition()
def randomPartition(arr, l, r) :
    #why ???
    n = r - l + 1;
    pivot = random.randrange(1, 100) % n;
    arr[l + pivot], arr[r] = arr[r], arr[l + pivot];
    return Partition(arr, l, r);
 
# Utility function to find median
#k is the median index.
#l for left , r for right
def MedianUtil(arr, l, r,
                k, a1, b1) :
 
    global a, b;
     
    # if l < r
    if (l <= r) :
         
        # Find the partition index
        partitionIndex = randomPartition(arr, l, r);
         
        # If partition index = k, then
        # we found the median of odd
        # number element in arr[]
        if (partitionIndex == k) :
            b = arr[partitionIndex];
            if (a1 != -1) :
                return;
                 
        # If index = k - 1, then we get
        # a & b as middle element of
        # arr[]
        elif (partitionIndex == k - 1) :
            a = arr[partitionIndex];
            if (b1 != -1) :
                return;
                 
        # If partitionIndex >= k then
        # find the index in first half
        # of the arr[]
        if (partitionIndex >= k) :
            return MedianUtil(arr, l, partitionIndex - 1, k, a, b);
             
        # If partitionIndex <= k then
        # find the index in second half
        # of the arr[]
        else :
            return MedianUtil(arr, partitionIndex + 1, r, k, a, b);
             
    return;
 
# Function to find Median
#n for length of the array
def findMedian(arr, n) :
    global a;
    global b;
    a = -1;
    b = -1;
     
    # If n is odd
    if (n % 2 == 1) :
        MedianUtil(arr, 0, n - 1, n // 2, a, b);
        ans = b;
         
    # If n is even
    
    else :
        MedianUtil(arr, 0, n - 1, n // 2, a, b);
        ans = (a + b) // 2;
         
    # Print the Median of arr[]
    return ans

class RoadGraph:
    def __init__(self, roads):
        """
    1.High level description about the function:
    use the input roads to generate a adjency matrix of the roads graph
    2. the approach you
    have undertaken.
    :Input:
    argv1:relevant, a list which contains the coordinates of the n relevant points.
    :Output, return or postcondition:
    :Time complexity: 
    O(|V | + |E|) time and space where:
    •V is the set of unique locations in roads. You can assume that all locations are connected
    by roads (i.e a connected graph); and the location IDs are continuous from 0 to |V | − 1. 
    E is the set roads.
    :Aux space complexity:
        """
        #adj_matrix
        find_max_v=[]
        road_matrix=[]
        for single_road in roads:#O(e)
            find_max_v.append(single_road[0])
            find_max_v.append(single_road[1])
        v_num=max(find_max_v)+1#O(v)
        row=[-1 for j in range(v_num)]#o(v)
        for i in range(v_num):#O(v)
            road_matrix.append(row)
        for single_road in roads:#O(e)
            start=single_road[0]
            end=single_road[1]
            dist=single_road[2]
            road_matrix[start][end]=dist


        self.adj_matrix=road_matrix
        self.edge_num=len(roads)

        adj_list=[[] for _ in range(len(v_num))]#O(v)
        for j in roads:#O(e)
            adj_list[j[0]].append(j)

        self.adj_list=adj_list
        self.v = v_num
        self.inf_ver = [float('inf') for v in range(self.v)]

        #reverse
        adj_list_rev=[[] for _ in range(len(v_num))]
        for j in roads:#O(e)
            adj_list_rev[j[1]].append((j[1],j[0]))
        self.reverse_adj_list=adj_list_rev

        road_matrix_rev=[]
        for i in range(v_num):#O(v)
            road_matrix_rev.append(row)
        for single_road in roads:#O(e)
            start=single_road[1]
            end=single_road[0]
            dist=single_road[2]
            road_matrix_rev[start][end]=dist
        self.adj_matrix_rev=road_matrix_rev
        #adj_list
        # self.edges = [[-1 for i in range(num_of_vertices)] for j in range(num_of_vertices)]
        # self.visited = []
        pass
# ToDo: Initialize the graph data structure here
    def routing(self, start, end, chores_location):
        """
        1.High level description about the function:
        use the input roads to generate a adjency matrix of the roads graph
        2. the approach you
        have undertaken.
        :Input:
        argv1:relevant, a list which contains the coordinates of the n relevant points.
        :Output, return or postcondition:
        :Time complexity: 
        O(|E|log|V |) time and space where:
        •V is the set of unique locations in roads. You can assume that all locations are connected
        by roads (i.e a connected graph); and the location IDs are continuous from 0 to |V | − 1. 
        E is the set roads.
        :Aux space complexity:O(|V | + |E|). The role of v,e is explained above.
        """
        dist_start=self.dijkstra(start,self.adj_matrix,self.adj_list)
        dist_end=self.dijkstra(end,self.adj_matrix_rev,self.adj_matrix_rev)
        find_min_dist=self.inf_ver
        for i in chores_location:#O(v) worst
            find_min_dist[i]=dist_start[i]+dist_end[i]
        return min(find_min_dist)#O(v)
        pass
    #Using dijistra to find the min path 
    def dijkstra(self, start_vertex,inf_ver,matrix,lst):

        D = inf_ver
        D[start_vertex] = 0
        visited=[]
    
        pq = PriorityQueue()
        #O(?)
        pq.put((0, start_vertex))
    
        while not pq.empty():
            (dist, current_vertex) = pq.get()
            visited.append(current_vertex)
    
            for i in lst[current_vertex]:#O(e)
                #same start for elements in adj_list[current_vertex]
                neighbor=i[1]
                
                if matrix[current_vertex][neighbor] != -1:
                    distance = matrix[current_vertex][neighbor]
                    if neighbor not in visited:
                        old_cost = D[neighbor]
                        new_cost = D[current_vertex] + distance
                        if new_cost < old_cost:
                            pq.put((new_cost, neighbor))
                            D[neighbor] = new_cost
        return D
# ToDo: Performs the operation needed to find the optimal route.

#arrayList for priority queue
