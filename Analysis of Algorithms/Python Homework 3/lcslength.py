def lcslength(x, y):
    m = len(x)
    n = len(y)
    c = [[0],[0]]
    b = [[0]]
    for i in range(1, m):
        c[i].append([0])
    for j in range(0, n):
        c[j].append([0])
    for i in range(1, m):
        for j in range(1, n):
            if x[i] == y[j]:
                c[i][j].append(c[i - 1][j - 1] + 1)
                b[i][j].append("up-left")
            elif c[i - 1][j] >= c[i][j - 1]:
                c[i][j].append(c[i - 1][j])
                b[i][j].append("up")
            else:
                c[i][j].append(c[i][j - 1])
                b[i][j].append("left")
    return (c, b)

x = [1, 0, 0, 1, 0, 1, 0, 1]
y = [0, 1, 0, 1, 1, 0, 1, 1, 0]

d = []
e = []
(d, e) = lcslength(x, y)