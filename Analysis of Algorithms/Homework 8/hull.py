import numpy as np
import matplotlib.pyplot as plt
from math import atan2

points = []
xcoords = []
ycoords = []

class point:

    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.polar = 0

def dist_sq(p1, p2):
    return (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y)

def angle(p1, p2, p3):
    a = (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y)
    if (a == 0):
        return 0
    if (a > 0):
        return 1
    else:
        return 2

def find_p0(points):
    min_y = float("inf")
    min_index = 0
    for i, point in enumerate(points):
        if point.y < min_y:
            min_y = point.y
            min_index = i
        if point.y == min_y:
            if point.x < points[min_index].x:
                min_index = i
    return points[min_index], min_index

def scatter(n):
    for i in range(0,n):
        point.x = np.random.randint(100)
        point.y = np.random.randint(100)
        xcoords.append(point.x)
        ycoords.append(point.y)
        points.append(point(point.x,point.y))
    plt.scatter(xcoords, ycoords)
    plt.show()

def polar_angle(p0, p1):
    y_span = p1.y - p0.y
    x_span = p1.x - p0.x
    return atan2(y_span, x_span)

def calc_angle(p1, p2, p3):
    return(p2.x-p3.x)*(p1.y-p3.y)-(p2.y-p3.y)*(p1.x-p3.x)

def graham_scan(points, n):
    p0, index = find_p0(points)
    points[0], points[index] = points[index], points[0]
    remove = []
    for i in range(1, n):
        while (i < n - 1 and angle(p0, points[i], points[i+1]) == 0):
            remove.append(i)
            i += i
    for i in range(len(remove)):
        points.pop(remove[i])
    polar = []
    for i in range(1, len(points)):
        pa = polar_angle(p0, points[i])
        polar.append(pa)
        points[i].polar = pa
    points.sort(key=lambda p1: p1.polar)
    p1 = points[1]
    p2 = points[2]
    stack = []
    stack.insert(0, p0)
    stack.insert(0, p1)
    stack.insert(0, p2)
    for i in range(3, n):
        while(calc_angle(stack[len(stack) - 2], stack[len(stack) - 1], points[i]) <= 0):
            stack.pop()
            for obj in stack:
                print(obj.x, obj.y)
        stack.insert(0, points[i])
    return stack


n = int(input("Enter number of points: "))
scatter(n)
ans = graham_scan(points, n)
axc = []
ayc = []
for obj in ans:
    axc.append(obj.x)
    ayc.append(obj.y)
plt.plot(axc, ayc)
plt.show()



