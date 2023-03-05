
from operator import index, le



def trainer(wordlist, word, marker):
    '''
    usage: quickly identify the possible word matches from the a given word list.
    arguments:wordlist, word, marker
     return values: templist ,a a list is in lexicographical order
    pre condition:there are no duplicate words in the list.
    post condition:return a list is in lexicographical order
    time:O(nm)
    space:O(nm)
    • N is the number of items in wordlist.
    • M is the length of the words in wordlist and word. 
    • X is the number of 0’s in marker.
    '''
# O(NM + NX + XlogN) = O(NM)
#N is the number of items in wordlist. • M is the length of the words in wordlist and word. • X is the number of 0’s in marker
#generate the position of all misplaced letter 
#compare it with the original one
#cool the ra test

    #sor for sorted/ sorting
    #mn to try wether in
    #templist=[[]]*len(wordlist)
    templist=[]#O(1)
    tempword=[""]*len(word)#O(nm)
    tempsort=[[] for _ in range(123)]#generate the in storage of sorting O(1)
    s=0
    #ascii of zeros 
    zeros=[]
    tempfinal=[]
    #generate list of  words
    #find templist in it 
    for s in range(len(word))  :#O(Mn)
        if ord(word[s]) not in zeros:
            zeros.append(ord(word[s]))
    zeros.sort()# O(nlogn)
    
#
    for N in wordlist:#O(n)
        if worf(word, marker, N)==True:
            templist.append(N)

    
    for s in range(len(word)-1,-1,-1):#O(m)
        #sorting 
        for N in range(len(templist)):#O(mn)
            a=ord(templist[N][s])
            tempsort[a].append(templist[N])#O(mn)
        for j in zeros:
            #problem happens 
            if tempsort[j] != []:
                tempfinal+=tempsort[j]
        templist=tempfinal
        tempfinal=[]
        tempsort=[[] for _ in range(123)] #O(123*m)
    
    return templist


# check between input word and  list word
def worf(word, marker, check):
    '''
    usage: find if the word in list 

    arguments:word, marker, check, same as trainer
     return values: a boolean
    pre condition:input number in the matrix is specific
    post condition:return bool
    time:#O(m)
    space:#O(m)
    m for the length of the words in wordlist and word. 

    '''
#O(m)
    for i in range(len(check)):
        if(marker[i] == 0):
            if(check[i] == word[i]):
                return False
        else: 
            if(check[i] != word[i]):
                return False
    return True



# matrix_1 = [[1,  2,  27, 28, 29, 30, 49],
#             [3,  4,  25, 26, 31, 32, 48],
#             [5,  6,  23, 24, 33, 34, 47],
#             [7,  8,  21, 22, 35, 36, 46],
#             [9,  10, 19, 20, 37, 38, 45],
#             [11, 12, 17, 18, 39, 40, 44],
#             [13, 14, 15, 16, 41, 42, 43]]




def local_maximum(M):
    '''
    usage: find a number to be a local maximum if all its neighbours are smaller
than it

    arguments:m, a n*n matrix
     return values: res ,a single pair of coordinates
    pre condition:input number in the matrix is specific
    post condition:for res=[x,y], M[x][y] is a local minima (samller than values around it )
    time:O(n)
    space:O(n)
    n for length of the matrix

    '''
    #lft boundary 
    lb=-1
    #right boundary
    rb=len(M)-1
    #top boundary
    tb=-1
    #bottom boundary
    bb=len(M)-1
    res=aux_local_maximum(M,lb,rb,tb,bb)
    
    return res





def aux_local_maximum(M,lb,rb,tb,bb):
    '''
    usage: find a number to be a local maximum if all its neighbours are smaller
than it

    arguments:m, a n*n matrix, lb,rb,tb,bb is the 4 bounduaries of the array, they are integers.
     return values: res ,a single pair of coordinates
    pre condition:input number in the matrix is specific
    post condition:for res=[x,y], M[x][y] is a local minima (samller than values around it )
    time:O(n)
    space:O(n)
    n for length of the matrix

    '''
    # x is first row index, y is last  row index
    lb+=1
    tb+=1
    MidRowIndex= (tb+bb)//2
    MidCol=(rb+lb)//2
    middleCol=[]
    middleRow=[]
    top=[]
    bot=[]
    lef=[]
    ri=[]
    #may wrong, mid coloumn
    #O(logn)
    for i in range(tb,bb+1):
        middleCol.append(M[i][MidCol])
#mid roll
#O(logn)
    for i in range(lb,rb+1):
        middleRow.append(M[MidRowIndex][i])

#upper boun
    for j in range(lb,rb+1):
        top.append(M[tb][j])
#low
    for j in range(lb,rb+1):
        bot.append(M[bb][j])
