
from operator import itemgetter
import re

def best_revenue(revenue, travel_days,start):
    """
    You are a travelling salesperson that sells your products in n cities (numbered 0, 1, . . . , n − 1)
    and you are trying to decide your schedule for the next d days (numbered 0, 1, . . . , d − 1) in
    order to maximize your revenue. You need to decide when it is better to sell on the city you
    are located in and when it is better to move to another city. 
    approach : dynamic programming ,record the best solution of every day in memo and use them to caluate revenue in further days  
    :Input:
        revenue:revenue is a list of lists.All interior lists are length n. 
        Each interior list represents a different day.
        Revenue[z][x] is the revenue that you would make if you work in city x on day z.
        
        travel_days: travel_days is a list of lists. travel_days[x][y] will contain either:
        1.A positive integer number indicating the number of days you need to spend to travel on
        the direct road from city x to city y. 
        2.  -1, to indicate that there is no direct road for you to travel from city x to city y.

        start:start denote the city you start in on day 0.    
    :Output, return or postcondition:the maximum possible revenue called max_rev
    :Time complexity:O (n^2 (d + n))
    :Aux space complexity:O(n^d)
    """
    """
    Notice:
    1.day starts from 0
    2.
    """

    #initailize memo,do dp
    # posotion=start
    # max_rev=0
    num_of_days=len(revenue)
    num_of_cities=len(travel_days)
    memo=[[-1 for i in range(num_of_cities)]]
    #memo[days][city]
    #O(d*n)
    for j in range(1,num_of_days):
        memo.append([0 for i in range(num_of_cities)])
    memo_max=[-1 for i in range(num_of_days)]
    # if memo[num_of_days-1]!=-1:
    #     return memo[num_of_days-1]

    for day in range(len(revenue)):#num of days，d

        for city_from in range(len(travel_days)):#num of cities
            # max_city=j
            if day>0 :#should start from start
                for city_to in range(len(travel_days)):#num of cities
                    if day>=travel_days[city_from][city_to] and travel_days[city_from][city_to]>0:# have enough day to travel between cites
                        #do not travel if no way
                        # if revenue[day][city_to]>=revenue[day][city_from]:#more earning , then run,
                            
                        if day==travel_days[city_from][city_to] and revenue[day][city_to] >  memo[day][city_to]:
                            #earn one day, other time run
                        #lack of if statement 
                            if city_from==start:
                                memo[day][city_to]=revenue[day][city_to]
                        elif day>travel_days[city_from][city_to] and memo[day][city_to]<(revenue[day][city_to]+memo[day-travel_days[city_from][city_to]][city_from]-revenue[day-travel_days[city_from][city_to]][city_from]):#earn on
                            # memo[day][city_to]=revenue[day][city_to]+memo_max[day-travel_days[city_from][city_to]-1]
                            # if not(day-travel_days[city_from][city_to]-1==0 and city_from!=start): 
                            if memo[day-travel_days[city_from][city_to]][city_from]!=-1:
                                memo[day][city_to]=revenue[day][city_to]+memo[day-travel_days[city_from][city_to]][city_from]-revenue[day-travel_days[city_from][city_to]][city_from]
                    #-1 means cannot be froms
                        #elif travel_days[city_from][city_to]<=0 and memo[day][city_to]!=-1:
                    
                    if (memo[day][city_from]==-1 or travel_days[city_from][city_to]>day or travel_days[city_from][city_to]<=0) and memo[day][city_to]<=0 and revenue[day][city_to]!=0:
                        memo[day][city_to]=-1
                    #memo[day][city_from]==-1  and 
                    elif revenue[day][city_to]==0 and memo[day-travel_days[city_from][city_to]][city_from]==-1 :
                        memo[day][city_to]=-1
                    else:
                        memo[day][city_to]=memo[day-travel_days[city_from][city_to]-1][city_from]

                #needs to be modified 
                #stay
                #mod
                ##############################################################
                    if not((day-1)==0 and city_from!=start and memo[day-1][city_from]==-1):
                        if (revenue[day][city_from]+memo[day-1][city_from])>=memo[day][city_from] and memo[day-1][city_from]!=-1:
                            memo[day][city_from]=revenue[day][city_from]+memo[day-1][city_from]
                    # elif ((day-1)==0 and city_from!=start) and memo[day][city_from]<=0:
                    #     memo[day][city_from]=-1
                    

                #how to wright else?
                
            #is this neccesary?
            if day==0 and city_from==start:
                memo[0][city_from]=revenue[day][start]
                memo_max[day]=revenue[day][start]
            if (day-1)==0 and city_from!=start and memo[day][city_from]<=0:
                memo[day][city_from]=-1
            
            # else:
            #     raise("day<0 error")
        memo_max[day]=max(memo[day])#O(d*n^2)    
            
        # if memo_max[day]==max(memo[day]):
        #     position=memo[day].index(memo_max[day])
    res=memo_max[num_of_days-1]
    if res<0:
        res=0
    return res
            


