import math

def print_ij(n):
    i = 2
    while (i < n):
        j = n
        while (j >= 0):
            print(i, j)
            j = j - math.floor(n/6)
        i = i + 1

# print_ij(6)
# print_ij(18)
# print_ij(72)
print_ij(216)
