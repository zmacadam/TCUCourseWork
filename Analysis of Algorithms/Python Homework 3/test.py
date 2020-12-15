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
        print("left")
        print(left_low, left_high, left_sum)
        (right_low, right_high, right_sum) = Find_Maximum_Subarray(A, mid+1, high)
        print("right")
        print(right_low, right_high, right_sum)
        (cross_low, cross_high, cross_sum) = Max_Crossing_Subarray(A, low, mid, high)
        print("cross")
        print(cross_low, cross_high, cross_sum)
        if left_sum >= right_sum and left_sum >= cross_sum:
            return(left_low, left_high, left_sum)
        elif right_sum >= left_sum and right_sum >= cross_sum:
            return(right_low, right_high, right_sum)
        else:
            return(cross_low, cross_high, cross_sum)

Find_Maximum_Subarray([13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7], 0, 15)