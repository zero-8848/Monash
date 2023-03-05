from audioop import reverse
from imp import find_module
from importlib.resources import path
from operator import index, length_hint
from typing import List
import random
from queue import PriorityQueue


def ideal_place(relevant):
    """
    1.High level description about the function:
     compute the coordinates (x, y) of an optimal intersection to open
your kiosk such that P ni=1(|x − xi| + |y − yi|) is minimal.

    find median of the list, using recursion and dynamic programming 
    :Input: relevant =
[[x1, y1], [x2, y2], [x3, y3], . . . , [xn, yn]] contains the coordinates of the n relevant points.
    argv1:relevant, a list which contains the coordinates of the n relevant points.
    :Output, return or postcondition:the point(List) p that sum of  ni=1(|x − xi| + |y − yi|) is minimal
    :Time complexity: For a list of n relevant points,this code have a worst-case time complexity of O(n) .
    :Aux space complexity:O(n)
    """

    lstx = []
    lsty = []
    for i in range(len(relevant)):
        lstx.append(relevant[i][0])
    for i in range(len(relevant)):
        lsty.append(relevant[i][1])
    return [fmid(lstx, len(lstx)), fmid(lsty, len(lsty))]


def Partition(inputArray, partl, partr):

    lst = inputArray[partr]
    i = partl
    j = partl
    while (j < partr):
        if (inputArray[j] < lst):
            inputArray[i], inputArray[j] = inputArray[j], inputArray[i]
            i += 1

        j += 1

    inputArray[i], inputArray[partr] = inputArray[partr], inputArray[i]
    return i

# Picks a random pivot element between
# l and r and partitions arr[l..r]
# around the randomly picked element
# using partition()


def originParti(radlist, left, right):
    # why ???
    n = right - left + 1
    handle = random.randrange(1, 100) % n
    radlist[left + handle], radlist[right] = radlist[right], radlist[left + handle]
    return Partition(radlist, left, right)

# Utility function to find median
# k is the median index.
# l for left , r for right


def med(arr, left, right,
               k, a1, b1):

    global a, b

    # if l < r
    if (left <= right):

        # Find the partition index
        partitionIndex = originParti(arr, left, right)

        if (partitionIndex == k):
            b = arr[partitionIndex]
            if (a1 != -1):
                return

        elif (partitionIndex == k - 1):
            a = arr[partitionIndex]
            if (b1 != -1):
                return

        if (partitionIndex >= k):
            return med(arr, left, partitionIndex - 1, k, a, b)

        else:
            return med(arr, partitionIndex + 1, right, k, a, b)

    return


def fmid(arr, n):
    global a
    global b
    a = -1
    b = -1

    if (n % 2 == 1):
        med(arr, 0, n - 1, n // 2, a, b)
        ans = b

    else:
        med(arr, 0, n - 1, n // 2, a, b)
        ans = (a + b) // 2

    return ans


class RoadGraph:
    def __init__(self, roads):
        """
    1.High level description about the function:
    use the input roads to generate a adjency matrix of the roads graph
    2. the approach you
    have undertaken.
    :Input:roads , a list showing the road start, end and weight 
    argv1:relevant, a list which contains the coordinates of the n relevant points.
    :Output, return or postcondition:the list show the path to get mii approach to chore and end .
    :Time complexity: 
    O(|V | + |E|) time and space where:
    •V is the set of unique locations in roads. You can assume that all locations are connected
    by roads (i.e a connected graph); and the location IDs are continuous from 0 to |V | − 1. 
    E is the set roads.
    :Aux space complexity:
        """
        # adj_matrix
        find_max_v = []
        road_matrix = []
        for single_road in roads:  # O(e)
            find_max_v.append(single_road[0])
            find_max_v.append(single_road[1])
        v_num = max(find_max_v)+1  # O(v)
        row = [-1 for j in range(v_num)]  # o(v)
        # for i in range(v_num):#O(v)
        #     road_matrix.append(row)
        # for single_road in roads:#O(e)
        #     start=single_road[0]
        #     end=single_road[1]
        #     dist=single_road[2]
        #     road_matrix[start][end]=dist

        # self.adj_matrix=road_matrix
        self.edge_num = len(roads)

        adj_list = [[] for _ in range(v_num)]  # O(v)
        for j in roads:  # O(e)
            adj_list[j[0]].append(j)

        self.adj_list = adj_list
        self.v = v_num
        # self.inf_ver = [float('inf') for v in range(self.v)]

        # reverse
        adj_list_rev = [[] for _ in range(v_num)]
        self.empty_lists = adj_list_rev
        for j in roads:  # O(e)
            adj_list_rev[j[1]].append((j[1], j[0], j[2]))
        self.reverse_adj_list = adj_list_rev

        # road_matrix_rev=[]
        # for i in range(v_num):#O(v)
        #     road_matrix_rev.append(row)
        # for single_road in roads:#O(e)
        #     start=single_road[1]
        #     end=single_road[0]
        #     dist=single_road[2]
        #     road_matrix_rev[start][end]=dist
        # self.adj_matrix_rev=road_matrix_rev

        # adj_list
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
        final_path_start = []
        final_path_end = []
        final_path_last = []
        dist_start, path_start = self.dijkstra(start, self.adj_list)
        dist_end, path_end = self.dijkstra(end, self.reverse_adj_list)
        find_min_dist = [-1 for v in range(self.v)]
        min_num = []
        for i in chores_location:  # O(v) worst
            find_min_dist[i] = dist_start[i]+dist_end[i]
            min_num.append(find_min_dist[i])

        num_min = min(min_num)
        chore_min = find_min_dist.index(num_min)  # O(v)
        # chore_min_index=index(chore_min)
        chore1 = chore_min
        chore2 = chore_min
        while path_start[chore1] != None:
            final_path_start.append(path_start[chore1])
            chore1 = path_start[chore1]
        while path_end[chore2] != None:
            final_path_end.append(path_end[chore2])
            chore2 = path_end[chore2]
        final_path_start.reverse()

        final_path_last = final_path_start+[chore_min]+final_path_end
        if (final_path_start == [] and chore_min != start) or (final_path_end == [] and chore_min != end):
            return None
        else:
            return final_path_last

    # Using dijistra to find the min path
    def dijkstra(self, start_vertex, lst):

        D = [float('inf') for v in range(self.v)]
        D[start_vertex] = 0
        visited = []
        path = [None for _ in range(self.v)]

        pq = PriorityQueue()
        # O(?)
        pq.put((0, start_vertex))

        while not pq.empty():
            (dist, current_vertex) = pq.get()

            visited.append(current_vertex)

            for i in lst[current_vertex]:  # O(e)
                # same start for elements in adj_list[current_vertex]
                neighbor = i[1]

                distance = i[2]
                if neighbor not in visited:
                    old_cost = D[neighbor]
                    new_cost = D[current_vertex] + distance
                    if new_cost < old_cost:
                        pq.put((new_cost, neighbor))
                        D[neighbor] = new_cost
                        path[neighbor] = current_vertex

        return D, path
# ToDo: Performs the operation needed to find the optimal route.

# #arrayList for priority queue
# roads = [(0,1,4), (1,2,2), (2,3,3), (3,4,1), (1,5,2), (5,6,5), (6,3,2), (6,4,3), (1,7,4), (7,8,2), (8,7,2), (7,3,2), (8,0,11), (4,3,1), (4,8,10)]
# start = 0
# end = 4
# chores_location = [0,4,5,6,8]
# myGraph = RoadGraph(roads)
# result = myGraph.routing(start, end, chores_location)
# print(result)