#left
    for i in range(tb,bb+1):
        lef.append(M[i][lb])

    for i in range(tb,bb+1):
        ri.append(M[i][rb])


    bigCol=max(middleCol)
    bigro=max(middleRow)
    bigtop=max(top)
    bigbo=max(bot)
    biglf=max(lef)
    bigri=max(ri)#O(n)
    biggest=max(bigbo,bigCol,biglf,bigri,bigro,bigtop)
    Mmidcol=[]
    lbro=[]

    if biggest==bigbo:
        row=bb
        col=M[bb].index(biggest)
    if biggest==bigCol:
        for i in range(len(M)):
            Mmidcol.append(M[i][MidCol])
        row=Mmidcol.index(biggest)
        col=MidCol
    if biggest==bigro:
        col=M[MidRowIndex].index(biggest)
        row=MidRowIndex
    if biggest==bigtop:
        row=tb
        col=M[tb].index(biggest)
    if biggest==biglf:
        for i in range(len(M)):
            lbro.append(M[i][lb])
        row=lef.index(biggest)
        col=lb
    if biggest==bigri:
        for i in range(len(M)):
            lbro.append(M[i][lb])
        row=ri.index(biggest)
        col=rb

    res=[row,col]
#down


    if row==len(M)-1:
        if col==len(M)-1:
            #right conner

            if M[row][col] > max([M[row-1][col],M[row][col-1]]):
                return res

            #left conner
        elif col==0:
            if i > max([M[row-1][col],M[row][col+1]]):
                return res
        else:
            #left down
            if M[row][col]<M[row][col-1]:#col<MidCol
                return aux_local_maximum(M,lb,MidCol,MidRowIndex,bb)#[:MidRowIndex][:MidRowIndex]
                    #right up
            elif M[row][col]<M[row][col+1]:
                return aux_local_maximum(M,MidRowIndex,rb,MidCol,bb)#[:MidRowIndex][MidRowIndex:]
            elif M[row][col]<M[row][col+1]:
                if col<MidCol:
                    return aux_local_maximum(M,lb,MidCol,MidRowIndex,bb)
                elif col>MidCol:
                    return aux_local_maximum(M,MidCol,rb,MidRowIndex,bb)
            else:
                return res

#up
    if row==0:
        if col==len(M)-1:
            if M[row][col] > max([M[row+1][col],M[row][col-1]]):
                return res

            #left conner
        elif col==0:
            if i > max([M[row+1][col],M[row][col+1]]):
                return res
        else:
        #left up
            if M[row][col]< M[row][col-1]:
                return aux_local_maximum(M,lb,MidCol,tb,MidRowIndex)
        #right up
            elif M[row][col]< M[row][col+1]:
                return aux_local_maximum(M,MidCol,rb,tb,MidRowIndex)
            else:
                if M[row][col]<M[row+1][col]:
                    if col>middleCol:
                        #right up
                        return aux_local_maximum(M,MidCol,rb,tb,MidRowIndex)
                    else:
                        #left up
                        return aux_local_maximum(M,lb,MidCol,tb,MidRowIndex)
                else:
                    return res

### errorr may happen, risht most
    elif col==len(M)-1 and row != 0 and row != len(M)-1:
        #rignt up
        if M[row][col]< M[row-1][col]:
            return aux_local_maximum(M,MidCol,rb,tb,MidRowIndex)
        #right down
        elif M[row][col]< M[row+1][col]:
            return aux_local_maximum(M,MidCol,rb,MidRowIndex,bb)
#may none
        else:
            if M[row][col]< M[row][col-1]:
                if row>MidRowIndex:
                    return aux_local_maximum(M,lb,MidCol,MidRowIndex,bb)
                elif row<MidRowIndex:
                    #right up
                    return aux_local_maximum(M,MidCol,rb,tb,MidRowIndex)
                else:
                    return res

    elif col==0 and row != 0 and row != len(M)-1:
        #left up
        if M[row][col]< M[row-1][col] and col>MidCol:
            return aux_local_maximum(M,lb,MidCol,tb,MidRowIndex)
#left down
        elif M[row][col]< M[row+1][col] and col>MidCol:
            return aux_local_maximum(M,lb,MidCol,MidRowIndex,bb)
                      
         
        else:
            if M[row][col]< M[row][col+1] and col>MidCol:


                #
                if row> MidRowIndex:
                    return aux_local_maximum(M,lb,MidCol,MidRowIndex,bb)
                else:
                    return aux_local_maximum(M,lb,MidCol,tb,MidRowIndex)
            else:
                return res
       #left up   
    elif M[row][col]< M[row-1][col] and col<MidRowIndex:
        return aux_local_maximum(M,lb,MidCol,tb,MidRowIndex)

        #right up
    elif M[row][col]< M[row-1][col] and col>MidRowIndex:
        return aux_local_maximum(M,MidCol,rb,tb,MidRowIndex)
        #left down
    elif M[row][col]< M[row+1][col] and col<MidRowIndex:
        return aux_local_maximum(M,lb,MidCol,MidRowIndex,bb)
#right down
    elif M[row][col]< M[row+1][col] and col>MidRowIndex:
        return aux_local_maximum(M,MidCol,rb,MidRowIndex,bb)
    elif  M[row][col] > max(M[row-1][col],M[row][col-1],M[row][col+1],M[row+1][col]):
        return res

        


    
    


