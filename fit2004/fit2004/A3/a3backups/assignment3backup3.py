
import encodings
from operator import itemgetter

def best_revenue(revenue, travel_days,start):
    """
    You are a travelling salesperson that sells your products in n cities (numbered 0, 1, . . . , n − 1)
    and you are trying to decide your schedule for the next d days (numbered 0, 1, . . . , d − 1) in
    order to maximize your revenue. You need to decide when it is better to sell on the city you
    are located in and when it is better to move to another city. 
    approach : dynamic programming 
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

        for city_to_1 in range(len(travel_days)):#num of cities
            # max_city=j
            if day>0 :#should start from start
                for city_from_1 in range(len(travel_days)):#num of cities
                        
    pass





    #                 if day>=travel_days[city_to_1][city_from_1] and travel_days[city_to_1][city_from_1]>0:# have enough day to travel between cites
    #                     #do not travel if no way
    #                     if revenue[day][city_from_1]>=revenue[day][city_to_1]:#more earning , then run,
                            
    #                         if day==travel_days[city_to_1][city_from_1] and revenue[day][city_from_1] >  memo[day][city_from_1]:
    #                             #earn one day, other time run
    #                         #lack of if statement 
    #                             if city_to_1==start:
    #                                 memo[day][city_from_1]=revenue[day][city_from_1]
    #                         elif day>travel_days[city_to_1][city_from_1] and memo[day][city_from_1]<(revenue[day][city_from_1]+memo[day-travel_days[city_to_1][city_from_1]-1][city_to_1]):#earn on
    #                             # memo[day][city_to]=revenue[day][city_to]+memo_max[day-travel_days[city_from][city_to]-1]
    #                             if memo[day-travel_days[city_to_1][city_from_1]][city_to_1]!=-1:
    #                             #in case back to line 0
    #                             #not((day-1)==0 and city_from!=start)
    #                             #trouble here

    #                                 memo[day][city_from_1]=revenue[day][city_from_1]+memo[day-travel_days[city_to_1][city_from_1]][city_to_1]-revenue[day-travel_days[city_to_1][city_from_1]][city_to_1]
    #                 #-1 means cannot be froms
    #                 #elif travel_days[city_from][city_to]<=0 and memo[day][city_to]!=-1:
    #                     if memo[day][city_to_1]==-1 and memo[day][city_from_1]<=0 :
    #                         memo[day][city_from_1]=-1

    #             #needs to be modified 
    #             #stay
    #                 if not((day-1)==0 and city_to_1!=start):
    #                     if (revenue[day][city_to_1]+memo[day-1][city_to_1])>memo[day][city_to_1] and memo[day-1][city_to_1]!=-1:
    #                         memo[day][city_to_1]=revenue[day][city_to_1]+memo[day-1][city_to_1]
    #                 # elif ((day-1)==0 and city_from!=start) and memo[day][city_from]<=0:
    #                 #     memo[day][city_from]=-1
                    

    #             #how to wright else?
                
    #         #is this neccesary?
    #         if day==0 and city_to_1==start:
    #             memo[0][city_to_1]=revenue[day][start]
    #             memo_max[day]=revenue[day][start]
    #         if (day-1)==0 and city_to_1!=start and memo[day][city_to_1]<=0:
    #             memo[day][city_to_1]=-1
            
    #         # else:
    #         #     raise("day<0 error")
    #     memo_max[day]=max(memo[day])#O(d*n^2)    
            
    #     # if memo_max[day]==max(memo[day]):
    #     #     position=memo[day].index(memo_max[day])

    # return memo_max[num_of_days-1]
            


def hero(attacks):
    """
    You are Dr Weird, gifted with the power travel across the multiverse. Your archnemesis Master
    X has duplicated himself and sends copies of himself across the multiverse.
    choose which Multiverse to defend 
    approach : dynamic programming 
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
    #1 for start , 2 for end 
    sort_attack=sorted(attacks, key=itemgetter(2))
    # memo=[[] for i in range(len(attacks))]#n
    memo=[[]]
    num_col=[-1 for i in range(len(attacks))]
    memo[0]=sort_attack[0]
    num_col[0]=sort_attack[0][3]
    # encodings=[sort_attack[i][] in range()]
    for i in range(1,len(attacks)):
        max_col=0
        # memo[i]=sort_attack[i]+memo[i-1]
        j=i-1
        while j>=0:
            if max_col<num_col[i-1]:
                if sort_attack[j][2]<sort_attack[i][1]:
                    memo+=[sort_attack[i]+memo[j]]
                    max_col=sort_attack[i][3]+num_col[j]
                num_col[i]=max_col
            j-=1
            # if max_col>
    return memo[-1]
    pass

attacks =[[1,2,7,5], [2,1,4,4], [3,6,9,2]]
print(hero(attacks))
# // Construct Memo[ ] starting from 1 until C in a way similar to previous slide .
# Initialize Memo[ ] to contain 0 for all indices
# for c = 1 to C,c for endday
# maxValue = 0
# for i=1 to N
# if Weight[ i ] <= c
# thisValue = Value[i] + Memo[c - Weight[ i ] ]
# if thisValue > maxValue
# maxValue = thisValue
# Memo[c] = maxValue


# revenue = [[18, 6, 19, 5], [0, 16, 10, 17], [16, 6, 16, 19], [8, 16, 19, 7], [16, 6, 0, 12], [18, 18, 16, 12], [15, 7, 18, 17], [0, 13, 18, 14], [17, 1, 16, 17]]
# travel_days = [[0, 2, 3, -1], [2, 0, -1, 2], [-1, 3, 0, 3], [3, -1, -1, 0]]
# start = 1
# #my revenue = 27

# res = best_revenue(revenue, travel_days, start)
# print(res)

# revenue=[[1, 2, 3, 4], [3, 6, 1, 5], [1, 8, 4, 1], [1, 10, 4, 5], [10, 4, 5, 9]]
# travel_days=[[-1, -1, 3, 1], [-1, -1, -1, 1], [1, -1, -1, 1], [1, 1, 2, -1]]
# start=0
# res = best_revenue(revenue, travel_days, start)
# print(res)

# revenue = [[6, 2], [9, 9], [18, 13]]
# travel_days = [[0, -1], [1, 0]]
# start = 1
# res = best_revenue(revenue, travel_days, start)
# print(res)

# revenue=[[1, 2, 3, 4], [3, 6, 1, 5], [1, 8, 4, 1], [1, 10, 4, 5], [10, 4, 5, 9]]
# travel_days=[[-1, -1, 3, 1], [-1, -1, -1, 1], [1, -1, -1, 1], [1, 1, 2, -1]]
# start=1
# res = best_revenue(revenue, travel_days, start)
# print(res)


#61
revenue = [[2, 4, 10, 7], [11, 6, 14, 16], [3, 12, 17, 0], [15, 12, 9, 11], [1, 2, 9, 1], [12, 12, 17, 2], [6, 13, 19, 16], [11, 15, 11, 14]]
travel_days = [[0, 3, -1, 2], [-1, 0, 3, -1], [3, 3, 0, 2], [-1, -1, 2, 0]]
start = 0
res = best_revenue(revenue, travel_days, start)
print(res)