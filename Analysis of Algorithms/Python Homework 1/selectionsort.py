def selectionSort(a):
    for i in range(len(a)):
        min = i
        for j in range(i+1, len(a)):
            if a[min] > a[j]:
                min = j
        temp = a[i]
        a[i] = a[min]
        a[min] = temp
    return a

def mergeSort(a):
    if len(a)>1:
        mid = len(a)//2
        lefthalf = a[:mid]
        righthalf = a[mid:]

        mergeSort(lefthalf)
        mergeSort(righthalf)

        i=0
        j=0
        k=0
        while i < len(lefthalf) and j < len(righthalf):
            if lefthalf[i] <= righthalf[j]:
                a[k]=lefthalf[i]
                i=i+1
            else:
                a[k]=righthalf[j]
                j=j+1
            k=k+1

        while i < len(lefthalf):
            a[k]=lefthalf[i]
            i=i+1
            k=k+1

        while j < len(righthalf):
            a[k]=righthalf[j]
            j=j+1
            k=k+1
    return a


def rand_list(n):
    from random import randrange
    rand_list = []
    for i in range(0,n):
        rand_list.append(randrange(100))
    return rand_list

def test_algorithm():
    import time
    for i in range (2,1000):
        random_list1 = rand_list(i)
        startS = time.perf_counter_ns()
        selectionSort(random_list1)
        endS = time.perf_counter_ns()
        random_list2 = rand_list(i)
        startM = time.perf_counter_ns()
        mergeSort(random_list2)
        endM = time.perf_counter_ns()
        selectionTime = endS - startS
        mergeTime = endM - startM
        print(mergeTime)
        print(selectionTime)
        if mergeTime < selectionTime:
            return i

print(test_algorithm())

