def prparens(s, i, j):
    if i == j:
        print("A", end = "")
        print(i, end = "")
    else:
        print("(", end = "")
        prparens(s, i, s[i][j])
        prparens(s, s[i][j] + 1, j)
        print(")", end = "")

s = [[0,0,0,0,0,0,0],[0,0,1,2,2,4,2],[0,0,0,2,2,2,2],[0,0,0,0,3,4,4],[0,0,0,0,0,4,4],[0,0,0,0,0,0,5]]
for r in s:
    for c in r:
        print(c, end = " ")
    print()

prparens(s,1,6)

