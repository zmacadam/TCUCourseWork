import math

def Max_Crossing_Subarray(A, low, mid, high):
    left_sum = float("-inf")
    sum = 0
    max_left = 0
    max_right = 0
    for i in range(mid, low, -1):
        sum = sum + A[i]
        if sum > left_sum:
            left_sum = sum
            max_left = i
    right_sum = float("-inf")
    sum = 0
    for j in range(mid+1, high):
        sum = sum + A[j]
        if sum > right_sum:
            right_sum = sum
            max_right = j
    return(max_left, max_right, left_sum + right_sum)

def Find_Maximum_Subarray(A, low, high):
    if high == low:
        return (low, high, A[low])
    else:
        mid = math.floor((low+high)/2)
        (left_low, left_high, left_sum) = Find_Maximum_Subarray(A, low, mid)
        (right_low, right_high, right_sum) = Find_Maximum_Subarray(A, mid+1, high)
        (cross_low, cross_high, cross_sum) = Max_Crossing_Subarray(A, low, mid, high)
        if left_sum >= right_sum and left_sum >= cross_sum:
            return(left_low, left_high, left_sum)
        elif right_sum >= left_sum and right_sum >= cross_sum:
            return(right_low, right_high, right_sum)
        else:
            return(cross_low, cross_high, cross_sum)

def Find_Maximum_Subarray_BF(A, n):
    maximum = float("-inf")
    for i in range(0, n):
        for j in range(0, n):
            current_sum = 0
            for k in range(i,j):
                current_sum += A[k]
            maximum = max(maximum,current_sum)
    return maximum

import csv

with open('AAPL.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    aapl = []
    high = []
    for row in csv_reader:
        if line_count == 0:
            line_count +=1
        elif line_count == 1:
            aapl.append(float(row[2]) - float(row[3]))
            high.append(float(row[2]))
            line_count +=1
        else:
            highindex = line_count
            todayhigh = float(row[2])
            high.append(todayhigh)
            yesterdayhigh = high[highindex-2]
            difference = todayhigh - yesterdayhigh
            aapl.append(difference)
            if (line_count == 29):
                print(f'{row[0]}')
            if (line_count == 191):
                print(f'{row[0]}')
            line_count += 1

    print(Find_Maximum_Subarray(aapl, 0, 250))
    print(Find_Maximum_Subarray_BF(aapl, 250))

with open('AMZN.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    amzn = []
    high = []
    for row in csv_reader:
        if line_count == 0:
            line_count +=1
        elif line_count == 1:
            amzn.append(float(row[2]) - float(row[3]))
            high.append(float(row[2]))
            line_count +=1
            print(f'{row[0]}')
        else:
            highindex = line_count
            todayhigh = float(row[2])
            high.append(todayhigh)
            yesterdayhigh = high[highindex-2]
            difference = todayhigh - yesterdayhigh
            amzn.append(difference)  
            if (line_count == 170):
                print(f'{row[0]}')
            line_count += 1

    print(Find_Maximum_Subarray(amzn, 0, 250))
    print(Find_Maximum_Subarray_BF(amzn, 250))

with open('DIS.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    dis = []
    high = []
    for row in csv_reader:
        if line_count == 0:
            line_count +=1
        elif line_count == 1:
            dis.append(float(row[2]) - float(row[3]))
            high.append(float(row[2]))
            line_count +=1
        else:
            highindex = line_count
            todayhigh = float(row[2])
            high.append(todayhigh)
            yesterdayhigh = high[highindex-2]
            difference = todayhigh - yesterdayhigh
            dis.append(difference)
            if (line_count == 86):
                print(f'{row[0]}')
            if (line_count == 218):
                print(f'{row[0]}')
            line_count += 1

    print(Find_Maximum_Subarray(dis, 0, 250))
    print(Find_Maximum_Subarray_BF(dis, 250))

with open('NFLX.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    nflx = []
    high = []
    for row in csv_reader:
        if line_count == 0:
            line_count +=1
        elif line_count == 1:
            nflx.append(float(row[2]) - float(row[3]))
            high.append(float(row[2]))
            line_count +=1
            print(f'{row[0]}')
        else:
            highindex = line_count
            todayhigh = float(row[2])
            high.append(todayhigh)
            yesterdayhigh = high[highindex-2]
            difference = todayhigh - yesterdayhigh
            nflx.append(difference)
            if (line_count == 119):
                print(f'{row[0]}')
            line_count += 1

    print(Find_Maximum_Subarray(nflx, 0, 250))
    print(Find_Maximum_Subarray_BF(nflx, 250))

def test_algorithm(a):
    import time
    from timeit import default_timer as timer
    for i in range (1,251):
        startD = timer()
        Find_Maximum_Subarray(nflx, 0, i)
        endD = timer()
        startB = timer()
        Find_Maximum_Subarray_BF(nflx, i)
        endB = timer()
        divideTime = endD - startD
        bruteTime = endB - startB
        if a == 1:
            print(math.floor(divideTime*1000000))
        if a == 2:
            print(math.floor(bruteTime*1000000))
        if divideTime < bruteTime:
            return i
