def problem(n):
    row_counter = 0
    col_counter = 0
    max_decrement = 0
    for i in range((n*2) - 1, 0):
        row_counter += 1
        max_decrement = row_counter % n
        for j in range((n*2) - 1, 0):
            print (n - col_counter)
            col_counter += 1


problem(4)