def hero(attacks):
    """
    You are Dr Weird, gifted with the power travel across the multiverse. Your archnemesis Master
    X has duplicated himself and sends copies of himself across the multiverse.
    choose which Multiverse to defend 
    approach : dynamic programming. sort the attacks by ending date and find the best solution for every ending date. Then record that to memo.Finally, return the element in memo which defend most colones
    :Input:
        attacks:a list contains attack info   
        attacks is a non-empty list of N attacks, where each attack is a list of 4 items [m, s, e, c]: • m is the multiverse which Master X is attacking.
        – m is an integer in the range of 1 to N. – Master X will only attack each multiverse once; because he do not like setbacks.
        • s and e are the starting and ending days of the attack.
        – s and e are integers in the range of 1 to D. – You can assume that s <= e. – You would need to be throughout the entire attack duration from day s to day e
        inclusive in order to defeat the clones.
        • c is the number of Master X clones in the attack.
        – c is an integer in the range of 2 to C. – Master X will always attack with at least 2 clones because he needs friends.
            :Output, return or postcondition: returned list with most Master X clones defeated.
    :worst case Time complexity:O(NlogN),N is the number of attacks in attacks
    :Aux space complexity:O(N),N is the number of attacks in attacks
    """
    sort_end_attack=sorted(attacks, key=itemgetter(2))
    sort_col_attack=sorted(attacks, key=itemgetter(3))
    cols=[sort_end_attack[i][3] for i in range(len(attacks))]
    # memo=[[] for i in range(len(attacks))]#n
    memo=[[] for _ in range(len(attacks))]
    num_col=[-1 for i in range(len(attacks))]
    #find all the endings and remove duplicated elements
    endings=[sort_end_attack[i][2] for i in range(len(attacks))]#O(n)
    duration=[sort_end_attack[i][2]-sort_end_attack[i][1] for i in range(len(attacks))]#O(n)
    # endings=remove_dup(endings)#O(n)
    min_end=sort_end_attack[0][2]
    memo[0]=sort_end_attack[0]
    num_col[0]=sort_end_attack[0][3]
    max_col=0
    for i in range(len(sort_end_attack)):
        floor_ceil=find_floor_ceil(endings,sort_end_attack[i][1])
        last_one=floor_ceil[0]
        #have at list one last one 
            #if can append
        
        if last_one != None:
            if (num_col[last_one]+sort_end_attack[i][3])>max_col:
                memo[i]=[sort_end_attack[i]]+memo[last_one]
                num_col[i]=num_col[last_one]+sort_end_attack[i][3]
                if max_col<num_col[i]:
                    max_col=num_col[i]


                
        #not need?
        else:
            if (sort_end_attack[i][3])>max_col:
                memo[i]=[sort_end_attack[i]]
                num_col[i]=sort_end_attack[i][3]
                if max_col<num_col[i]:
                    max_col=num_col[i]

        if num_col[i]<num_col[i-1]:
            num_col[i]=num_col[i-1]
            memo[i]=memo[i-1]

            



    res_ind=num_col.index(max_col)
    return memo[res_ind]
    # for i in endings:
    #     if endings[i]>
        


def find_floor_ceil(attack_find_floor, bound):
    start_pivot, mid, end_upper, max_idx = 0, 0, len(attack_find_floor)-1, len(attack_find_floor)-1 
    while start_pivot <= end_upper:
        mid = start_pivot + (end_upper - start_pivot)// 2
        # if arr[mid] == target:
        #     return mid, mid
        if attack_find_floor[mid] < bound:
            start_pivot = mid + 1
        else:
            end_upper = mid - 1
    if start_pivot > max_idx:
        return end_upper,  None
    elif end_upper < 0:
        return None, start_pivot
    else:
        return end_upper, start_pivot




