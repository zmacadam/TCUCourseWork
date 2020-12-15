
def find_peak(arr):
    list_length = len(arr)
    if not arr:
        return []
    if list_length == 1:
        return [0]
    result = []
    if arr[0] > arr[1]:
        result.append(0)
    if list_length > 1:
        if arr[list_length - 2] < arr[list_length - 1]:
            result.append(list_length - 1)
    for i in range (0,list_length):
        if i+2 < list_length:
            if arr[i] < arr[i+1] and arr[i+1] > arr[i+2]:
                result.append(i+1)
    return(result)

def rand_list(n):
    from random import randrange
    rand_list = []
    for i in range(0,n):
        rand_list.append(randrange(100))
    return rand_list

def test_algorithm():
    import time
    for i in range(1,251):
        random_list = rand_list(i)
        start = time.perf_counter_ns()
        find_peak(random_list)
        end = time.perf_counter_ns()
        print((end - start))

# test_algorithm()
print(find_peak([5,10,20,15]))
print(find_peak([10,20,15,2,23,90,67]))
print(find_peak([]))
print(find_peak([1]))
print(find_peak([1,2]))
print(find_peak([2,1]))
print(find_peak([2,1,2]))
print(find_peak([1,1]))
print(find_peak([2,2,1,1,3,3,1,1,-1,-1,0]))
