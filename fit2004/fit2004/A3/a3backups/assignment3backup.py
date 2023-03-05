


import re
from turtle import position


def best_revenue(revenue, travel_days,start):
    """
    You are a travelling salesperson that sells your products in n cities (numbered 0, 1, . . . , n − 1)
    and you are trying to decide your schedule for the next d days (numbered 0, 1, . . . , d − 1) in
    order to maximize your revenue. You need to decide when it is better to sell on the city you
    are located in and when it is better to move to another city. 
    High level description about the functiona and the approach you
    have undertaken.
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
    :Aux space complexity:
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
    memo=[]
    #memo[days][city]
    #O(d*n)
    for j in range(num_of_days):
        memo.append([0 for i in range(num_of_cities)])
    memo_max=[-1 for i in range(num_of_days)]
    # if memo[num_of_days-1]!=-1:
    #     return memo[num_of_days-1]

    for day in range(len(revenue)):#num of days，d

        for city_from in range(len(travel_days)):#num of cities
            # max_city=j
            if day>0 and not((day-1)==0 and city_from!=start):
                for city_to in range(len(travel_days)):#num of cities
                    if day>=travel_days[city_from][city_to] and travel_days[city_from][city_to]>0:# have enough day to travel between cites
                        #do not travel if no way
                        if revenue[day][city_to]>revenue[day][city_from]:#more earning , then run,
                            #cand[0]
                            if day==travel_days[city_from][city_to] and revenue[day][city_to] >  memo[day][city_to]:#earn one day, other time run
                            #lack of if statement 
                            
                                memo[day][city_to]=revenue[day][city_to]
                            elif day>travel_days[city_from][city_to] and memo[day][city_to]<revenue[day][city_to]+memo[day-travel_days[city_from][city_to]-1][city_from]:#earn on
                                # memo[day][city_to]=revenue[day][city_to]+memo_max[day-travel_days[city_from][city_to]-1]
                                # if memo[day-travel_days[city_from][city_to]-1][city_from]>0:
                                memo[day][city_to]=revenue[day][city_to]+memo[day-travel_days[city_from][city_to]-1][city_from]

                       

                #needs to be modified 
        
                if (revenue[day][city_from]+memo[day-1][city_from])>memo[day][city_from] :
                    memo[day][city_from]=revenue[day][city_from]+memo[day-1][city_from]
                    

                #how to wright else?
                
            #is this neccesary?
            elif day==0 and city_from==start:#cand[-1]
                memo[0][city_from]=revenue[day][start]
                memo_max[day]=revenue[day][start]
            # else:
            #     raise("day<0 error")
        memo_max[day]=max(memo[day])#O(d*n^2)    
            
        # if memo_max[day]==max(memo[day]):
        #     position=memo[day].index(memo_max[day])

    return memo_max[num_of_days-1]
            


def hero(attacks):
    pass
revenue = [[18, 6, 19, 5], #stay1  
            [0, 16, 10, 17], #stay1 *2
            [16, 6, 16, 19],#stay1
            [8, 16, 19, 7], 
            [16, 6, 0, 12], 
            [18, 18, 16, 12], 
            [15, 7, 18, 17], 
            [0, 13, 18, 14], 
            [17, 1, 16, 17]]
travel_days = [[-1, 2, 3, -1], 
                [2, -1, -1, 2], 
                [-1, 3, -1, 3], 
                [3, -1, -1, -1]]
start = 1
#my revenue = 98 

res = best_revenue(revenue, travel_days, start)
print(res)
# def bf(revenue, travel_days, start):
#     res=[]
#     num_of_cities=len(travel_days)
#     for a in range(num_of_cities):
#         for b in range(num_of_cities):
#             for c in range(num_of_cities):
#                 for d in range(num_of_cities):
#                     for e in range(num_of_cities):
#                         for f in range(num_of_cities):
#                             for g in range(num_of_cities):
#                                 for h in range(num_of_cities):
#                                     for i in range(num_of_cities):
#                                         if (revenue[0][a]+revenue[1][b]+revenue[2][c]+revenue[3][d]+revenue[4][e]+revenue[5][f]+revenue[6][g]+revenue[7][h]+revenue[8][i])==98:
#                                             m=[a,b,c,d,e,f,g,h,i]
#                                             res.append(m)
#                                             for i in range(1,len(m)):
#                                                 if travel_days[m[i-1]][m[i]]==-1:
#                                                     res.pop()
#                                                     break
#     print(res)

# bf(revenue, travel_days, start)