# #61
# revenue = [[2, 4, 10, 7], [11, 6, 14, 16], [3, 12, 17, 0], [15, 12, 9, 11], [1, 2, 9, 1], [12, 12, 17, 2], [6, 13, 19, 16], [11, 15, 11, 14]]
# travel_days = [[0, 3, -1, 2], [-1, 0, 3, -1], [3, 3, 0, 2], [-1, -1, 2, 0]]
# start = 0
# res = best_revenue(revenue, travel_days, start)
# print(res)

# #69
# revenue = [[19, 4], [0, 16], [2, 12], [13, 17], [3, 17], [4, 15], [0, 13], [14, 8], [14, 16]]
# travel_days = [[0, -1], [-1, 0]]
# start = 0
# res = best_revenue(revenue, travel_days, start)
# print(res)

# #81
# revenue = [[15, 16], [4, 11], [10, 14], [2, 0], [19, 15], [1, 1], [2, 7], [14, 6], [5, 8], [19, 0]]
# travel_days = [[0, -1], [3, 0]] 
# start = 1

# res = best_revenue(revenue, travel_days, start)
# print(res)

# # #59
# revenue = [[6, 6, 7, 18], [16, 10, 14, 8], [16, 10, 5, 8], [17, 19, 1, 3], [17, 0, 10, 2], [7, 4, 19, 4]]
# travel_days = [[0, 3, -1, 1], [1, 0, 3, 3], [1, -1, 0, 3], [-1, 1, 2, 0]]
# start = 3
# res = best_revenue(revenue, travel_days, start)
# print(res)

# #22
# revenue=[[1, 2, 3, 4], [3, 6, 1, 5], [1, 8, 4, 1], [1, 10, 4, 5], [10, 4, 5, 9]]
# travel_days=[[-1, -1, 3, 1], [-1, -1, -1, 1], [1, -1, -1, 1], [1, 1, 2, -1]]
# start=2
# res = best_revenue(revenue, travel_days, start)
# print(res)

# revenue = [[5, 0, 8, 11], [6, 0, 19, 3]]
# travel_days = [[0, 3, 3, 2], [3, 0, 2, -1], [2, 1, 0, 2], [2, -1, -1, 0]]
# start = 1
# res = best_revenue(revenue, travel_days, start)
# print(res)

# #64
# revenue = [[0, 1], [0, 12], [11, 6], [12, 10], [1, 5], [13, 1], [11, 10], [16, 15]]
# travel_days = [[0, -1], [3, 0]]
# start = 0
# res = best_revenue(revenue, travel_days, start)
# print(res)


# # # you_revenue = 96 , my revenue = 77 . 
# revenue = [[6, 13, 16, 16], [12, 0, 1, 17], [3, 12, 0, 3], [1, 3, 15, 3], [8, 16, 19, 1], [2, 14, 6, 3], [11, 0, 16, 3], [3, 8, 9, 18], [18, 14, 16, 6], [2, 4, 15, 6]]
# travel_days = [[0, 2, 3, 3], [1, 0, -1, 1], [-1, 1, 0, 1], [-1, 3, -1, 0]]
# start = 3

# res = best_revenue(revenue, travel_days, start)
# print(res)

# #  you_revenue = 51 , my revenue = 64 . 
# revenue = [[2, 17, 19], [3, 19, 4], [3, 8, 0], [3, 6, 1], [1, 15, 18], [10, 15, 1], [7, 13, 3], [1, 1, 4], [6, 12, 1], [5, 8, 18]]
# travel_days = [[0, -1, 2], [-1, 0, -1], [1, 2, 0]]
# start = 0

# res = best_revenue(revenue, travel_days, start)
# print(res)

 #you_revenue = 60 , my revenue = 53 . 
revenue = [[15, 14, 7, 13], [7, 15, 12, 17], [14, 12, 8, 14], [12, 0, 6, 8], [0, 11, 7, 10], [18, 16, 7, 2], [2, 0, 4, 9]]
travel_days = [[0, 3, -1, 1], [2, 0, 2, 2], [1, -1, 0, 2], [2, 2, -1, 0]]
start = 2
res = best_revenue(revenue, travel_days, start)
print(res)
