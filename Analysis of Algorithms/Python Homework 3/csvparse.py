import csv

with open('AAPL.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    line_count = 0
    list = []
    for row in csv_reader:
        if line_count == 0:
            print(" ".join(row))
            line_count +=1
        else:
            print(float(row[2]) - float(row[3]))
            list.append(float(row[2]) - float(row[3]))
            print(f'date: {row[0]}\t open: {row[1]}\t high: {row[2]}\t low: {row[3]}\t close: {row[4]}\t adj close: {row[5]}\t volume: {row[6]}')
            line_count += 1
    print(f'Processed {line_count} lines.')